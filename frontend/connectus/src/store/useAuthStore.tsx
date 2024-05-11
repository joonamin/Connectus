import {create} from 'zustand';

interface AuthStore {
  isLogin: boolean;
  setLogin: () => void;
}

const useAuthStore = create<AuthStore>(set => ({
  isLogin: false,
  setLogin: () => {
    set(state => {
      return {...state, isLogin: true};
    });
  },
}));

export default useAuthStore;
