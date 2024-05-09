// 모여라에서 사용할 api들을 정리한 파일입니다
import {axiosGather} from './axios';

type gather = {
  hostId: number;
  content: String;
  maxJoiner: number;
  endTime: String;
};

/**
 * 모여라를 시작할 때, 호출할 axios
 * @param {gather} body
 * @returns {number} 모여라에서 반환된 id를 반환
 */
const gatherStart = async (body: gather) => {
  const {data} = await axiosGather.post('/gather', body);
  return data;
};

/**
 * 모여라 상세정보를 위한 axios요청
 * @param {number} gatherId
 * @returns
 */
const gatherDetail = async (gatherId: number) => {
  const {data} = await axiosGather.get(`/gather/${gatherId}`);
  return data;
};

type requestGather = {
  userId: number;
  gatherId: number;
};

/**
 * 모여라를 조기종료할 떄, 실행할 axios
 * @param {{userId: number,gatherId: number; }} body
 * @returns
 */
const gatherDone = async (body: requestGather): Promise<string[]> => {
  const {data} = await axiosGather.patch('/gather/close', body);
  return data;
};

/**
 * 모여라를 참여 희망할 때, 실행할 axios
 * @param body
 * @returns
 */
const gatherJoin = async (body: requestGather): Promise<string[]> => {
  const {data} = await axiosGather.patch('/gather/want_join', body);
  return data;
};

/**
 * 모여라에 도착했을 때, 실행할 axios
 * @param body
 * @returns
 */
const gatherReach = async (body: requestGather): Promise<string[]> => {
  const {data} = await axiosGather.patch('/gather/join', body);
  return data;
};

export {gatherStart, gatherDetail, gatherDone, gatherJoin, gatherReach};
