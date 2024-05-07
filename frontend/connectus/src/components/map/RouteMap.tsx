import colors from '@/constants/colors';
import React, {useRef} from 'react';
import {Platform, StyleSheet, View} from 'react-native';
import MapView, {LatLng, PROVIDER_GOOGLE, Polyline} from 'react-native-maps';
import StartMarker from '@/components/map/StartMarker';
import FinishMarker from '@/components/map/FinishMarker';

/**
 * RouteMap을 생성할 시 전달할 인자를 지정합니다
 */
export interface RouteMapProps {
  /**
   * 산책 경로
   */
  routes: LatLng[];
}

/**
 * 경로를 지도에 표시합니다
 *
 * @returns RouteMap
 */
export default function RouteMap({routes}: RouteMapProps) {
  // 지도 ref 받아오기
  const map = useRef<MapView>(null);

  // 지도가 준비되었을 시 실행
  const onMapReady = () => {
    // 지도를 적절한 위치에 위치
    if (Platform.OS === 'ios') {
      map.current?.fitToElements();
    } else {
      map.current?.fitToCoordinates(routes, {
        animated: false,
        edgePadding: {
          top: 50,
          bottom: 30,
          left: 30,
          right: 30,
        },
      });
    }
  };

  return (
    <View style={styles.mapContainer}>
      <MapView
        ref={map}
        style={styles.map}
        scrollEnabled={false}
        zoomEnabled={false}
        provider={PROVIDER_GOOGLE}
        onMapReady={onMapReady}>
        {/* 시작, 종료 마커 */}
        <StartMarker coordinate={routes[0]} />
        <FinishMarker coordinate={routes[routes.length - 1]} />
        {/* 경로 line */}
        <Polyline
          coordinates={routes}
          strokeWidth={8}
          strokeColor={colors.dividerColor}
        />
      </MapView>
    </View>
  );
}

const styles = StyleSheet.create({
  mapContainer: {
    aspectRatio: '1 / 1',
    borderRadius: 15,
    overflow: 'hidden',
    alignItems: 'stretch',
  },
  map: {
    flexBasis: '100%',
  },
});
