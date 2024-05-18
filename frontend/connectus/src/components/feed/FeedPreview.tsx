import {
  Pressable,
  StyleSheet,
  Text,
  View,
  Image,
  Dimensions,
  Modal,
  SafeAreaView,
} from 'react-native';
import React, {useEffect, useState} from 'react';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';

import {FeedStackParamList} from '@/navigations/stack/FeedStackNavigator';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import MainText from '../text/MainText';
import colors from '@/constants/colors';
import {useMutation, useQuery} from '@tanstack/react-query';
import {getPostDetail, getPostSpot, postFeedLike} from '@/api/post';
import useAuthStore from '@/store/useAuthStore';
import {queryKeys} from '@/constants';
import queryClient from '@/api/queryClient';
import {dateTimeToString} from '@/utils';
import {modalType} from '@/screens/feed/FeedHomeScreen';
import {LatLng} from 'react-native-maps';

type feedPreviewProps = {
  feedId: number;
  show: (modalType: modalType, position: LatLng, feedId: number) => void;
};

type Navigation = CompositeNavigationProp<
  StackNavigationProp<FeedStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

export default function FeedPreview({feedId, show}: feedPreviewProps) {
  // const [viewIsLiked, setViewIsLiked] = useState(isLiked);
  const navigation = useNavigation<Navigation>();
  const {user} = useAuthStore();

  const {data, isLoading} = useQuery({
    queryFn: () => getPostDetail(feedId, user?.userId as number),
    queryKey: [queryKeys.GET_FEED_DETAIL, feedId],
  });

  const postLikeBody: Parameters<typeof postFeedLike>[0] = {
    userId: user?.userId as number,
    domainId: feedId,
    type: 'POST',
  };

  const {data: postSpot} = useQuery({
    queryFn: () => getPostSpot(feedId),
    queryKey: [queryKeys.GET_MARKER_POSITION],
  });

  const postLike = useMutation({
    mutationFn: () => postFeedLike(postLikeBody),
    onSuccess: () => {
      console.log('요청 성공');
      queryClient.invalidateQueries({queryKey: [queryKeys.GET_FEED_DETAIL]});
    },
    onError: () => {
      console.log('에러');
    },
  });

  const handlePressPressFeedDetail = () => {
    navigation.navigate('FeedDetail', {feedId: feedId});
  };

  const handleLike = () => {
    postLike.mutate();
  };

  if (isLoading) {
    return <Text>로딩중입니다</Text>;
  }

  if (!data) {
    return <MainText>데이터가 없습니다</MainText>;
  }

  return (
    <>
      <View style={styles.feedContainer}>
        <View style={styles.profileContainer}>
          <View style={styles.imageContainer}>
            <Image
              source={require('@/assets/default-profile.png')}
              style={styles.profileImage}
            />
          </View>
          <View style={styles.feedInfoContainer}>
            <MainText>{data.authorName}</MainText>
            <Text style={styles.postDate}>
              {dateTimeToString(data.updatedAt)}
            </Text>
          </View>
          <Pressable
            style={styles.moveButton}
            onPress={() =>
              show(
                'move',
                {
                  latitude: postSpot?.latitude as number,
                  longitude: postSpot?.longitude as number,
                },
                feedId,
              )
            }>
            <Text style={styles.moveButtonText}>보러가기</Text>
          </Pressable>
        </View>
        <View style={styles.feedImageContainer}>
          {data.imageUrl && (
            <Image
              style={styles.feedImage}
              source={{
                uri: data.imageUrl,
              }}
            />
          )}
          {!data.imageUrl && <MainText>사진이 존재하지 않습니다</MainText>}
        </View>
        <View style={styles.feedIndicator}>
          {data.like ? (
            <Pressable onPress={handleLike}>
              <Ionicons name="heart" size={32} color={'red'} />
            </Pressable>
          ) : (
            <Pressable onPress={handleLike}>
              <Ionicons name="heart-outline" size={32} color={'white'} />
            </Pressable>
          )}
          <Text style={styles.feedIndicatorText}>
            좋아요 {data.likeCount}개
          </Text>
          <Text style={styles.feedIndicatorText}>
            댓글 {data.commentCount}개
          </Text>
        </View>
        {data.inRange ? (
          <Pressable
            style={styles.lockButton}
            onPress={handlePressPressFeedDetail}>
            <MainText>게시글 바로보기</MainText>
            <Ionicons name="arrow-redo-circle" size={24} />
          </Pressable>
        ) : (
          <Pressable
            style={styles.lockButton}
            onPress={() =>
              show(
                'open',
                {latitude: postSpot.latitde, longitude: postSpot.longitude},
                feedId,
              )
            }>
            <MainText>포인트로 해금하기</MainText>
            <Ionicons name="lock-closed" size={24} />
          </Pressable>
        )}
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  feedContainer: {gap: 5},
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
  feedInfoContainer: {justifyContent: 'center', paddingLeft: 10},
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
  feedImageContainer: {
    width: '100%',
    height: Dimensions.get('screen').width - 30,
    justifyContent: 'center',
    alignItems: 'center',
  },
  feedImage: {width: '100%', height: '100%'},
  feedIndicator: {flexDirection: 'row', alignItems: 'center', gap: 10},
  feedIndicatorText: {
    fontFamily: 'GmarketSansTTFLight',
    color: colors.white,
    fontSize: 14,
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
  modalBackground: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});
