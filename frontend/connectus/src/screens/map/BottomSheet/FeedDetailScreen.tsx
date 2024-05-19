import {
  Dimensions,
  Image,
  Keyboard,
  Pressable,
  StyleSheet,
  Text,
  TextInput,
  TouchableWithoutFeedback,
  View,
} from 'react-native';
import React, {useEffect, useState} from 'react';
import {KeyboardAwareScrollView} from 'react-native-keyboard-aware-scroll-view';
import Ionicons from 'react-native-vector-icons/Ionicons';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import Comment from '@/components/feed/Comment';
import {BottomSheetScrollView} from '@gorhom/bottom-sheet';
import {
  createPostComment,
  getMapPostDetail,
  getPostDetail,
  postFeedLike,
} from '@/api/post';
import {StackScreenProps} from '@react-navigation/stack';
import {BottomSheetStackParamList} from '@/navigations/stack/BottomSheetQuickStackNavigator';
import useAuthStore from '@/store/useAuthStore';
import {useMutation, useQuery} from '@tanstack/react-query';
import {queryKeys} from '@/constants';
import {dateTimeToString, getPosition} from '@/utils';
import queryClient from '@/api/queryClient';

type FeedDetailScreenProps = StackScreenProps<
  BottomSheetStackParamList,
  'Feed'
>;

/**
 * 바텀시트에서 사용할 Feed Detail Screen
 * @todo feedHomeScreen에서 해당 스크린에 대한 데이터를 전달받아 like, comment의 수를 받아와야하고
 * 이미지 및 상세 content내용을 받아와야합니다
 */
export default function FeedDetailScreen({route}: FeedDetailScreenProps) {
  const [isUseKeyBoard, setIsUseKeyBoard] = useState(false);
  const [comment, setComment] = useState('');
  // 요청에 사용할 피드 아이디입니다.
  const {feedId, coordinate} = route.params;
  // 요청에 사용할 유저 정보입니다.
  const {user} = useAuthStore();

  const {data, isLoading} = useQuery({
    queryFn: () => getMapPostDetail(feedId, user?.userId as number, coordinate),
    queryKey: [queryKeys.GET_FEED_DETAIL, feedId],
  });

  console.log(data);

  const like = useMutation({
    mutationFn: () =>
      postFeedLike({
        userId: user?.userId as number,
        domainId: feedId,
        type: 'POST',
      }),
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: [queryKeys.GET_FEED_DETAIL, feedId],
      }),
  });

  const addComment = useMutation({
    mutationFn: () =>
      createPostComment(feedId, {
        content: comment,
        authorId: user?.userId as number,
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: [queryKeys.GET_FEED_DETAIL, feedId],
      });
      setComment('');
    },
  });

  // 좋아요 버튼을 눌렀을때 실행할 함수로 나중에 api연결이 필요합니다
  const handlePressLikeButton = () => {
    like.mutate();
  };

  /**
   * 댙긑 제출함수
   * @todo 추후 postId, {content , authorId} 수정
   */
  const handleSubmitComment = async () => {
    addComment.mutate();
    Keyboard.dismiss();
  };

  // 컴포넌트 로드 시 키보드가 보일때 마다 화면을 제어하기위해 이벤트리스너를 부착합니다
  useEffect(() => {
    const didShow = Keyboard.addListener('keyboardDidShow', () =>
      setIsUseKeyBoard(true),
    );
    const didHide = Keyboard.addListener('keyboardDidHide', () =>
      setIsUseKeyBoard(false),
    );
    return () => {
      didShow.remove();
      didHide.remove();
    };
  }, []);

  if (isLoading) {
    return <MainText>게시글을 로드중입니다</MainText>;
  }

  return (
    <>
      <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
        <BottomSheetScrollView>
          <KeyboardAwareScrollView style={styles.feedContainer}>
            <View style={styles.profileContainer}>
              <View style={styles.imageContainer}>
                <Image
                  source={require('@/assets/default-profile.png')}
                  style={styles.profileImage}
                />
              </View>
              <View style={styles.feedInfoContainer}>
                <MainText>{data?.authorName}</MainText>
                <Text style={styles.postDate}>
                  {dateTimeToString(data?.updatedAt as string)}
                </Text>
              </View>
            </View>
            <View style={styles.feedImageContainer}>
              <Image style={styles.feedImage} source={{uri: data?.imageUrl}} />
            </View>
            <View style={styles.feedIndicator}>
              {data?.like ? (
                <Pressable onPress={handlePressLikeButton}>
                  <Ionicons name="heart" size={32} color={'red'} />
                </Pressable>
              ) : (
                <Pressable onPress={handlePressLikeButton}>
                  <Ionicons name="heart-outline" size={32} color={'white'} />
                </Pressable>
              )}
              <Text style={styles.feedIndicatorText}>
                좋아요 {data?.likeCount}개
              </Text>
              <Text style={styles.feedIndicatorText}>
                댓글 {data?.commentCount}개
              </Text>
            </View>
            {!data?.inRange && (
              <MainText>
                세부 내용을 확인하시려면 조금더 가까이 다가가주세요
              </MainText>
            )}
            <View style={styles.feedContentContainer}>
              <MainText>{data?.content}</MainText>
            </View>
            <View style={styles.commentListContainer}>
              {data?.commentList !== undefined &&
                data?.commentList.map((comment, index) => {
                  return <Comment key={index} params={comment} />;
                })}
            </View>
            <View style={styles.defaultBottomPadding} />
            <View style={isUseKeyBoard ? styles.keyboardBottomPadding : null} />
          </KeyboardAwareScrollView>
        </BottomSheetScrollView>
      </TouchableWithoutFeedback>
      <View style={commentStyle.commentInputContainer}>
        <TextInput
          multiline
          returnKeyType="send"
          autoCapitalize="none"
          spellCheck={false}
          autoCorrect={false}
          placeholder="댓글 남기기"
          placeholderTextColor={colors.white}
          style={commentStyle.commentInput}
          value={comment}
          onChangeText={text => setComment(text)}
        />
        <Pressable
          style={commentStyle.submitButton}
          onPress={handleSubmitComment}>
          <MaterialIcons name="message" size={24} color={colors.white} />
        </Pressable>
      </View>
    </>
  );
}

const commentStyle = StyleSheet.create({
  commentInputContainer: {
    position: 'absolute',
    width: Dimensions.get('screen').width,
    height: 50,
    bottom: 0,
    backgroundColor: colors.background,
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  commentInput: {
    color: colors.white,
    backgroundColor: colors.buttonBackground,
    width: Dimensions.get('screen').width - 55,
    borderRadius: 15,
  },
  submitButton: {
    width: 50,
    height: 50,
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

const styles = StyleSheet.create({
  feedContainer: {
    flex: 1,
    gap: 5,
    paddingTop: 5,
    paddingBottom: Dimensions.get('screen').height / 2,
    backgroundColor: colors.background,
  },
  profileContainer: {
    paddingHorizontal: 5,
    flexDirection: 'row',
    height: 60,
    paddingBottom: 10,
    alignItems: 'center',
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
  feedImageContainer: {
    width: '100%',
    height: Dimensions.get('screen').width - 30,
    justifyContent: 'center',
    alignItems: 'center',
  },
  feedImage: {width: '100%', height: '100%'},
  feedIndicator: {
    paddingVertical: 5,
    paddingLeft: 5,
    flexDirection: 'row',
    alignItems: 'center',
    gap: 10,
  },
  feedIndicatorText: {
    fontFamily: 'GmarketSansTTFLight',
    color: colors.white,
    fontSize: 14,
  },
  confirmModalContainer: {
    backgroundColor: colors.buttonBackground,
    flex: 1,
    justifyContent: 'flex-end',
  },
  keyboardBottomPadding: {
    paddingBottom: 50,
  },
  commentListContainer: {
    padding: 5,
    gap: 15,
  },
  defaultBottomPadding: {
    paddingBottom: 60,
  },
  feedContentContainer: {
    padding: 5,
  },
});
