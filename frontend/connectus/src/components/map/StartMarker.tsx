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
    <Marker
      identifier="start"
      tracksViewChanges={false}
      image={require('@/assets/markers/ping_start.png')}
      {...props}
    />
  );
}
