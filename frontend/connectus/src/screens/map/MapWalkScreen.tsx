import React, {useEffect, useMemo, useRef, useState} from 'react';
import {
  DeviceEventEmitter,
  Dimensions,
  Pressable,
  StyleSheet,
  View,
} from 'react-native';
import {
  NavigationContainer,
  NavigationContainerRef,
  useNavigation,
} from '@react-navigation/native';
import MapView from 'react-native-map-clustering';
import {
  LatLng,
  Marker,
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
import {convertSecondsToTime, formatTime} from '@/utils';
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
    latitude: 35.09064834938094,
    longitude: 128.85494849796893,
  },
  {
    latitude: 35.09063223602116,
    longitude: 128.85511808214923,
  },
  {
    latitude: 35.09060123205063,
    longitude: 128.85478844604273,
  },
];

type Navigation = StackNavigationProp<MapStackParamList>;

type deltaType = {
  latitudeDelta: number;
  longitudeDelta: number;
};

export default function MapWalkScreen() {
  const navigation = useNavigation<Navigation>();
  const [trackingMode, setTrackingMode] = useState<boolean>(false);
  const {userLocation} = useUserLocation();
  const [mapDelta, setMapDelta] = useState<deltaType>({
    latitudeDelta: 0.001,
    longitudeDelta: 0.001,
  });
  const mapRef = useRef<MapView | null>(null);
  const bottomSheetNav =
    useRef<NavigationContainerRef<MapBottomSheetTabParamList> | null>(null);
  const bottomSheetRef = useRef<BottomSheet>(null);
  const [time, setTime] = useState<number>(0);
  const [indicagteTime, setIndicateTime] = useState<string | null>(null);
  const [trace, setTrace] = useState<LatLng[]>([]);
  const snapPoints = useMemo(() => ['25%', '50%', '75%'], []);

  //  바텀시트를 열고닫을 코드
  const handleBottomSheetOpen = () => bottomSheetRef.current?.expand();
  const handleBottomSheetClose = () => bottomSheetRef.current?.close();
  // ios 환경에서 노치를 계산해주는 함수
  const inset = useSafeAreaInsets();
  // 1초마다 time을 증가시키기 위해 useInterval에 넣어줄 코드입니다
  const tick = () => {
    setTime(prev => prev + 1);
  };
  const traceTick = () => {
    setTrace(prev => [...prev, userLocation]);
  };
  useInterval(tick, 1000);
  useInterval(traceTick, 3000);
  /**
   * @todo 지금까지의 정보를 props로 넘겨주는 코드를 작성해야합니다(navigation에 params로 넣으면되지 않을까요?)
   * 걸은 시간, 거리, 좌표는 이 스크린에서 관리할 예정이며, 작성 post는 전역으로 관리합니다.
   */
  const navigateToResultScreen = () => {
    handleBottomSheetClose();
    navigation.navigate('MapResult');
  };

  const handleCheckDragged = (region: Region, details) => {
    if (details.isGesture) {
      setTrackingMode(false);
    }
  };

  const handleChangeDelta = (region: Region) => {
    setMapDelta({
      longitudeDelta: region.longitudeDelta,
      latitudeDelta: region.latitudeDelta,
    });
  };

  const handleMarkerClick = () => {
    setTrackingMode(false);
    handleBottomSheetOpen();
    // 밑줄이 그여있지만 작동은 합니다...진짜입니다...
    bottomSheetNav.current && bottomSheetNav.current.navigate('Feed');
  };

  const handleClusterClick = () => {
    setTrackingMode(false);
    handleBottomSheetOpen();
    // 밑줄이 그여있지만 작동은 합니다...진짜입니다...
    bottomSheetNav.current && bottomSheetNav.current.navigate('FeedList');
  };

  const handleTrackingMode = () => {
    setTrackingMode(!trackingMode);
  };

  // 디바이스 전체에 listener설정
  DeviceEventEmitter.addListener(
    'navigateToResultScreen',
    navigateToResultScreen,
  );

  // 1초마다 하단 인디케이터의 시간을 업데이트해주는 useEffect 함수입니다.
  useEffect(() => {
    const {seconds, minutes, hours} = convertSecondsToTime(time);
    setIndicateTime(formatTime(hours, minutes, seconds));
  }, [time]);

  // 디바이스 종료 시 리스너 제거를 위한 useEffect 입니다
  useEffect(() => {
    return () => {
      DeviceEventEmitter.removeAllListeners('navigateToResultScreen');
    };
  }, []);
  return (
    <>
      <MapView
        ref={mapRef}
        onClusterPress={handleClusterClick}
        style={styles.container}
        provider={PROVIDER_GOOGLE}
        showsUserLocation
        showsMyLocationButton={false}
        zoomEnabled={true}
        initialRegion={{...userLocation, ...mapDelta}}
        region={trackingMode ? {...userLocation, ...mapDelta} : undefined}
        onRegionChangeComplete={handleChangeDelta}
        onRegionChange={handleCheckDragged}>
        <Polyline
          coordinates={trace}
          strokeWidth={8}
          strokeColor={colors.primaryColorBlue}
        />
        {/* 게시글을 확인해줄 마커들 */}
        {DUMMY_POSITION.map((data, index) => {
          return (
            <Marker
              key={index}
              coordinate={data}
              onPress={handleMarkerClick}
              style={{width: 100, height: 100}}
              image={require('@/assets/scroll2.png')}
            />
          );
        })}
        {DUMMY_GATHER.map((data, index) => {
          return (
            <Marker
              key={index}
              coordinate={data}
              onPress={handleMarkerClick}
              style={{width: 100, height: 100}}
              image={require('@/assets/star.png')}
            />
          );
        })}
      </MapView>
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
            <Ionicons name="timer-outline" size={32} />
            <MainText style={styles.timeText}>{indicagteTime}</MainText>
          </View>
          <View style={styles.distanceTextContainer}>
            <FontAwesome5 name={'walking'} size={28} />
            <MainText style={styles.distanceText}>{'00:42KM'}</MainText>
          </View>
        </View>
        <Pressable style={styles.menuButton} onPress={handleBottomSheetOpen}>
          <Ionicons name="menu" size={42} />
        </Pressable>
      </View>
      <BottomSheet
        ref={bottomSheetRef}
        index={-1}
        snapPoints={snapPoints}
        enablePanDownToClose={true}>
        <SafeAreaProvider>
          <NavigationContainer independent={true} ref={bottomSheetNav}>
            <MapBottomSheetNavigator />
          </NavigationContainer>
          {/* <QuickMenuHomeScreen /> */}
        </SafeAreaProvider>
      </BottomSheet>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
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
});
