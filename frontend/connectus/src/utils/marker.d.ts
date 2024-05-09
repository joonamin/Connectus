import {LatLng, MapMarkerProps} from 'react-native-maps';

/**
 * 커스텀 마커를 사용하기 위해 기존 JavaScript로 만들어진 서드파티 모듈들을
 * TypeScript 환경에서도 사용할수 있도록 했습니다.
 */

declare module 'react-native-maps' {
  export interface MyMapMarkerProps extends MapMarkerProps {
    coordinate?: LatLng;
  }
}
