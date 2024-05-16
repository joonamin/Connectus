import {updateWalker} from '../walk';
import {queryKeys} from '@/constants';
import queryClient from '../queryClient';
import {useMutation} from '@tanstack/react-query';

/**
 * 따라걷기를 눌렀을때 실행할 hook 지도로 이동시키면서, 따라 걸은사람을 ++
 * @param walkId
 * @param userId
 * @returns
 */
function useMutateUpdateRoute(walkId: number, userId: number) {
  return useMutation({
    mutationFn: () => updateWalker(walkId, userId),
    onSuccess: () => {
      queryClient.invalidateQueries({queryKey: [queryKeys.GET_ROUTE_LIST]});
    },
    onError: () => {
      console.log('error at like route');
    },
  });
}

export default useMutateUpdateRoute;
