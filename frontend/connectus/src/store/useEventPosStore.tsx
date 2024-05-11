import {LatLng} from 'react-native-maps';
import {create} from 'zustand';

interface PositionStore {
  position: LatLng[];
  setAddPos: (newPos: LatLng) => void;
  setDeletePos: (pos: LatLng) => void;
}

/**
 * 이벤트 화면에서 함수를 router로 전달하지 못하기때문에 전역적으로 관리를 하는 store를 작성했습니다.
 * @returns position
 * @returns setAddPos
 * @returns setDeletePos
 */
const useEventPosStore = create<PositionStore>(set => ({
  position: [],
  setAddPos: (newPos: LatLng) => {
    set(state => {
      return {...state, position: [...state.position, newPos]};
    });
  },
  setDeletePos: (pos: LatLng) => {
    set(state => {
      const index = state.position.findIndex(
        item =>
          item.latitude === pos.latitude && item.longitude === pos.longitude,
      );
      if (index !== -1) {
        const newPositions = [
          ...state.position.slice(0, index),
          ...state.position.slice(index + 1),
        ];
        return {...state, position: newPositions};
      }
      return state;
    });
  },
}));

export default useEventPosStore;
