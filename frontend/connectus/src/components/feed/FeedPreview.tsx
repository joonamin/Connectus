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
import React, {useState} from 'react';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';

import {FeedStackParamList} from '@/navigations/stack/FeedStackNavigator';
import {BottomTabParamList} from '@/navigations/bottomTabs/MapBottomTabsNavigator';
import MainText from '../text/MainText';
import colors from '@/constants/colors';

type feedPreviewProps = {
  isLiked: boolean;
  isVisible?: boolean;
  commentNumber: number;
  likeNumber: number;
  show?: () => void;
};

type Navigation = CompositeNavigationProp<
  StackNavigationProp<FeedStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

export default function FeedPreview({
  isLiked,
  isVisible = false,
  commentNumber,
  likeNumber,
  show,
}: feedPreviewProps) {
  const [viewIsLiked, setViewIsLiked] = useState(isLiked);
  const navigation = useNavigation<Navigation>();
  const handlePressLikeButton = () => {
    setViewIsLiked(!viewIsLiked);
  };

  const handlePressPressFeedDetail = () => {
    navigation.navigate('FeedDetail');
  };

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
            <MainText>{'userName'}</MainText>
            <Text style={styles.postDate}>{'2024-04-29'}</Text>
          </View>
          <Pressable style={styles.moveButton}>
            <Text style={styles.moveButtonText} onPress={show}>
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
          {viewIsLiked ? (
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
        {isVisible ? (
          <Pressable
            style={styles.lockButton}
            onPress={handlePressPressFeedDetail}>
            <MainText>게시글 바로보기</MainText>
            <Ionicons name="arrow-redo-circle" size={24} />
          </Pressable>
        ) : (
          <Pressable
            style={styles.lockButton}
            onPress={() => console.log('바로가기')}>
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
});
