import {LatLng} from 'react-native-maps';
import {create} from 'zustand';

interface PosStore {
  lookUpFeed: number | null;
  lookUpFeedPos: LatLng | null;
  setId: (feedId: number) => void;
  setPos: (pos: LatLng) => void;
  clearPosition: () => void;
}

/**
 * 전역적으로 관리할 유저가 보러가기를 한 방명록을 표시합니다.
 */
const useLookUpPost = create<PosStore>(set => ({
  lookUpFeed: null,
  lookUpFeedPos: null,
  setId: (feedId: number) => {
    console.log('setting id', feedId);
    set(() => ({
      lookUpFeed: feedId,
    }));
  },
  setPos: (pos: LatLng) => {
    console.log('setting position', pos);
    set(() => ({
      lookUpFeedPos: pos,
    }));
  },
  clearPosition: () => {
    console.log('clearing position');
    set(() => ({
      lookUpFeed: null,
      lookUpFeedPos: null,
    }));
  },
}));

export default useLookUpPost;
