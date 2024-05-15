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
import useModal from '@/hooks/useModal';
import Comment from '@/components/feed/Comment';
import {createPostComment, getPostDetail, postFeedLike} from '@/api/post';
import {useMutation, useQuery} from '@tanstack/react-query';
import useAuthStore from '@/store/useAuthStore';
import {queryKeys} from '@/constants';
import {comment} from '@/types';
import queryClient from '@/api/queryClient';

/**
 * @todo feedHomeScreen에서 해당 스크린에 대한 데이터를 전달받아 like, comment의 수를 받아와야하고
 * 이미지 및 상세 content내용을 받아와야합니다
 */
export default function FeedDetailScreen() {
  const {
    isVisible: isMoveModalVisible,
    show: moveModalShow,
    hide: moveModalHide,
  } = useModal();

  const [isFeedLiked, setIsFeedLiked] = useState(false);
  const [isUseKeyBoard, setIsUseKeyBoard] = useState(false);
  const [comment, setComment] = useState('');
  const {user} = useAuthStore();

  // 좋아요 버튼을 눌렀을때 실행할 함수로 나중에 api연결이 필요합니다
  const handlePressLikeButton = () => {
    setIsFeedLiked(!isFeedLiked);
  };

  const {data, isLoading, isError} = useQuery({
    queryFn: () => getPostDetail(9, {userId: 1, distance: 5}),
    queryKey: [queryKeys.GET_FEED_DETAIL],
  });

  /**
   * @todo postId 전달
   * params로 전달해줄
   * {"content" : String,"authorId" : Long }
   * 형태의 데이터 전달
   */
  const body = {content: comment, authorId: user?.userId as number};
  const postComment = useMutation({
    mutationFn: () => createPostComment(9, body),
    onSuccess: () => {
      console.log('요청 성공');
      queryClient.invalidateQueries({queryKey: [queryKeys.GET_FEED_DETAIL]});
    },
    onSettled: () => {
      setComment('');
      Keyboard.dismiss();
    },
  });

  const postLikeBody: Parameters<typeof postFeedLike>[0] = {
    userId: user?.userId as number,
    domainId: 9,
    type: 'POST',
  };
  /**
   * 좋아요 버튼을 눌럿을 때 호출할 mutation 함수입니다
   * @todo body에 있는 domainId 넘버링 처리하기
   */
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

  const handlePostComment = () => {
    postComment.mutate();
  };

  const handlePostLike = () => {
    postLike.mutate();
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
    return <Text>로딩중입니당...</Text>;
  }

  if (isError || data === undefined) {
    return <Text>요청에 실패했습니다...</Text>;
  }

  return (
    <>
      <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
        <KeyboardAwareScrollView style={styles.feedContainer}>
          <View style={styles.profileContainer}>
            <View style={styles.imageContainer}>
              <Image
                source={require('@/assets/default-profile.png')}
                style={styles.profileImage}
              />
            </View>
            <View style={styles.feedInfoContainer}>
              <MainText>{data.authorName}</MainText>
              <Text style={styles.postDate}>{'2024-04-29'}</Text>
            </View>
            <Pressable style={styles.moveButton}>
              <Text style={styles.moveButtonText} onPress={moveModalShow}>
                보러가기
              </Text>
            </Pressable>
          </View>
          <View style={styles.feedImageContainer}>
            <Image style={styles.feedImage} source={{uri: data.imageUrl}} />
          </View>
          {/* 좋아요 버튼 */}
          <View style={styles.feedIndicator}>
            {data.like ? (
              <Pressable onPress={handlePostLike}>
                <Ionicons name="heart" size={32} color={'red'} />
              </Pressable>
            ) : (
              <Pressable onPress={handlePostLike}>
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
          <View style={styles.feedContentContainer}>
            <MainText>{data.content}</MainText>
          </View>
          <View style={styles.commentListContainer}>
            {data.commentList.map((comment: comment, index) => {
              return <Comment key={index} params={comment} />;
            })}
          </View>
          <View style={styles.defaultBottomPadding} />
          <View style={isUseKeyBoard ? styles.keyboardBottomPadding : null} />
        </KeyboardAwareScrollView>
      </TouchableWithoutFeedback>
      <View style={commentStyle.commentInputContainer}>
        <View style={commentStyle.textInputContainer}>
          <TextInput
            multiline={true}
            onChangeText={text => setComment(text)}
            returnKeyType="send"
            autoCapitalize="none"
            spellCheck={false}
            autoCorrect={false}
            placeholder="댓글 남기기"
            placeholderTextColor={colors.white}
            style={commentStyle.commentInput}
            value={comment}
          />
        </View>
        <Pressable
          style={commentStyle.submitButton}
          onPress={handlePostComment}>
          <MaterialIcons name="message" size={24} color={colors.white} />
        </Pressable>
      </View>
    </>
  );
}

const commentStyle = StyleSheet.create({
  commentInputContainer: {
    bottom: 0,
    height: 50,
    flexDirection: 'row',
    position: 'absolute',
    justifyContent: 'space-between',
    backgroundColor: colors.background,
    width: Dimensions.get('screen').width,
  },
  textInputContainer: {
    height: 50,
    alignItems: 'center',
    justifyContent: 'center',
    textAlignVertical: 'center',
  },
  commentInput: {
    height: '100%',
    color: colors.white,
    backgroundColor: colors.buttonBackground,
    width: Dimensions.get('screen').width - 55,
    padding: 15,
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
    overflow: 'hidden',
  },
  profileImage: {
    width: '100%',
    height: '100%',
    borderRadius: 50,
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
    right: 5,
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
    padding: 15,
    borderBottomColor: colors.white,
    borderBottomWidth: 1,
  },
});
