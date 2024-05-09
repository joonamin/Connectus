import {LatLng} from 'react-native-maps';

function toRadians(degrees: number) {
  return degrees * (Math.PI / 180);
}

/**
 * 두 지점의 위도경도 좌표를 통해 거리를 계산하는 함수입니다.
 * @param pos1 type : LatLng
 * @param pos2 type : LatLng
 * @returns distance(number)
 */

function getDistance(pos1: LatLng, pos2: LatLng) {
  const R = 6371; // 지구의 반지름 (km)
  const dLat = toRadians(pos2.latitude - pos1.latitude);
  const dLon = toRadians(pos2.longitude - pos1.longitude);
  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(toRadians(pos1.latitude)) *
      Math.cos(toRadians(pos2.latitude)) *
      Math.sin(dLon / 2) *
      Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const distance = R * c;

  return distance;
}

export {getDistance};
