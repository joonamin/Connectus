import axios from 'axios';
import {axiosInstance} from './axios';

/**
 *  id를 list형태로 서버에 전달해 feed의 상세정보를 요청하는 axios입니다.
 */
const getPostDetailList = async () => {
  const {data} = await axiosInstance.get('/post/mainPostList');
  return data;
};

const getPostDetail = async (postId: number) => {
  const {data} = await axiosInstance.get(`/post/${postId}`);
  return data;
};

const createPostComment = async (postId: number) => {
  const {data} = await axiosInstance.get(`/post/${postId}/comment`);
};
