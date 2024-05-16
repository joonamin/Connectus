// axios 요청으로 받아올 데이터에 대한 타입을 정의한 파일입니다.

import {Image} from 'react-native-image-crop-picker';

interface comment {
  commentId: number;
  content: string;
  authorId: number;
  authorName: string;
  createdAt: string;
}

interface postDetail {
  postId: number;
  imageUrl: string;
  content: string;
  authorId: number;
  authorName: string;
  likeCount: number;
  commentList: comment[];
  commentCount: number;
  updatedAt: string;
  inRange: boolean;
  like: boolean;
}

interface postType {
  authorId: number;
  content: String;
  image: Image;
}

interface spotType {
  id: number;
  longitude: number;
  latitude: number;
  type: String;
  domainId: number;
  cretedAt: String;
  updatedAt: String;
}

interface spotListType {
  nearby: spotType[];
  distance: null | number;
}

export type {postDetail, comment, postType, spotType, spotListType};
