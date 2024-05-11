import {Pressable, SafeAreaView, StyleSheet} from 'react-native';
import React, {useEffect, useRef, useState} from 'react';
import MapView, {
  MapPressEvent,
  Marker,
  PROVIDER_GOOGLE,
  Region,
} from 'react-native-maps';
import Geolocation from '@react-native-community/geolocation';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import useEventPosStore from '@/store/useEventPosStore';
import {StackScreenProps} from '@react-navigation/stack';
import {EventStackParamList} from '@/navigations/stack/EventStackNavigator';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';

type ScreenProps = StackScreenProps<EventStackParamList>;

export default function EventSelectPosScreen({navigation}: ScreenProps) {
  const mapRef = useRef<MapView | null>(null);
  const [currentPos, setCurrentPos] = useState<Region | undefined>(undefined);
  const {position, setAddPos, setDeletePos} = useEventPosStore();

  const setUserLocation = async () => {
    Geolocation.getCurrentPosition(
      info => {
        console.log(info);
        setCurrentPos({
          latitude: info.coords.latitude,
          longitude: info.coords.longitude,
          longitudeDelta: 0.001,
          latitudeDelta: 0.001,
        });
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
  };

  /**
   * map을 press시 해당 위도경도 좌표를 리스트에 추가합니다.
   */
  const handlePressMap = ({nativeEvent}: MapPressEvent) => {
    setAddPos(nativeEvent.coordinate);
  };

  /**
   * 유저를 중앙으로 이동시킵니다.
   */
  const handlePos = () => {
    setUserLocation();
    if (currentPos) {
      mapRef.current?.animateToRegion({...currentPos});
    }
  };

  /**
   * 작성완료시 이전 이벤트 작성 페이지로 이동합니다.
   */
  const handleComplete = () => {
    navigation.goBack();
  };

  useEffect(() => {
    setUserLocation();
  }, []);

  return (
    <SafeAreaView style={styles.flex}>
      <MapView
        ref={mapRef}
        style={styles.flex}
        provider={PROVIDER_GOOGLE}
        showsUserLocation
        initialRegion={currentPos}
        followsUserLocation
        onPress={handlePressMap}
        onMapReady={setUserLocation}>
        {position &&
          position.map((coordinate, index) => {
            return (
              <Marker
                key={index}
                coordinate={coordinate}
                stopPropagation
                onPress={e => setDeletePos(e.nativeEvent.coordinate)}
              />
            );
          })}
      </MapView>
      <Pressable style={styles.locationButton} onPress={handlePos}>
        <MaterialIcons color={colors.background} name="my-location" size={42} />
      </Pressable>
      <Pressable style={styles.submitButton} onPress={handleComplete}>
        <MainText>위치 설정 완료</MainText>
      </Pressable>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  flex: {flex: 1},
  locationButton: {
    position: 'absolute',
    bottom: 90,
    right: 15,
    width: 77,
    height: 77,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.white,
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
  submitButton: {
    position: 'absolute',
    bottom: 15,
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primaryColorBlue,
    borderRadius: 15,
    padding: 15,
  },
});
