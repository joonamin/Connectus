import React, {useEffect, useMemo, useRef, useState} from 'react';
import {Dimensions, Pressable, StyleSheet, Text, View} from 'react-native';
import {NavigationContainer, useNavigation} from '@react-navigation/native';
import MapView, {PROVIDER_GOOGLE} from 'react-native-maps';
import Ionicons from 'react-native-vector-icons/Ionicons';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';
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
import MapBottomSheetNavigator from '@/navigations/Tabs/MapBottomSheetNavigator';
import EventIndicator from '@/components/my/EventIndicator';
import {StackNavigationProp} from '@react-navigation/stack';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import QuickMenuHomeScreen from './BottomSheet/QuickMenuHomeScreen';

type Navigation = StackNavigationProp<MapStackParamList>;

export default function MapWalkScreen() {
  const navigation = useNavigation<Navigation>();

  const {userLocation} = useUserLocation();
  const mapRef = useRef<MapView | null>(null);
  const bottomSheetRef = useRef<BottomSheet>(null);
  const [time, setTime] = useState<number>(0);
  const [indicagteTime, setIndicateTime] = useState<string | null>(null);
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
  useInterval(tick, 1000);

  // 바텀시트를 닫고 , 메인스크린을 옮기며, 지금까지의 정보를 props로 넘겨줘야합니다.
  const navigateToResultScreen = () => {
    handleBottomSheetClose();
    navigation.navigate('MapResult');
  };
  // 1초마다 하단 인디케이터의 시간을 업데이트해주는 useEffect 함수입니다.
  useEffect(() => {
    const {seconds, minutes, hours} = convertSecondsToTime(time);
    setIndicateTime(formatTime(hours, minutes, seconds));
  }, [time]);

  return (
    <>
      <MapView
        ref={mapRef}
        style={styles.container}
        provider={PROVIDER_GOOGLE}
        showsUserLocation
        followsUserLocation
        showsMyLocationButton={true}
        region={{
          ...userLocation,
          latitudeDelta: 0.009,
          longitudeDelta: 0.009,
        }}>
        {}
      </MapView>
      <View style={[styles.eventIndicator, {top: inset.top || 20}]}>
        <EventIndicator />
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
          <NavigationContainer independent={true}>
            <MapBottomSheetNavigator
              navigateToResultScreen={navigateToResultScreen}
            />
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
});
