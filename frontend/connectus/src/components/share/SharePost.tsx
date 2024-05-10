import {
  Dimensions,
  Image,
  Pressable,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import React, {useState} from 'react';
import colors from '@/constants/colors';
import Ionicons from 'react-native-vector-icons/Ionicons';
import MainText from '../text/MainText';
import {LatLng} from 'react-native-maps';

type Props = {
  modalOpen: (route: LatLng[]) => void;
};

/**
 * share 스크린에서 무한스크롤을 통해 렌더링할 컴포넌트입니다.
 * @todo 추후 기록이 어떻게 전달되는지에 따라서 안쪽의 state를 수정해야합니다
 */
export default function SharePost({modalOpen}: Props) {
  const [viewIsLiked, setViewIsLiked] = useState(false);
  const commentNumber = 2;
  const likeNumber = 2;

  // 해당 포스트가 가지고있는 route
  const route = [
    {latitude: 35.090907937349, longitude: 128.85270665510822},
    {latitude: 35.09097724600205, longitude: 128.85406782269817},
    {latitude: 35.08917478192473, longitude: 128.85407087211667},
    {latitude: 35.08920490612796, longitude: 128.85269005548565},
  ];

  const handlePressLikeButton = () => {
    setViewIsLiked(!viewIsLiked);
  };

  const handlePressPressFeedDetail = () => {
    console.log('hello');
  };
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
            <MainText>{'userName'}</MainText>
            <Text style={styles.postDate}>{'2024-04-29'}</Text>
          </View>
          <Pressable
            style={styles.moveButton}
            onPress={() => {
              modalOpen(route);
            }}>
            <Text style={styles.moveButtonText}>따라걷기</Text>
          </Pressable>
        </View>
        <View style={styles.shareImageContainer}>
          <Image
            style={styles.shareImage}
            source={require('@/assets/map.png')}
          />
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
          <Text style={styles.shareIndicatorText}>좋아요 {likeNumber}개</Text>
          <Text style={styles.shareIndicatorText}>
            같이 걸은 사람 {commentNumber}명
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
