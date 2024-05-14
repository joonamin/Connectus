import {jwtDecode} from 'jwt-decode';
import EncryptedStorage from 'react-native-encrypted-storage';
import {create} from 'zustand';
import {createJSONStorage, persist} from 'zustand/middleware';

/**
 * 사용자 인증 정보를 저장하는 store입니다
 */
export interface AuthStore {
  /**
   * Access token
   */
  accessToken?: string;
  /**
   * 디코드한 access token 데이터
   */
  accessTokenData?: AuthTokenData;

  /**
   * Token으로부터 불러온 사용자 정보
   */
  user?: AuthData;

  /**
   * Access token을 설정합니다
   *
   * @param {string | undefined} accessToken 설정할 access token
   */
  setAccessToken: (accessToken?: string) => void;
  /**
   * Access token을 초기화합니다
   */
  invalidate: () => void;
}

/**
 * 사용자 정보를 지정합니다
 */
export interface AuthData {
  /**
   * 사용자 이메일
   */
  email: string;
  /**
   * Token 발행일시
   */
  issuedAt: string;
  /**
   * Token을 발행한 서비스
   */
  issuer: string;
  /**
   * 사용자 ID
   */
  userId: number;
}

/**
 * 응답받는 JWT token의 데이터 형식입니다
 */
export interface AuthTokenData {
  exp: number;
  iat: number;
  iss: string;
  payload: AuthData;
}

/**
 * 사용자 인증 store입니다
 */
const useAuthStore = create<AuthStore>()(
  persist(
    (set, get) => ({
      setAccessToken: (accessToken?: string) => {
        // JWT decoding 후 데이터 저장
        const accessTokenData = accessToken
          ? jwtDecode<AuthTokenData>(accessToken)
          : undefined;

        set({accessToken, accessTokenData, user: accessTokenData?.payload});
      },
      invalidate: () => {
        get().setAccessToken();
      },
    }),
    {
      name: 'authStore',
      storage: createJSONStorage(() => EncryptedStorage),
    },
  ),
);

export default useAuthStore;
