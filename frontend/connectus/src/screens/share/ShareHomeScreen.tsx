import {
  FlatList,
  Modal,
  Pressable,
  SafeAreaView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import React, {useEffect, useState} from 'react';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import {LatLng} from 'react-native-maps';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import SharePost from '@/components/share/SharePost';
import colors from '@/constants/colors';
import {ShareStackParamList} from '@/navigations/stack/ShareStackNavigator';
import useRouteStore from '@/store/useRouteStore';
import MainText from '@/components/text/MainText';
import {StackNavigationProp} from '@react-navigation/stack';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import {getNearWalkRecord} from '@/api/walk';
import {useInfiniteQuery} from '@tanstack/react-query';
import {queryKeys} from '@/constants';
import Geolocation from '@react-native-community/geolocation';
import useMutateUpdateRoute from '@/api/queries/useMutateUpdateRoute';
import useAuthStore from '@/store/useAuthStore';

type Navigation = CompositeNavigationProp<
  StackNavigationProp<ShareStackParamList>,
  StackNavigationProp<MapStackParamList>
>;

export default function ShareHomeScreen() {
  const navigation = useNavigation<Navigation>();
  // 모달을 컨트롤 할 state
  const [isVisible, setIsVisible] = useState<boolean>(false);
  // routeStore에서 선택한 방명록에 대한 데이터를 관리하고 저장 선택 삭제함.
  const {setRoute, setDeleteRoute} = useRouteStore();
  // refresh 중에 다시 요청을 보내는것을 막기위한 state
  const [isRefreshing, setIsRefreshing] = useState(false);
  // 따라가기 숫자를 늘리는 axios호출을 위해 사용할 walkId state
  const [selectedWalkId, setSelectedWalkId] = useState<number | undefined>(
    undefined,
  );
  // 유저정보 요청
  const {user} = useAuthStore();

  // const [currentPos, setCurrentPos] = useState<LatLng>();
  // const [posLoading, setPosLoading] = useState<boolean>(false);

  const {
    data,
    hasNextPage,
    isFetching,
    isFetchingNextPage,
    fetchNextPage,
    refetch,
  } = useInfiniteQuery({
    queryFn: ({pageParam}) => getNearWalkRecord(pageParam),
    queryKey: [queryKeys.GET_ROUTE_LIST],
    initialPageParam: 0,
    getNextPageParam: lastPage => {
      if (lastPage.hasNext) {
        return lastPage.pageNum + 1;
      }
      return undefined;
    },
  });

  /**
   * @todo 작동확인
   */
  const updateWalker = useMutateUpdateRoute(
    selectedWalkId as number,
    user?.userId as number,
  );

  /**
   * 하위 스크린에 모달을 여는 함수를 전달함과 동시에 모달을 열 때
   * 해당 스크린이 가진 route(산책 루트 경로)를 전역으로 설정합니다.
   */
  const handleModalOpen = (route: LatLng[], walkId: number) => {
    setIsVisible(true);
    setDeleteRoute();
    setRoute(route);
    setSelectedWalkId(walkId);
  };

  // 모달을 닫기위한 코드
  const handleModalClose = () => {
    setIsVisible(false);
  };

  // 화면을 이동시키고 map에 poly라인 설정
  const handleFollowApprove = () => {
    setIsVisible(false);
    updateWalker.mutate();
    navigation.navigate('WalkTest');
  };

  // store에 저장된 루트를 삭제하고 modal창을 close
  const handleFollowDeny = () => {
    setIsVisible(false);
    setDeleteRoute();
  };

  const handlePressShare = () => {
    navigation.navigate('ShareRecord');
  };

  // 스크롤을 위로 당겼을 때, refetch 진행
  const handleRefresh = async () => {
    setIsRefreshing(true);
    await refetch;
    setIsRefreshing(false);
  };

  // scroll을 아래로 했을때 실행할 함수.
  const handleEndReached = () => {
    if (hasNextPage && !isFetchingNextPage) {
      fetchNextPage();
    }
    fetchNextPage();
  };

  // const getPosition = function () {
  //   return new Promise(function (resolve, reject) {
  //     Geolocation.getCurrentPosition(resolve, reject, {
  //       enableHighAccuracy: true,
  //       distanceFilter: 0,
  //     });
  //   });
  // };

  // useEffect(() => {
  //   const setLocation = async () => {
  //     setPosLoading(true);
  //     const pos = await getPosition();
  //     setCurrentPos({longitude: pos.longitude, latitude: pos.latitude});
  //     setPosLoading(false);
  //   };
  //   setLocation();
  // }, []);

  if (isFetching) {
    return <Text>로딩중입니다.</Text>;
  }

  return (
    <>
      <SafeAreaView style={styles.mainContainer}>
        <FlatList
          data={data?.pages.flatMap(page => page.walksList)}
          refreshing={isRefreshing}
          onRefresh={handleRefresh}
          onEndReached={handleEndReached}
          onEndReachedThreshold={0.5}
          scrollIndicatorInsets={{right: 1}}
          renderItem={({item}) => (
            <SharePost
              walkId={item.walkId}
              title={item.title}
              route={item.route}
              walkTime={item.walkTime}
              walkDistance={item.walkDistance}
              likeUsers={item.likeUsers}
              postList={item.postList}
              trackingUsers={item.trackingUsers}
              imageUrl={item.imageUrl}
              updatedAt={item.updatedAt}
              modalOpen={handleModalOpen}
            />
          )}
        />
        <Pressable
          style={styles.shareButtonContainer}
          onPress={handlePressShare}>
          <MaterialCommunityIcons
            name="book-plus"
            color={colors.white}
            size={42}
          />
        </Pressable>
      </SafeAreaView>
      <Modal visible={isVisible} transparent={true} animationType="slide">
        <SafeAreaView
          style={styles.modalContainer}
          onTouchEnd={handleModalClose}>
          <View style={styles.modalView} onTouchEnd={e => e.stopPropagation()}>
            <MainText>해당루트를 걸어보시겠어요?</MainText>
            <View style={styles.modalButtonContainer}>
              <Pressable
                style={styles.modalButton}
                onPress={handleFollowApprove}>
                <MainText>지도로 이동</MainText>
              </Pressable>
              <Pressable style={styles.modalButton} onPress={handleFollowDeny}>
                <MainText>취소</MainText>
              </Pressable>
            </View>
          </View>
        </SafeAreaView>
      </Modal>
    </>
  );
}

const styles = StyleSheet.create({
  mainContainer: {flex: 1, gap: 15},
  shareButtonContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    position: 'absolute',
    width: 72,
    height: 72,
    right: 15,
    bottom: 15,
    backgroundColor: colors.primaryColorBlue,
    borderRadius: 50,
  },
  modalContainer: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalView: {
    width: '90%',
    height: 200,
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
    gap: 15,
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalButtonContainer: {
    flexDirection: 'row',
    gap: 15,
  },
  modalButton: {
    padding: 15,
    borderRadius: 8,
    backgroundColor: colors.background,
    justifyContent: 'center',
    alignItems: 'center',
  },
});
