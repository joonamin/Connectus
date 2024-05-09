import axios from 'axios';

/**
 * 모여라에 사용할 axiosInstance
 */
const axiosGather = axios.create({
  baseURL: '1234',
  withCredentials: true,
});

/**
 * 피드작성?post작성에 사용할 axiosInstance
 */
const axiosPost = axios.create({
  baseURL: '1234',
  withCredentials: true,
});

/**
 * 이벤트에 사용할 axiosInstance
 */
const axiosEvent = axios.create({
  baseURL: '1234',
  withCredentials: true,
});

export {axiosGather, axiosPost, axiosEvent};
