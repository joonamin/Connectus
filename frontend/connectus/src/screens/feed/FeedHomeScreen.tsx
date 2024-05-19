import {
  Alert,
  Button,
  Dimensions,
  FlatList,
  Modal,
  Pressable,
  SafeAreaView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import React, {useEffect, useState} from 'react';
import Carousel from 'react-native-reanimated-carousel';
import FeedPreview from '@/components/feed/FeedPreview';
import colors from '@/constants/colors';
import useModal from '@/hooks/useModal';
import {useInfiniteQuery, useQuery} from '@tanstack/react-query';
import {openPost} from '@/api/post';
import Geolocation from '@react-native-community/geolocation';
import {LatLng} from 'react-native-maps';
import {queryKeys} from '@/constants';
import {getNearWalkRecord} from '@/api/walk';
import queryClient from '@/api/queryClient';
import useAuthStore from '@/store/useAuthStore';
import {StackNavigationProp} from '@react-navigation/stack';
import {FeedStackParamList} from '@/navigations/stack/FeedStackNavigator';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import useLookUpPost from '@/store/useLookUpPost';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import {getUserInfo} from '@/api/user';

/**
 * 피드 메인 페이지에서 오픈할 modal의 타입입니다.
 * open -> 해금용 모달
 * move -> 확인하기용 모달
 */
export type modalType = 'open' | 'move';

type Navigation = CompositeNavigationProp<
  StackNavigationProp<FeedStackParamList>,
  StackNavigationProp<MapStackParamList>
>;

export default function FeedHomeScreen() {
  const carouselWidth = Dimensions.get('window').width;
  const carouselHeight = Dimensions.get('window').height;
  const navigation = useNavigation<Navigation>();

  // 보러가기 버튼을 눌럿을 때, update되어 이동 id를 modal에 전달해줄 state
  const [selectFeed, setSelectFeed] = useState<number | undefined>(undefined);
  // 피드 호출에 전달할 유저 위도 경도 좌표
  const [currentPos, setCurrentPos] = useState<LatLng>();
  const [isRefreshing, setIsRefreshing] = useState(false);

  // axios요청에 사용할 유저 정보
  const {user} = useAuthStore();

  // 모달에 사용할 state
  const [selectModalType, setSelectModalType] = useState<modalType | null>(
    null,
  );
  const [selectFeedId, setSelectFeedId] = useState<number>();
  const [selectFeedPos, setSelectFeedPos] = useState<LatLng>();
  const {setPosition} = useLookUpPost();

  // 보러가기 버튼 클릭시 실행할 update 함수 (조회수 증가함수 호출 필요)
  const handlePressViewButton = (id: number) => {
    setSelectFeed(id);
  };

  const {data, hasNextPage, isFetchingNextPage, fetchNextPage, refetch} =
    useInfiniteQuery({
      queryFn: ({pageParam}) => getNearWalkRecord(pageParam),
      queryKey: [queryKeys.GET_ROUTE_LIST],
      initialPageParam: 0,
      refetchInterval: 3000,
      getNextPageParam: lastPage => {
        if (lastPage.hasNext) {
          return lastPage.pageNum + 1;
        }
        return undefined;
      },
    });

  // 스크롤을 위로 당겼을 때, refetch 진행
  const handleRefresh = async () => {
    queryClient.resetQueries([queryKeys.GET_ROUTE_LIST]);
    refetch();
  };

  // scroll을 아래로 했을때 실행할 함수.
  const handleEndReached = () => {
    if (hasNextPage && !isFetchingNextPage) {
      fetchNextPage();
    }
  };

  const {
    isVisible: isMoveModalVisible,
    show: moveModalShow,
    hide: moveModalHide,
  } = useModal();

  const handleOpenModal = (
    modalType: modalType,
    position: LatLng,
    feedId: number,
  ) => {
    setSelectModalType(modalType);
    setSelectFeedPos(position);
    setSelectFeedId(feedId);
    moveModalShow();
  };
  const {data: userData} = useQuery({
    queryKey: [queryKeys.GET_USER_INFO, user?.userId],
    queryFn: async ({queryKey}) => {
      return (await getUserInfo(user?.userId)).data;
    },
  });
  /**
   * 포인트로 해금하기 버튼을 눌럿을 때, 실행시킬 함수
   * 요청 성공 후, 디테일 페이지로 이동 후 데이터를 피드 디테일에 관한 데이터를 요청합니다.
   */
  const handleOpenFeed = () => {
    if (user) {
      openPost(user.userId, selectFeedId as number)
        .then(() => navigation.navigate('FeedDetail', {feedId: selectFeedId}))
        .catch(() => {
          Alert.alert('포인트가 모자랍니다');
        });
    }
  };

  /**
   * 보러가기 버튼을 눌럿을 때, 해당 마커를 하이라이팅 하고, 특별한 마커로 변환!
   */
  const handleLookUpFeed = () => {
    if (selectFeedPos) {
      setPosition(selectFeedId as number);
      navigation.navigate('MapHome');
    }
  };

  useEffect(() => {
    Geolocation.getCurrentPosition(
      info => {
        setCurrentPos({
          latitude: info.coords.latitude,
          longitude: info.coords.longitude,
        });
      },
      () => {
        console.log('error');
      },
      {
        // 상세 좌표를 요청하는 코드
        enableHighAccuracy: true,
        distanceFilter: 0,
        // interval: 3000,
        // fastestInterval: 2000,
      },
    );
  }, []);

  return (
    <SafeAreaView>
      <FlatList
        data={data?.pages.flatMap(page => page.walksList)}
        refreshing={isRefreshing}
        onRefresh={handleRefresh}
        onEndReached={handleEndReached}
        onEndReachedThreshold={0.5}
        scrollIndicatorInsets={{right: 1}}
        renderItem={({item}) => {
          return (
            <View style={{marginBottom: 30}}>
              <Carousel
                width={carouselWidth}
                height={carouselWidth + 80}
                mode="parallax"
                autoFillData={false}
                panGestureHandlerProps={{
                  activeOffsetX: [-10, 10],
                }}
                data={item.postList}
                renderItem={({item, index}) => {
                  // console.log('================================');
                  // console.log('item', item);
                  // console.log('================================');
                  return <FeedPreview feedId={item} show={handleOpenModal} />;
                }}
              />
            </View>
          );
        }}>
        {/* <Carousel
          width={carouselWidth}
          mode="parallax"
          data={test}
          renderItem={({index}) => (
            <FeedPreview
              isLiked={false}
              likeNumber={12}
              commentNumber={53}
              show={moveModalShow}
            />
          )}
        /> */}
      </FlatList>
      <Modal
        visible={isMoveModalVisible}
        transparent={true}
        animationType="slide">
        <SafeAreaView style={styles.modalBackground} onTouchEnd={moveModalHide}>
          {selectModalType === 'move' && (
            <View style={styles.confirmModal}>
              <Text style={styles.confirmText}>
                해당 방명록의 위치를 확인하시겠습니까?
              </Text>
              <View style={styles.confirmButtonContainer}>
                <Pressable
                  style={styles.confirimButton}
                  onPress={handleLookUpFeed}>
                  <Text style={styles.buttonText}>보러가기</Text>
                </Pressable>
                <Pressable
                  style={styles.confirimButton}
                  onPress={moveModalHide}>
                  <Text style={styles.buttonText}>취소</Text>
                </Pressable>
              </View>
            </View>
          )}
          {selectModalType === 'open' && (
            <View style={styles.confirmModal}>
              <Text style={styles.confirmText}>포인트로 방명록 보기</Text>
              <Text
                style={
                  styles.confirmText
                }>{`보유 포인트 : ${userData?.point}`}</Text>
              <View style={styles.confirmButtonContainer}>
                <Pressable
                  style={styles.confirimButton}
                  onPress={handleOpenFeed}>
                  <Text style={styles.buttonText}>보기</Text>
                </Pressable>
                <Pressable
                  style={styles.confirimButton}
                  onPress={moveModalHide}>
                  <Text style={styles.buttonText}>취소</Text>
                </Pressable>
              </View>
            </View>
          )}
        </SafeAreaView>
      </Modal>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  modalBackground: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  confirmModal: {
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 20,
    padding: 35,
    gap: 10,
    backgroundColor: colors.buttonBackground,
  },
  confirmText: {
    fontFamily: 'GmarketSansTTFLight',
    fontSize: 16,
    color: colors.white,
  },
  confirmButtonContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    gap: 30,
  },
  confirimButton: {
    borderRadius: 8,
    paddingHorizontal: 15,
    paddingVertical: 10,
    backgroundColor: colors.background,
  },
  buttonText: {
    fontFamily: 'GmarketSansTTFMedium',
    color: colors.white,
    fontSize: 16,
  },
});
