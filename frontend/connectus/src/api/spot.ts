/**
 * 위치관련 요청들을 조회할 요청들을 정리한 파일입니다
 */

import {spotListType} from '@/types';
import {axiosInstance} from './axios';
import {getPosition} from '@/utils';

/**
 * 주변에 있는 마커들을 조회합니다.
 * @returns {spotListType}
 */
const getNearMarker = async () => {
  const post = await getPosition();
  const body = {
    longitude: post.coords.longitude,
    latitude: post.coords.latitude,
  };
  const {data} = await axiosInstance.post<spotListType>(
    '/spot/findNearby',
    body,
  );
  return data;
};

const getDetailSpotList = async (spotIdList: number[]) => {
  const body = {spotIdList: spotIdList};
  const {data} = await axiosInstance.post('/spot/get', body);
  return data;
};

type spotInfo = {
  domainId: number;
  latitude: number;
  longitude: number;
  type: String;
};

type setSpotRequestType = {
  spotList: spotInfo[];
};

/**
 * 해당 타입의 마커를 등록하는? 용도로 사용합니다.
 */
const setSpot = async (spotIdList: setSpotRequestType) => {
  const body = {spotIdList: spotIdList};
  const {data} = await axiosInstance.post('/spot/insert', body);
  return data;
};

export {getNearMarker, getDetailSpotList, setSpot};