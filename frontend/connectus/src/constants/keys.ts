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
} as const;

export {queryKeys};
