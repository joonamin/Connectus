import {
  Dimensions,
  Image,
  Keyboard,
  Pressable,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  TouchableWithoutFeedback,
  View,
} from 'react-native';
import React, {useEffect, useLayoutEffect, useState} from 'react';
import {KeyboardAwareScrollView} from 'react-native-keyboard-aware-scroll-view';
import Ionicons from 'react-native-vector-icons/Ionicons';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import useModal from '@/hooks/useModal';
export default function FeedDetailScreen() {
  const {
    isVisible: isMoveModalVisible,
    show: moveModalShow,
    hide: moveModalHide,
  } = useModal();

  const [isFeedLiked, setIsFeedLiked] = useState(false);
  const likeNumber = 12;
  const commentNumber = 42;
  const handlePressLikeButton = () => {
    setIsFeedLiked(!isFeedLiked);
  };
  const [isUseKeyBoard, setIsUseKeyBoard] = useState(false);

  useEffect(() => {
    const didShow = Keyboard.addListener('keyboardDidShow', () =>
      setIsUseKeyBoard(true),
    );
    const didHide = Keyboard.addListener('keyboardDidHide', () =>
      setIsUseKeyBoard(false),
    );
    console.log('keyboard');
    return () => {
      didShow.remove();
      didHide.remove();
    };
  }, []);

  useEffect(() => {
    console.log('change keyboard state');
  }, [isUseKeyBoard]);
  return (
    <>
      <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
        <ScrollView style={styles.feedContainer}>
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
          <View style={{paddingHorizontal: 5, paddingBottom: 45}}>
            <MainText>메인내용</MainText>
          </View>
          <View>
            <Text>여기에 댓글 들어가요</Text>
          </View>
          <View
            style={isUseKeyBoard ? styles.keyboardBottomPadding : null}></View>
        </ScrollView>
      </TouchableWithoutFeedback>
      <KeyboardAwareScrollView style={commentStyle.commentContainer}>
        <TextInput
          autoCapitalize="none"
          spellCheck={false}
          autoCorrect={false}
          style={commentStyle.commentInput}
        />
      </KeyboardAwareScrollView>
    </>
  );
}

const commentStyle = StyleSheet.create({
  commentContainer: {
    position: 'absolute',
    width: Dimensions.get('screen').width,
    height: 45,
    bottom: 0,
    backgroundColor: colors.white,
  },
  commentInput: {
    color: colors.white,
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
  keyboardBottomPadding: {
    paddingBottom: 50,
  },
});
