import React, {useEffect, useMemo, useRef, useState} from 'react';
import {
  Dimensions,
  Pressable,
  SafeAreaView,
  StyleSheet,
  View,
} from 'react-native';
import MapView from 'react-native-map-clustering';
import {LatLng, PROVIDER_GOOGLE} from 'react-native-maps';
import useUserLocation from '../../hooks/useUserLocation';
import MainText from '@/components/text/MainText';
import {
  CompositeNavigationProp,
  NavigationContainer,
  NavigationContainerRef,
  useNavigation,
} from '@react-navigation/native';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import colors from '@/constants/colors';
import Geolocation from '@react-native-community/geolocation';
import mapStyle from '@/style/mapStyle';
import {deleteSaveUserPos, getNearMarker, startSaveUserPos} from '@/api/spot';
import useAuthStore from '@/store/useAuthStore';
import {queryKeys} from '@/constants';
import {useQuery} from '@tanstack/react-query';
import CustomMarker from '@/components/map/CustomMarker';
import useLookUpPost from '@/store/useLookUpPost';
import BottomSheet from '@gorhom/bottom-sheet';
import {SafeAreaProvider} from 'react-native-safe-area-context';
import BottomSheetQuickStackNavigator from '@/navigations/stack/BottomSheetQuickStackNavigator';
import {MapBottomSheetTabParamList} from '@/navigations/Tabs/MapBottomSheetNavigator';
import {domainType} from '@/types';

type Navigation = CompositeNavigationProp<
  StackNavigationProp<MapStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

export default function MapHomeScreen() {
  const {user} = useAuthStore();
  const {lookUpFeed} = useLookUpPost();
  const bottomSheetRef = useRef<BottomSheet>(null);
  const mapRef = useRef<MapView | null>(null);
  const navigation = useNavigation<Navigation>();
  const [initPos, setInitPos] = useState<LatLng>();
  const [isStarting, setIsStarting] = useState<boolean>(false);
  const snapPoints = useMemo(() => ['25%', '50%', '75%'], []);
  const [isBottomSheetOpen, setBottomSheetOpen] = useState<boolean>(false);
  const bottomSheetNav =
    useRef<NavigationContainerRef<MapBottomSheetTabParamList> | null>(null);

  /**
   * 산책 시작 버튼 press시 이동할 screen을 잠시 test로 설정해뒀습니다.
   * 이제 저희 산책은 테스트입니다.
   */
  const handlePressStart = async () => {
    // navigation.navigate('MapWalk');
    setIsStarting(true);
    await startSaveUserPos(user?.userId as number)
      .then(() => {
        setIsStarting(false);
        navigation.navigate('WalkTest');
      })
      .catch(error => {
        setIsStarting(false);
        deleteSaveUserPos(user?.userId as number);
      });
  };

  const handleBottomSheetOpen = () => bottomSheetRef.current?.expand();
  const handleBottomSheetClose = () => bottomSheetRef.current?.close();

  useEffect(() => {
    Geolocation.getCurrentPosition(
      info => {
        const {latitude, longitude} = info.coords;
        // 초기 좌표값 설정.
        setInitPos({latitude, longitude});
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

  const {data, isLoading} = useQuery({
    queryFn: () => getNearMarker(),
    queryKey: [queryKeys.GET_MARKER],
    refetchInterval: 5000,
  });

  const handleMarkerPress = (
    type: domainType,
    domainId: number,
    coordinate: LatLng,
  ) => {
    // setTrackingMode(false);
    if (type === 'GATHER') {
      bottomSheetNav.current &&
        bottomSheetNav.current.navigate('Gather', {gatherId: domainId});
    } else if (type === 'POST') {
      bottomSheetNav.current &&
        bottomSheetNav.current.navigate('Feed', {
          feedId: domainId,
          coordinate: coordinate,
        });
    }
    handleBottomSheetOpen();
  };

  return (
    <SafeAreaView style={{flex: 1}}>
      {initPos && (
        <MapView
          ref={mapRef}
          style={styles.container}
          provider={PROVIDER_GOOGLE}
          showsUserLocation
          followsUserLocation
          showsMyLocationButton={true}
          customMapStyle={mapStyle}
          initialRegion={{
            ...initPos,
            latitudeDelta: 0.012,
            longitudeDelta: 0.011,
          }}>
          {data &&
            data?.nearby.map((marker, index) => {
              return (
                <CustomMarker
                  key={index}
                  coordinate={{
                    latitude: marker.latitude,
                    longitude: marker.longitude,
                  }}
                  markerId={marker.domainId}
                  lookUpFeed={lookUpFeed}
                  type={marker.type}
                  onPress={() =>
                    handleMarkerPress(marker.type, marker.domainId, {
                      latitude: marker.latitude,
                      longitude: marker.longitude,
                    })
                  }
                />
              );
            })}
        </MapView>
      )}
      <BottomSheet
        index={-1}
        ref={bottomSheetRef}
        snapPoints={snapPoints}
        onChange={index => {
          if (index === -1) {
            setBottomSheetOpen(false);
          } else {
            setBottomSheetOpen(true);
          }
        }}
        enablePanDownToClose={true}>
        <SafeAreaProvider>
          <NavigationContainer independent={true} ref={bottomSheetNav}>
            <BottomSheetQuickStackNavigator />
          </NavigationContainer>
        </SafeAreaProvider>
      </BottomSheet>
      <View
        style={[
          styles.buttonContainer,
          isBottomSheetOpen ? styles.hide : null,
        ]}>
        <Pressable
          disabled={isStarting}
          style={[styles.startButton, isStarting && styles.disabled]}
          onPress={handlePressStart}>
          <MainText>{isStarting ? '산책 준비중입니다' : '산책시작'}</MainText>
        </Pressable>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  hide: {display: 'none'},
  buttonContainer: {
    zIndex: 2,
    position: 'absolute',
    bottom: 0,
    width: Dimensions.get('screen').width,
    backgroundColor: 'transparent',
  },
  bottomSheetContainer: {
    flex: 1,
    alignItems: 'center',
  },
  startButton: {
    width: '100%',
    backgroundColor: colors.primaryColorBlue,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 15,
    borderRadius: 15,
  },
  disabled: {
    backgroundColor: colors.buttonBackground,
  },
});
