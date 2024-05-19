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
const getNearMarker = async (): Promise<spotListType> => {
  const post = await getPosition();
  const body = {
    latitude: post.coords.latitude,
    longitude: post.coords.longitude,
  };
  console.log(body);
  try {
    const {data} = await axiosInstance.post('/spot/findNearby', body);
    return data;
  } catch (error) {
    throw error;
  }
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

/**
 * 산책 시작과 동시에 유저의 위치 정보를 저장합니다.
 */
const startSaveUserPos = async (userId: number) => {
  const pos = await getPosition();
  const body = {
    userId: userId,
    latitude: pos.coords.latitude,
    longitude: pos.coords.longitude,
  };
  try {
    const {data} = await axiosInstance.post('/user/insert-spot', body);
    return data;
  } catch (error) {
    throw error;
  }
};

/**
 * 산책 종료 시 , 유저의 저장 정보를 삭제합니다
 */
const deleteSaveUserPos = async (userId: number) => {
  const {data} = await axiosInstance.post(`/user/delete-spot/${userId}`);
  console.log('end walk', userId);
  return data;
};

const updateSaveUserPos = async (userId: number) => {
  const pos = await getPosition();
  const body = {
    userId: userId,
    latitude: pos.coords.latitude,
    longitude: pos.coords.longitude,
  };
  console.log('position updating', body);
  try {
    const {data} = await axiosInstance.post('/user/update-spot', body);
    return data;
  } catch (error) {
    deleteSaveUserPos(userId);
    throw error;
  }
};

export {
  getNearMarker,
  getDetailSpotList,
  setSpot,
  startSaveUserPos,
  deleteSaveUserPos,
  updateSaveUserPos,
};
