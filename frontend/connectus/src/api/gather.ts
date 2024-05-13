// 모여라에서 사용할 api들을 정리한 파일입니다
import {axiosInstance} from './axios';

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
const gatherStart = async (body: gather): Promise<any> => {
  try {
    const {data} = await axiosInstance.post('/gather', body);
    console.log(data);
    return data;
  } catch (error: any) {
    if (error.response) {
      console.error('에러 응답:', error.response.data);
      console.error('에러 코드:', error.response.status);
    } else if (error.request) {
      console.error('요청을 보냈지만 응답을 받지 못했습니다.');
    } else {
      console.error('요청을 설정하는 중에 에러가 발생했습니다:', error.message);
    }
  }
};

/**
 * 모여라 상세정보를 위한 axios요청
 * @param {number} gatherId
 * @returns
 */
const gatherDetail = async (gatherId: number) => {
  const {data} = await axiosInstance.get(`/gather/${gatherId}`);
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
  const {data} = await axiosInstance.patch('/gather/close', body);
  return data;
};

/**
 * 모여라를 참여 희망할 때, 실행할 axios
 * @param body
 * @returns
 */
const gatherJoin = async (body: requestGather): Promise<string[]> => {
  const {data} = await axiosInstance.patch('/gather/want_join', body);
  return data;
};

/**
 * 모여라에 도착했을 때, 실행할 axios
 * @param body
 * @returns
 */
const gatherReach = async (body: requestGather): Promise<string[]> => {
  const {data} = await axiosInstance.patch('/gather/join', body);
  return data;
};

export {gatherStart, gatherDetail, gatherDone, gatherJoin, gatherReach};
