import {create} from 'zustand';
import {LatLng} from 'react-native-maps';

interface RouteStore {
  route: LatLng[];
  setRoute: (route: LatLng[]) => void;
  setDeleteRoute: () => void;
}

/**
 * 유저가 기록공유 스크린에서 따라걷기를 선택시 전역store에 움직였던 루트들을 저장하고
 * 그것을 mapwalk screen에 전달해 다른 색으로 polyline을 그려줍니다
 */
const useRouteStore = create<RouteStore>(set => ({
  route: [],
  setRoute: (route: LatLng[]) => {
    set(() => {
      return {route};
    });
  },
  setDeleteRoute: () => {
    set({route: []});
  },
}));

export default useRouteStore;
