// axios 요청으로 받아올 데이터에 대한 타입을 정의한 파일입니다.

import {Image} from 'react-native-image-crop-picker';
import {LatLng} from 'react-native-maps';

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

type domainType = 'GATHER' | 'POST' | 'USER' | 'EVENT';

interface spotType {
  id: number;
  longitude: number;
  latitude: number;
  type: domainType;
  domainId: number;
  cretedAt: string;
  updatedAt: string;
}

interface spotListType {
  nearby: spotType[];
  distance: null | number;
}

interface Walk {
  walkId: number;
  userId: number;
  title: string;
  route: LatLng[];
  walkTime: number;
  walkDistance: number;
  likeUsers: number[];
  postList: number[];
  completedAchievement: string[];
  participateEvent: number;
  trackingUsers: number[];
  imageUrl: string;
  updatedAt: Date;
  public: boolean;
}

export type {
  postDetail,
  comment,
  postType,
  spotType,
  spotListType,
  Walk,
  domainType,
};
