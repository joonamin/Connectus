import useAuthStore from '@/store/useAuthStore';
import axios, {AxiosError, AxiosResponse} from 'axios';

/**
 * 사용자 인증을 제외한 API 요청에 사용되는 axiosInstance입니다
 */
const axiosInstance = axios.create({
  baseURL: 'http://3.38.245.137:8000',
  headers: {
    'Content-Type': 'application/json',
  },
});

// 매 요청마다 Authorization 헤더를 추가합니다
axiosInstance.interceptors.request.use(config => {
  config.headers.Authorization = `Bearer ${
    useAuthStore.getState().accessToken
  }`;
  return config;
});

// 요청 후 401 Unauthorized 응답을 받은 경우 access token을 invalidate
axiosInstance.interceptors.response.use(
  (response: AxiosResponse<any, any>) => response,
  (error: AxiosError<any, any>) => {
    if (error?.response?.status === 401) {
      useAuthStore.getState().invalidate();
    }
    return Promise.reject(error);
  },
);

/**
 * 로그인 및 회원가입 시 사용되는 axiosInstance입니다
 */
const authAxios = axios.create({
  baseURL: 'http://3.38.245.137:8000',
});

export {axiosInstance, authAxios};
