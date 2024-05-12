import axios from 'axios';

/**
 * @todo (중요)현재 api의 baseURL이 어떻게 분리될 지 몰라 각각의 서비스별로 axiosInstance를 작성했습니다.
 * api 명세와 요청보낼 주소가 확정이되면 수정이 필요합니다!!!!!
 */

/**
 * 모여라에 사용할 axiosInstance
 */
const axiosGather = axios.create({
  baseURL: '1234',
  withCredentials: true,
});

/**
 * 산책에서 사용할 axiosInstance
 */
const axiosWalk = axios.create({
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

export {axiosGather, axiosPost, axiosEvent, axiosWalk};
