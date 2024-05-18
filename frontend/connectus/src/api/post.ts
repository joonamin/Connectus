import {postDetail} from '@/types';
import {axiosInstance} from './axios';
import {getDistance, getPosition} from '@/utils';
import {LatLng} from 'react-native-maps';
/**
 *  id를 list형태로 서버에 전달해 feed의 상세정보를 요청하는 axios입니다.
 */
const getPostDetailList = async (id: number[]) => {
  const {data} = await axiosInstance.get('/post/mainPostList', {params: id});
  return data;
};

const getFeedDetail = async (walkId: number) => {
  const {data} = await axiosInstance.get(`/post/feed/${walkId}`, {
    params: walkId,
  });
  return data;
};

interface getPostDetailBody {
  userId: number;
  distance: number;
}

/**
 * 방명록의 상세정보를 요청하는 axios 요청입니다.
 * 요청중 타이핑 에러가나면 any로 수정해서 사용합시다...
 * @param postId : 요청을 보낼 포스트 아이디
 * @param body : postId, userId, distance
 */
const getPostDetail = async (
  postId: number,
  userId: number,
): Promise<postDetail> => {
  const position = await getPostSpot(postId);
  const postPos = {latitude: position.latitude, longitude: position.longitude};
  const pos = await getPosition();

  const userPos = {
    latitude: pos.coords.latitude,
    longitude: pos.coords.longitude,
  };
  const dis = getDistance(postPos, userPos);
  try {
    const {data} = await axiosInstance.get(
      `/post/${postId}?userId=${userId}&distance=${dis}`,
    );
    return data;
  } catch (error) {
    throw error;
  }
};

/**
 * map에서 마커의 좌표를 받아와서 데이터를 반환합니다.
 */
const getMapPostDetail = async (
  postId: number,
  userId: number,
  coordinate: LatLng,
): Promise<postDetail> => {
  const pos = await getPosition();

  const userPos = {
    latitude: pos.coords.latitude,
    longitude: pos.coords.longitude,
  };

  const distance = getDistance(userPos, coordinate);
  console.log('이건 거리다아아아앙', postId, userId, distance);
  try {
    const {data} = await axiosInstance.get(
      `/post/${postId}?userId=${userId}&distance=${distance}`,
    );
    console.log(data);
    return data;
  } catch (error) {
    throw error;
  }
};

interface createPostCommentParams {
  content: String;
  authorId: number;
}

/**
 * feed에 댓글을 남기기위한 axios 요청입니다.
 * api명세서를 기반으로 type을 남겨두었으나 dto를 넣는지 확실하지 않아 일단 작성해두고 추후 확인하겠습니다.
 * @param postId 댓글을 남길 post의 id
 * @param body
 * @returns
 */
const createPostComment = async (
  postId: number,
  body: createPostCommentParams,
): Promise<any> => {
  const {data} = await axiosInstance.post(`/post/${postId}/comment`, body);
  console.log(body);
  return data;
};

interface reponseGedFeedList {
  content: {
    walkId: number;
    authorId: number;
    authorName: String;
    postList: [
      {
        postId: number;
        authorId: number;
        authorName: String;
        imageUrl: String;
        countCount: number;
        updatedAt: String;
        inRange: boolean;
      },
    ];
  };
  hasNext: boolean;
  pageNum: number;
}
/**
 * 하단바의 피드스크린에서 피드를 내려받을 axios request입니다.
 * @param params : longitude latitude pageNum
 * pageNum을 1씩 증가시키면서 요청을 이어나갑니다.
 */
const getFeedList = async (
  longitude: number,
  latitude: number,
  pageNum = 0,
): Promise<reponseGedFeedList[]> => {
  const {data} = await axiosInstance.get(
    `/post/feed/main?longitude=${longitude}&latitude=${latitude}&pageNum=${pageNum}`,
  );
  return data;
};

type postFeedLikeParmas = {
  userId: number;
  domainId: number;
  type: 'POST' | 'ROUTE';
};

const postFeedLike = async (body: postFeedLikeParmas) => {
  const {data} = await axiosInstance.post('/likes/insert', body);
  return data;
};

type getSpotType = {
  postId: number;
  latitude: number;
  longitude: number;
};

const getPostSpot = async (postId: number): Promise<getSpotType> => {
  try {
    const {data} = await axiosInstance.get(`/post/${postId}/spot`);
    return data;
  } catch (error) {
    throw error;
  }
};

type openPostResponse = {
  userId: number;
  currentPoint: number;
};
/**
 * 포인트를 사용해서 피드리스트에서 방명록을 열람 가능한 상태로 바꿔줍니다.
 */
const openPost = async (
  userId: number,
  postId: number,
): Promise<openPostResponse> => {
  const body = {
    userId,
    postId,
  };
  try {
    const {data} = await axiosInstance.post('/post/open', body);
    console.log('open post data', data);
    return data;
  } catch (error) {
    console.log('open post error', error);
    throw error;
  }
};

export {
  getPostDetailList,
  getPostDetail,
  createPostComment,
  getFeedList,
  getFeedDetail,
  postFeedLike,
  getMapPostDetail,
  getPostSpot,
  openPost,
};
