/**
 * react query key를 관리할 파일입니다.
 */

const queryKeys = {
  GET_GATHER: 'getGather',
  GET_FEED_LIST: 'getFeedList',
  GET_FEED_DETAIL: 'getFeedDetail',
  POST_FEED_COMMENT: 'postFeedComment',
  GET_ROUTE_LIST: 'getRouteList',
  GET_ROUTE_DETAIL: 'getRouteDetail',
  GET_WALKS_BY_USER: 'getWalksByUser',
  GET_MARKER: 'getMarker',
  GET_MARKER_POSITION: 'getMarkerPosition',
  UPDATE_USER_POS: 'updateUserPos',
  GET_USER_INFO: 'getUserInfo',
  GET_USER_COMPLETED_ACHIEVEMENT: 'getUserCompletedAchievement',
} as const;

export {queryKeys};
