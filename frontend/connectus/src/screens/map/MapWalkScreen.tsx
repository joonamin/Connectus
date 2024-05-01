import React, {useEffect, useRef, useState} from 'react';
import {Dimensions, StyleSheet, Text, View} from 'react-native';
import MapView, {PROVIDER_GOOGLE} from 'react-native-maps';
import useUserLocation from '@/hooks/useUserLocation';
import Ionicons from 'react-native-vector-icons/Ionicons';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';
import colors from '@/constants/colors';
import MainText from '@/components/text/MainText';
import useInterval from '@/hooks/useInterval';
import {convertSecondsToTime, formatTime} from '@/utils';

export default function MapWalkScreen() {
  const {userLocation} = useUserLocation();
  const mapRef = useRef<MapView | null>(null);
  const [time, setTime] = useState<number>(0);
  const [indicagteTime, setIndicateTime] = useState<string | null>(null);

  // 1초마다 time을 증가시키기 위해 useInterval에 넣어줄 코드입니다
  const tick = () => {
    setTime(prev => prev + 1);
  };
  useInterval(tick, 1000);

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
        <Ionicons style={styles.menuButton} name="menu" size={42} />
      </View>
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
    alignItems:'center',
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
    position:'absolute',
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
});
