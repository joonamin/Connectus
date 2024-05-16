/**
 * react query key를 관리할 파일입니다.
 */

const queryKeys = {
  GET_GATHER: 'getGather',
  GET_FEED_LIST: 'getFeedList',
  GET_FEED_DETAIL: 'getFeedDetail',
  POST_FEED_COMMENT: 'postFeedComment',
  GET_ROUTE_LIST: 'getRouteList',
} as const;

export {queryKeys};
