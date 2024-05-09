import {QueryClient} from '@tanstack/react-query';

// 요청 실패 시, 재시도하는 옵션을 끄는 과정
// 이곳 저곳에서 사용할 쿼리를 설정해둔 객체라 생각하면 편할 듯 하다
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: false,
    },
    mutations: {
      retry: false,
    },
  },
});

export default queryClient;
