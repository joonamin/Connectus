// 산책에서 사용할 api들을 정리한 파일입니다
import {LatLng} from 'react-native-maps';
import {axiosInstance} from './axios';

/**
 * 산책 공유 페이지에서 경로를 좋아요를 할 때 호출할 api입니다.
 */
const routeLike = async (walkId: number, userId: number) => {
  const body = {walkId, userId};
  const {data} = await axiosInstance.put('/walk/route-like', body);
  return data;
};

/**
 * @todo post에 대한 타이핑 지정 필요
 */
type createRoutetype = {
  userId: number;
  title: string;
  route: LatLng[];
  walkTime: number;
  walkDistance: number;
  //   postList: post[];
  // 아니 이거 뭐야 무서ㅜ어
  completedAchievement: string[];
  participateEvent: number;
};

/**
 * 산책을 작성할때 (마지막에 산책 완료를 하면서 저장버튼 터치 시)호출할 api입니다
 */
const createRoute = async (body: createRoutetype) => {
  const {data} = await axiosInstance.post('/walk', body);
  return data;
};

/**
 *
 * @param walkId {number} 조회할 산책의 id값을 param으로 전달합니다.
 */
const getRouteDetail = async (walkId: number) => {
  const {data} = await axiosInstance.get(`/walk/${walkId}`);
  return data;
};

/**
 * 작성자를 기준으로 산책 기록을 상세하게 조회합니다.
 * @param userId 해당 유저의 아이디를 받아옵니다.
 * @returns
 */
const getUserRoute = async (userId: string) => {
  const {data} = await axiosInstance.get(`walk/user/${userId}`);
  return data;
};

/**
 * 이번 산책에서 달성한 업적 목록들을 반환할 axios요청입니다.
 */
const getCompletedAchievement = async () => {
  const {data} = await axiosInstance.get('/walk/end-walk');
  return data;
};

/**
 * 해당 산책 기록을 공유하기위해 호출하는 axios요청입니다.
 */
const shareRoute = async (walkId: number, userId: number) => {
  const body = {walkId, userId};
  const {data} = await axiosInstance.patch('/walk/route-share', body);
  return data;
};

/**
 * 공유한 산책 경로의 공유를 취소하는 코드입니다.
 * @param walkId
 * @param userId
 * @returns
 */
const cancelShareRoute = async (walkId: number, userId: number) => {
  const body = {walkId, userId};
  const {data} = await axiosInstance.patch('/walk/route-protect', body);
  return data;
};

/**
 * 따라걷기 버튼을 눌럿을때, 해당 post를 같이 걸은 사람의 수를 증가시켜줍니다.
 * @param walkId 산책 기록 id
 * @param userId 요청을 보내는 유저 id
 * @returns
 */
const updateWalker = async (walkId: number, userId: number) => {
  const body = {walkId, userId};
  const {data} = await axiosInstance.patch('/walk/route-track', body);
  return data;
};

export {
  routeLike,
  getRouteDetail,
  createRoute,
  getUserRoute,
  getCompletedAchievement,
  shareRoute,
  updateWalker,
  cancelShareRoute,
};
