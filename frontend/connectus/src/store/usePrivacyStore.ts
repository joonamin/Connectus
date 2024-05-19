import {create} from 'zustand';
import {persist} from 'zustand/middleware';

/**
 * 개인 정보 관련 설정을 저장하는 store입니다
 */
export interface PrivacyStore {
  /**
   * 위치 공유 여부
   */
  locationSharing: boolean;

  /**
   * 위치 공유 여부를 설정합니다
   *
   * @param newLocationSharing 위치 공유 설정 값
   */
  setLocationSharing: (newLocationSharing?: boolean) => void;
}

/**
 * 개인 정보 관련 설정을 저장하는 store입니다
 */
const usePrivacyStore = create<PrivacyStore>()(
  persist(
    (set, get) => ({
      locationSharing: false,
      setLocationSharing(newLocationSharing) {
        set({
          locationSharing:
            typeof newLocationSharing !== 'undefined'
              ? newLocationSharing
              : !get().locationSharing,
        });
      },
    }),
    {
      name: 'privacyStore',
    },
  ),
);

export default usePrivacyStore;
