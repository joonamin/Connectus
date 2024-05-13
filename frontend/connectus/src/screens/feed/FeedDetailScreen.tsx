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
import {createPostComment} from '@/api/post';

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
  // 스크린 확인을 위한 더미 데이터입니다
  const likeNumber = 12;
  const commentNumber = 42;
  // 좋아요 버튼을 눌렀을때 실행할 함수로 나중에 api연결이 필요합니다
  const handlePressLikeButton = () => {
    setIsFeedLiked(!isFeedLiked);
  };

  /**
   * @todo postId 전달
   * params로 전달해줄
   * {postId" : Long, "dto" : {"content" : String,"authorId" : Long}}
   * 형태의 데이터 전달
   */
  const handlePostComment = async () => {
    createPostComment(1);
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
              <MainText>{'userName'}</MainText>
              <Text style={styles.postDate}>{'2024-04-29'}</Text>
            </View>
            <Pressable style={styles.moveButton}>
              <Text style={styles.moveButtonText} onPress={moveModalShow}>
                보러가기
              </Text>
            </Pressable>
          </View>
          <View style={styles.feedImageContainer}>
            <Image
              style={styles.feedImage}
              source={require('@/assets/test-feed-image.jpg')}
            />
          </View>
          {/* 좋아요 버튼 */}
          <View style={styles.feedIndicator}>
            {isFeedLiked ? (
              <Pressable onPress={handlePressLikeButton}>
                <Ionicons name="heart" size={32} color={'red'} />
              </Pressable>
            ) : (
              <Pressable onPress={handlePressLikeButton}>
                <Ionicons name="heart-outline" size={32} color={'white'} />
              </Pressable>
            )}
            <Text style={styles.feedIndicatorText}>좋아요 {likeNumber}개</Text>
            <Text style={styles.feedIndicatorText}>댓글 {commentNumber}개</Text>
          </View>
          <View style={styles.feedContentContainer}>
            <MainText>메인내용</MainText>
          </View>
          <View style={styles.commentListContainer}>
            <Comment />
            <Comment />
            <Comment />
          </View>
          <View style={styles.defaultBottomPadding} />
          <View style={isUseKeyBoard ? styles.keyboardBottomPadding : null} />
        </KeyboardAwareScrollView>
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
        />
        <Pressable style={commentStyle.submitButton}>
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
    padding: 5,
  },
});
