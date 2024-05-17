import {
  Dimensions,
  Image,
  Pressable,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import React, {useEffect, useState} from 'react';
import colors from '@/constants/colors';
import Ionicons from 'react-native-vector-icons/Ionicons';
import MainText from '../text/MainText';
import {LatLng} from 'react-native-maps';
import {getRouteDetail, routeLike} from '@/api/walk';
import useAuthStore from '@/store/useAuthStore';
import queryClient from '@/api/queryClient';
import {queryKeys} from '@/constants';
import {useMutation, useQuery} from '@tanstack/react-query';
import {dateTimeToString} from '@/utils';

type Props = {
  walkId: number;
  modalOpen: (route: LatLng[], walkId: number) => void;
};

/**
 * share 스크린에서 무한스크롤을 통해 렌더링할 컴포넌트입니다.
 * @todo 추후 기록이 어떻게 전달되는지에 따라서 안쪽의 state를 수정해야합니다
 */
export default function SharePost({walkId, modalOpen}: Props) {
  const [viewIsLiked, setViewIsLiked] = useState(false);
  const [trigger, setTrigger] = useState(0);
  const {user} = useAuthStore();

  const {data, isLoading} = useQuery({
    queryFn: () => getRouteDetail(walkId),
    queryKey: [queryKeys.GET_ROUTE_DETAIL, walkId],
  });

  /**
   * 라우트를 좋아요할 때 사용할 mutation함수
   * @todo 작동확인
   */
  const like = useMutation({
    mutationFn: () => routeLike(walkId, user?.userId as number),
    onSuccess: () => {
      console.log('요청 성공');
      queryClient.invalidateQueries({
        queryKey: [queryKeys.GET_ROUTE_DETAIL, walkId],
      });
      setViewIsLiked(true);
    },
    onError: () => {
      console.log('에러');
    },
  });

  useEffect(() => {
    if (data?.likeUsers?.includes(user?.userId as number)) {
      setViewIsLiked(true);
    } else {
      setViewIsLiked(false);
    }
  }, []);

  /**
   * @todo route like 인수 변경
   */
  const handlePressLikeButton = () => {
    like.mutate();
    setTrigger(prev => (prev += 1));
  };

  if (isLoading) {
    return <></>;
  }

  return (
    <>
      <View style={styles.shareContainer}>
        <View style={styles.profileContainer}>
          <View style={styles.imageContainer}>
            <Image
              source={require('@/assets/default-profile.png')}
              style={styles.profileImage}
            />
          </View>
          <View style={styles.shareInfoContainer}>
            <MainText>{data.title}</MainText>
            <Text style={styles.postDate}>
              {dateTimeToString(data.updatedAt)}
            </Text>
          </View>
          <Pressable
            style={styles.moveButton}
            onPress={() => {
              modalOpen(data.route as LatLng[], walkId);
            }}>
            <Text style={styles.moveButtonText}>따라걷기</Text>
          </Pressable>
        </View>
        <View style={styles.shareImageContainer}>
          <Image style={styles.shareImage} source={{uri: data.imageUrl}} />
        </View>
        <View style={styles.shareIndicator}>
          {viewIsLiked ? (
            <Pressable onPress={handlePressLikeButton}>
              <Ionicons name="heart" size={32} color={'red'} />
            </Pressable>
          ) : (
            <Pressable onPress={handlePressLikeButton}>
              <Ionicons name="heart-outline" size={32} color={'white'} />
            </Pressable>
          )}
          <Text style={styles.shareIndicatorText}>
            좋아요 {data.likeUsers?.length}개
          </Text>
          <Text style={styles.shareIndicatorText}>
            같이 걸은 사람 {data.trackingUsers?.length}명
          </Text>
        </View>
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  shareContainer: {gap: 15, marginBottom: 25},
  profileContainer: {
    flexDirection: 'row',
    height: 44,
  },
  imageContainer: {
    width: 44,
    height: 44,
    borderColor: colors.white,
    borderWidth: 1,
    borderRadius: 50,
  },
  profileImage: {
    width: '100%',
    height: '100%',
  },
  shareInfoContainer: {justifyContent: 'center', paddingLeft: 10},
  userName: {fontSize: 24, color: colors.white},
  postDate: {
    fontFamily: 'GmarketSansTTFLight',
    fontSize: 12,
    color: colors.white,
  },
  moveButton: {
    position: 'absolute',
    right: 0,
    height: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primaryColorPink,
    borderRadius: 15,
    paddingHorizontal: 10,
  },
  moveButtonText: {
    fontFamily: 'GmarketSansTTFMedium',
    fontSize: 16,
    color: colors.white,
  },
  shareImageContainer: {
    width: '100%',
    height: Dimensions.get('screen').width - 30,
    justifyContent: 'center',
    alignItems: 'center',
  },
  shareImage: {width: '100%', height: '100%'},
  shareIndicator: {flexDirection: 'row', alignItems: 'center', gap: 10},
  shareIndicatorText: {
    fontFamily: 'GmarketSansTTFLight',
    color: colors.white,
    fontSize: 18,
  },
  lockButton: {
    backgroundColor: colors.primaryColorPink,
    flexDirection: 'row',
    width: '100%',
    paddingTop: 10,
    paddingBottom: 8,
    paddingHorizontal: 15,
    justifyContent: 'space-between',
    alignItems: 'center',
    borderRadius: 8,
  },
  confirmModalContainer: {
    backgroundColor: colors.buttonBackground,
    flex: 1,
    justifyContent: 'flex-end',
  },
});
