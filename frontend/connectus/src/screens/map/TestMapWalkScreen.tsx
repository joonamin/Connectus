import React, {useEffect, useMemo, useRef, useState} from 'react';
import {
  DeviceEventEmitter,
  Dimensions,
  Modal,
  Pressable,
  SafeAreaView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import {
  NavigationContainer,
  NavigationContainerRef,
  useNavigation,
} from '@react-navigation/native';
import MapView from 'react-native-map-clustering';
import Map, {
  Circle,
  Details,
  LatLng,
  PROVIDER_GOOGLE,
  Polyline,
  Region,
} from 'react-native-maps';
import Ionicons from 'react-native-vector-icons/Ionicons';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import BottomSheet from '@gorhom/bottom-sheet';

import useUserLocation from '@/hooks/useUserLocation';
import colors from '@/constants/colors';
import MainText from '@/components/text/MainText';
import useInterval from '@/hooks/useInterval';
import {convertSecondsToTime, formatTime, getDistance} from '@/utils';
import {
  SafeAreaProvider,
  useSafeAreaInsets,
} from 'react-native-safe-area-context';
import MapBottomSheetNavigator, {
  MapBottomSheetTabParamList,
} from '@/navigations/Tabs/MapBottomSheetNavigator';
import EventIndicator from '@/components/my/EventIndicator';
import {StackNavigationProp} from '@react-navigation/stack';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import CustomMarker from '@/components/map/CustomMarker';
import Geolocation from '@react-native-community/geolocation';
import useRouteStore from '@/store/useRouteStore';
import useModal from '@/hooks/useModal';
import BottomSheetQuickStackNavigator from '@/navigations/stack/BottomSheetQuickStackNavigator';
import {useQuery} from '@tanstack/react-query';
import {getNearMarker} from '@/api/spot';
import mapStyle from '@/style/mapStyle';

const DUMMY_POSITION = [
  {
    latitude: 35.09198080366587,
    longitude: 128.85326819627983,
  },
  {
    latitude: 35.09056972684619,
    longitude: 128.85331301309265,
  },
  {
    latitude: 35.09613483737488,
    longitude: 128.8535979719482,
  },
];

const DUMMY_GATHER = [
  {
    gatherId: 1,
    latitude: 35.09064834938094,
    longitude: 128.85494849796893,
  },
  {
    gatherId: 1,
    latitude: 35.09063223602116,
    longitude: 128.85511808214923,
  },
  {
    gatherId: 1,
    latitude: 35.09060123205063,
    longitude: 128.85478844604273,
  },
];

type Navigation = StackNavigationProp<MapStackParamList>;

// type deltaType = {
//   latitudeDelta: number;
//   longitudeDelta: number;
// };

export default function TestMapWalkScreen() {
  const navigation = useNavigation<Navigation>();
  // 유저 추적을 위한 boolean 값입니다
  const [trackingMode, setTrackingMode] = useState<boolean>(true);
  // 유저가 걸은 거리를 담을 state입니다.
  const [distance, setDistance] = useState(0);
  // 유저의 위치좌표가 담겨있습니다
  const {userLocation} = useUserLocation();
  // 경로 공유 페이지에서 선택한 route의 존재여부 확인
  const {route} = useRouteStore();
  // 초기 지도의 확대값 설정 및, drag이벤트로 관리할 delta값
  const [mapDelta, setMapDelta] = useState({
    latitudeDelta: 0.001,
    longitudeDelta: 0.001,
  });
  // 초기 지도의 중앙 좌표값 및
  const [mapPos, setMapPos] = useState<LatLng>();

  const mapRef = useRef<Map | null>(null);
  const bottomSheetNav =
    useRef<NavigationContainerRef<MapBottomSheetTabParamList> | null>(null);
  const bottomSheetRef = useRef<BottomSheet>(null);
  const [time, setTime] = useState<number>(0);
  const [indicateTime, setIndicateTime] = useState<string | null>(null);
  const [trace, setTrace] = useState<LatLng[]>([]);
  const snapPoints = useMemo(() => ['25%', '50%', '75%'], []);

  // 산책 종료 확인을 위한 모달을 열고닫는 state
  const {isVisible, show, hide} = useModal();

  //  바텀시트를 열고닫을 코드
  const handleBottomSheetOpen = () => bottomSheetRef.current?.expand();
  const handleBottomSheetClose = () => bottomSheetRef.current?.close();
  // ios 환경에서 노치를 계산해주는 함수
  const inset = useSafeAreaInsets();
  // 1초마다 time을 증가시키기 위해 useInterval에 넣어줄 코드입니다
  const tick = () => {
    setTime(prev => prev + 1);
  };
  // 3초마다 한번씩 실행하면서 배열에 유저의 정보를 저장합니다
  const traceTick = () => {
    const dist = getDistance(userLocation, trace[trace.length - 1]);
    setTrace(prev => [...prev, userLocation]);
    setDistance(prev => {
      return prev + dist;
    });
    // console.log(distance.toFixed(2));
  };
  useInterval(tick, 1000);
  useInterval(traceTick, 3000);

  /**
   * @todo 지금까지의 정보를 props로 넘겨주는 코드를 작성해야합니다(navigation에 params로 넣으면되지 않을까요?)
   * 걸은 시간, 거리, 좌표는 이 스크린에서 관리할 예정이며, 작성 post는 전역으로 관리합니다.
   */
  const openBottomSheetModal = () => {
    show();
  };

  /**
   * 모달 창을 닫고 결과페이지로 이동합니다.
   */
  const handleWalkDone = () => {
    hide();
    navigation.pop();
    navigation.navigate('MapResult', {
      time: time,
      distance: distance,
      walkRoute: trace,
    });
  };

  // map screen에서 드래그 시, 화면고정을 해제합니다
  const onRegionChange = async (region: Region, details: Details) => {
    if (details.isGesture) {
      setTrackingMode(false);
    }
  };

  // userFocus해제시 화면에 고정시킬 좌표를 저장하기위해 실행하는 함수입니다
  // const onRegionChangeComplete = async (region: Region, details: Details) => {
  //   if (details.isGesture === true) {
  //     setMapPos({
  //       latitude: region.latitude,
  //       longitude: region.longitude,
  //     });
  //     setMapDelta({
  //       longitudeDelta: region.longitudeDelta,
  //       latitudeDelta: region.latitudeDelta,
  //     });
  //     return;
  //   }
  // };

  // 마커들을 3초마다 한번씩 요청할 query
  // const {data, isError} = useQuery({
  //   refetchInterval: 3000,
  //   queryFn: () => getNearMarker(),
  //   queryKey: ['test'],
  // });

  // console.log(data);
  // console.log(isError);

  const handleMenuPress = () => {
    bottomSheetNav.current &&
      bottomSheetNav.current.reset({routes: [{name: 'Home'}]});
    handleBottomSheetOpen();
  };

  // feed를 press시 바텀시트를 열고 Feed페이지로 이동합니다
  const handleMarkerPress = (coordinate: LatLng) => {
    setTrackingMode(false);
    setMapPos({
      latitude: coordinate.latitude,
      longitude: coordinate.longitude,
    });
    bottomSheetNav.current && bottomSheetNav.current.navigate('Feed');
    handleBottomSheetOpen();
  };

  const handleClusterPress = () => {
    setTrackingMode(false);
    bottomSheetNav.current && bottomSheetNav.current.navigate('FeedList');
    handleBottomSheetOpen();
  };

  // 모여라 press시 모여라 페이지로 이동합니다
  const handleGatherPress = (gatehrId: number) => {
    setTrackingMode(false);
    bottomSheetNav.current &&
      bottomSheetNav.current.navigate('Gather', {gatherId: gatehrId});
    handleBottomSheetOpen();
  };

  const handleTrackingMode = () => {
    setMapPos({...userLocation});
    setTrackingMode(!trackingMode);
  };

  // 디바이스 전체에 listener설정
  DeviceEventEmitter.addListener('openBottomSheetModal', openBottomSheetModal);

  useEffect(() => {
    Geolocation.getCurrentPosition(
      info => {
        const {latitude, longitude} = info.coords;
        // polyline 그리는데 필요한 LatLag
        setTrace([{latitude, longitude}]);
        // 초기 좌표값 설정.
        setMapPos({latitude, longitude});
      },
      () => {
        console.log('error');
      },
      {
        // 상세 좌표를 요청하는 코드
        enableHighAccuracy: true,
        distanceFilter: 0,
        interval: 3000,
        fastestInterval: 2000,
      },
    );
  }, []);

  // 1초마다 하단 인디케이터의 시간을 업데이트해주는 useEffect 함수입니다.
  useEffect(() => {
    const {seconds, minutes, hours} = convertSecondsToTime(time);
    setIndicateTime(formatTime(hours, minutes, seconds));
  }, [time]);

  // 디바이스 종료 시 리스너 제거를 위한 useEffect 입니다
  useEffect(() => {
    return () => {
      DeviceEventEmitter.removeAllListeners('openBottomSheetModal');
    };
  }, []);

  return (
    <>
      {mapPos && (
        <MapView
          ref={mapRef}
          // onClusterPress={handleClusterPress}
          style={styles.container}
          provider={PROVIDER_GOOGLE}
          showsUserLocation
          showsMyLocationButton={true}
          zoomEnabled={true}
          customMapStyle={mapStyle}
          initialRegion={{...mapPos, ...mapDelta}}
          onUserLocationChange={event => {
            const {latitude, longitude} = event.nativeEvent.coordinate;
            if (trackingMode) {
              mapRef.current?.animateToRegion({
                latitude,
                longitude,
                ...mapDelta,
              });
            }
          }}
          onMapReady={() => {
            Geolocation.getCurrentPosition(
              info => {
                const {latitude, longitude} = info.coords;
                setTrace([{latitude, longitude}]);
                setMapPos({latitude, longitude});
              },
              () => {
                console.log('error');
              },
              {
                // 상세 좌표를 요청하는 코드
                enableHighAccuracy: true,
                distanceFilter: 0,
                // interval: 3000,
                // fastestInterval: 2000,
              },
            );
          }}
          // region={trackingMode ? {...userLocation, ...mapDelta} : undefined}
          // onRegionChangeComplete={onRegionChangeComplete}
          onRegionChange={onRegionChange}>
          <Polyline
            coordinates={trace}
            strokeWidth={8}
            strokeColor={colors.primaryColorBlue}
          />
          {route && (
            <Polyline
              coordinates={route}
              strokeColor={colors.primaryColorPink}
              strokeWidth={10}
            />
          )}
          {/* 게시글을 확인해줄 마커들 */}
          {DUMMY_POSITION.map((data, index) => {
            return (
              <CustomMarker
                key={index}
                coordinate={data}
                onPress={event =>
                  handleMarkerPress(event.nativeEvent.coordinate)
                }
                type={2}
              />
            );
          })}
          {/* 모여라 좌표 */}
          {DUMMY_GATHER.map((data, index) => {
            return (
              <CustomMarker
                key={index}
                coordinate={data}
                onPress={() => handleGatherPress(data.gatherId)}
                type={3}
              />
            );
          })}
          <Circle
            center={{...userLocation}}
            radius={100}
            strokeWidth={0}
            fillColor="rgba(193, 200, 210, 0.5)"
          />
        </MapView>
      )}
      <View style={[styles.eventIndicator, {top: inset.top || 20}]}>
        <EventIndicator />
      </View>
      {/* 화면이 유저의 위치를 따라가는지 설정 가능한 버튼 */}
      <View style={styles.trackingButtonContainer}>
        <Pressable style={styles.trackingButton} onPress={handleTrackingMode}>
          <MaterialIcons
            color={
              trackingMode ? colors.primaryColorBlue : colors.buttonBackground
            }
            name="my-location"
            size={42}
          />
        </Pressable>
      </View>
      <View style={styles.bottomIndicator}>
        <View style={styles.indicatorContainer}>
          <View style={styles.timeTextContainer}>
            <Ionicons
              name="timer-outline"
              color={colors.background}
              size={32}
            />
            <MainText style={styles.timeText}>{indicateTime}</MainText>
          </View>
          <View style={styles.distanceTextContainer}>
            <FontAwesome5
              name={'walking'}
              color={colors.background}
              size={28}
            />
            <MainText style={styles.distanceText}>{`${distance.toFixed(
              2,
            )}km`}</MainText>
          </View>
        </View>
        <Pressable style={styles.menuButton} onPress={handleMenuPress}>
          <Ionicons name="menu" color={colors.background} size={42} />
        </Pressable>
      </View>
      <BottomSheet
        ref={bottomSheetRef}
        index={-1}
        snapPoints={snapPoints}
        enablePanDownToClose={true}>
        <SafeAreaProvider>
          <NavigationContainer independent={true} ref={bottomSheetNav}>
            <BottomSheetQuickStackNavigator />
          </NavigationContainer>
        </SafeAreaProvider>
      </BottomSheet>
      <Modal visible={isVisible} transparent={true} animationType="slide">
        <SafeAreaView style={styles.centeredView} onTouchEnd={hide}>
          <View style={styles.modalView} onTouchEnd={e => e.stopPropagation()}>
            <MainText style={[styles.fontBlack, {textAlign: 'center'}]}>
              산책을 종료하시겠어요?
            </MainText>
            <View style={[styles.row, styles.gap15]}>
              <Pressable onPress={handleWalkDone} style={styles.modalButton}>
                <MainText style={[{textAlign: 'center'}]}>산책 종료</MainText>
              </Pressable>
              <Pressable onPress={hide} style={styles.modalButton}>
                <MainText style={[{textAlign: 'center'}]}>취소</MainText>
              </Pressable>
            </View>
          </View>
        </SafeAreaView>
      </Modal>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  row: {
    flexDirection: 'row',
  },
  fontBlack: {color: colors.background},
  gap15: {gap: 15},
  bottomIndicator: {
    width: Dimensions.get('screen').width,
    height: 70,
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: colors.white,
    position: 'absolute',
    bottom: 0,
  },
  indicatorContainer: {
    position: 'absolute',
    width: '80%',
    flexDirection: 'row',
    alignItems: 'center',
  },
  timeTextContainer: {
    gap: 5,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
  timeText: {
    color: colors.background,
  },
  distanceTextContainer: {
    position: 'absolute',
    right: 0,
    gap: 5,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
  distanceText: {
    color: colors.background,
  },
  menuButton: {
    position: 'absolute',
    right: 15,
  },
  eventIndicator: {
    width: '100%',
    position: 'absolute',
    justifyContent: 'center',
    alignItems: 'center',
  },
  trackingButtonContainer: {
    width: 70,
    height: 70,
    position: 'absolute',
    alignItems: 'center',
    backgroundColor: colors.white,
    justifyContent: 'center',
    right: 15,
    bottom: 80,
    borderRadius: 50,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.23,
    shadowRadius: 2.62,

    elevation: 4,
  },
  trackingButton: {
    width: '100%',
    height: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  testButton: {
    width: 70,
    height: 70,
    position: 'absolute',
    alignItems: 'center',
    backgroundColor: colors.white,
    justifyContent: 'center',
    right: 15,
    bottom: 200,
    borderRadius: 50,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.23,
    shadowRadius: 2.62,

    elevation: 4,
  },
  centeredView: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalView: {
    width: '70%',
    height: 200,
    margin: 20,
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 35,
    alignItems: 'center',
    justifyContent: 'center',
    shadowColor: '#000',
    gap: 15,
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5,
  },
  modalButton: {
    padding: 10,
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
  },
});
