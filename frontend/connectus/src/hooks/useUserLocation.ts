import Geolocation from '@react-native-community/geolocation';
import {useEffect, useState} from 'react';
import {LatLng} from 'react-native-maps';
import useAppState from './useAppState';

/**
 * 유저의 위치에 접근해 위도, 경도 좌표를 반환합니다
 * @returns {latitude, longitude}
 */
function useUserLocation() {
  // null 값을 방지하기 위해 미리 녹산동의 주소를 입력했습니다.
  const [userLocation, setUserLocation] = useState<LatLng>({
    latitude: 35.0846,
    longitude: 128.8503,
  });
  const [isUserLocationError, setIsUserLocationError] = useState(false);
  const {isComback} = useAppState();

  useEffect(() => {
    Geolocation.getCurrentPosition(
      info => {
        const {latitude, longitude} = info.coords;
        setUserLocation({latitude, longitude});
        setIsUserLocationError(false);
      },
      // 에러가 발생했을 시 작동할 코드.
      () => {
        setIsUserLocationError(true);
      },
      {
        // 상세 좌표를 요청하는 코드
        enableHighAccuracy: true,
      },
    );
  }, [isComback]);
  return {userLocation, isUserLocationError};
}

export default useUserLocation;
