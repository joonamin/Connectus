import React from 'react';
import {Image, StyleSheet, Text, View} from 'react-native';
import {LatLng, MyMapMarkerProps, Marker} from 'react-native-maps';

interface CustomMarkerProps extends MyMapMarkerProps {
  coordinate?: LatLng;
  type: number;
}

/**
 * 맵에서 마커들을 표시할 커스텀 마커 컴포넌트 입니다.
 * 아직 백엔드에서 어떤식으로 데이터들을 확실하게 보내줄지 모르지만
 * 타입의 경우에는 number의 형태로 보낸다고 들었습니다.
 * @param {number}type  1 : 이벤트 좌표, 2: 피드 이미지 좌표, 3: 모여라 좌표
 * @param {LatLng|undefined}coordinate 해당 마커의 좌표
 */

export default function CustomMarker({
  coordinate,
  type,
  ...props
}: CustomMarkerProps) {
  const markerView = (
    <View style={styles.container}>
      {type === 1 && (
        <Image style={styles.image} source={require('@/assets/star.png')} />
      )}
      {type === 2 && (
        <Image
          style={styles.image}
          source={require('@/assets/feedImage.png')}
        />
      )}
      {type === 3 && (
        <Image
          style={styles.image}
          source={require('@/assets/gatherImage.png')}
        />
      )}
    </View>
  );

  return coordinate ? (
    <Marker coordinate={coordinate} {...props}>
      {markerView}
    </Marker>
  ) : (
    markerView
  );
}

const styles = StyleSheet.create({
  container: {width: 55, height: 55},
  image: {
    width: '100%',
    height: '100%',
  },
});
