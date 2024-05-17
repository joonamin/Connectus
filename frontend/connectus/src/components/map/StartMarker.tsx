import React from 'react';
import {Image, StyleSheet, View} from 'react-native';
import {MapMarkerProps, Marker} from 'react-native-maps';

/**
 * 경로의 시작을 알리는 Marker입니다
 *
 * @returns StartMarker
 */
export default function StartMarker({...props}: MapMarkerProps) {
  return (
    <Marker identifier="start" tracksViewChanges={false} {...props}>
      <View style={styles.container}>
        <Image
          style={styles.image}
          source={require('@/assets/markers/ping_start.png')}
        />
      </View>
    </Marker>
  );
}

const styles = StyleSheet.create({
  container: {
    width: 70,
    aspectRatio: '207 / 250',
    borderRadius: 50,
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    width: '100%',
    height: '100%',
  },
});
