import React, {useEffect, useRef, useState} from 'react';
import {
  Dimensions,
  Pressable,
  SafeAreaView,
  StyleSheet,
  View,
} from 'react-native';
import MapView, {LatLng, PROVIDER_GOOGLE} from 'react-native-maps';
import useUserLocation from '../../hooks/useUserLocation';
import MainText from '@/components/text/MainText';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import colors from '@/constants/colors';
import Geolocation from '@react-native-community/geolocation';
import mapStyle from '@/style/mapStyle';
import {startSaveUserPos} from '@/api/spot';
import useAuthStore from '@/store/useAuthStore';

type Navigation = CompositeNavigationProp<
  StackNavigationProp<MapStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

export default function MapHomeScreen() {
  const mapRef = useRef<MapView | null>(null);
  const navigation = useNavigation<Navigation>();
  const [initPos, setInitPos] = useState<LatLng>();
  const [isStarting, setIsStarting] = useState<boolean>(false);

  const {user} = useAuthStore();
  /**
   * 산책 시작 버튼 press시 이동할 screen을 잠시 test로 설정해뒀습니다.
   */
  const handlePressStart = async () => {
    // navigation.navigate('MapWalk');
    setIsStarting(true);
    await startSaveUserPos(user?.userId as number).then(() => {
      setIsStarting(false);
      navigation.navigate('WalkTest');
    });
  };

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
          {}
        </MapView>
      )}

      <View style={styles.buttonContainer}>
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
  buttonContainer: {
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
