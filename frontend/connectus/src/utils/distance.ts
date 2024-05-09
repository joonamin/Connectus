import {LatLng} from 'react-native-maps';

function toRadians(degrees: number) {
  return degrees * (Math.PI / 180);
}

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
