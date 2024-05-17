import React from 'react';
import {Image, StyleSheet, View} from 'react-native';
import {MapMarkerProps, Marker} from 'react-native-maps';

/**
 * 경로의 끝을 알리는 Marker입니다
 *
 * @returns FinishMarker
 */
export default function FinishMarker({...props}: MapMarkerProps) {
  return (
    <Marker
      identifier="finish"
      tracksViewChanges={false}
      image={require('@/assets/markers/ping_finish.png')}
      {...props}
    />
  );
}
