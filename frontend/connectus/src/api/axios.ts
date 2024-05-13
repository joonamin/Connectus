import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://3.38.245.137:8000',
  withCredentials: true,
});

/**
 * 로그인 및 회원가입 시 사용되는 axiosInstance입니다
 */
const authAxios = axios.create({
  baseURL: 'http://3.38.245.137:8000',
});

export {axiosInstance, authAxios};
