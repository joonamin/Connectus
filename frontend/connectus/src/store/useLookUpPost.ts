import {LatLng} from 'react-native-maps';
import {create} from 'zustand';

interface PosStore {
  lookUpFeed: number | null;
  setPosition: (feedId: number) => void;
  clearPosition: () => void;
}

/**
 * 전역적으로 관리할 유저가 보러가기를 한 방명록을 표시합니다.
 */
const useLookUpPost = create<PosStore>(set => ({
  lookUpFeed: null,
  setPosition: (feedId: number) => {
    console.log('setting position', feedId);
    set({lookUpFeed: feedId});
  },
  clearPosition: () => {
    return {lookUpFeed: null};
  },
}));

export default useLookUpPost;
