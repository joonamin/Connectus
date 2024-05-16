import Geolocation from '@react-native-community/geolocation';

/**
 * 현재 위치를 반환할 getPosition친구입니다.
 * 다른거도 많이 반환하지만
 * 받아온데이터.coords.longitude
 * 이런식으로 위도 경도 좌표를 받는데 사용할겁니다
 */
const getPosition = function () {
  return new Promise(function (resolve, reject) {
    Geolocation.getCurrentPosition(resolve, reject, {
      enableHighAccuracy: true,
      distanceFilter: 0,
    });
  });
};

export {getPosition};
