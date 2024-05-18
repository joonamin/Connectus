import colors from '@/constants/colors';
import React from 'react';
import {Image, ImageProps, StyleSheet, View, ViewProps} from 'react-native';
import MainContainer from '@/components/containers/MainContainer';
import MainText from '@/components/text/MainText';
import HeadingText from '@/components/text/HeadingText';
import StatisticsBadge from './StatisticsBadge';

/**
 * ProfileOverview를 생성하기 위한 인자를 지정합니다
 */
export interface ProfileOverviewProps extends ViewProps {
  /**
   * 프로필 사진
   */
  profileImage?: ImageProps['source'];
}

/**
 * 마이페이지 내 프로필 정보를 표시합니다
 *
 * @returns ProfileOverview
 */
export default function ProfileOverview({
  profileImage,
  ...props
}: ProfileOverviewProps) {
  // 프로필 이미지가 없을 시 기본 이미지 사용
  if (typeof profileImage === 'undefined') {
    profileImage = require('@/assets/default-profile.png');
  }

  return (
    <View style={styles.container}>
      <Image
        style={avatarStyles.avatar}
        source={profileImage}
        defaultSource={require('@/assets/default-profile.png')}></Image>
      <MainContainer style={styles.overview}>
        <HeadingText style={styles.username}>한방 옻닭</HeadingText>
        <View style={styles.badges}>
          <StatisticsBadge
            style={styles.badge}
            label="보유 포인트"
            value={320}
          />
          <StatisticsBadge
            style={styles.badge}
            label="작성 방명록"
            value={32}
          />
          <StatisticsBadge style={styles.badge} label="달성 업적" value={12} />
        </View>
      </MainContainer>
    </View>
  );
}

const avatarStyles = StyleSheet.create({
  avatar: {
    aspectRatio: '1 / 1',
    height: 96,
    borderRadius: 180,
    backgroundColor: colors.buttonBackground,
    alignSelf: 'center',
    borderColor: colors.white,
    borderWidth: 2,
    zIndex: 1,
  },
});
const styles = StyleSheet.create({
  container: {
    marginBottom: -(avatarStyles.avatar['height'] / 2),
  },
  overview: {
    top: -(avatarStyles.avatar['height'] / 2),
    paddingTop: avatarStyles.avatar['height'] / 2 + 15,
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
    gap: 15,
  },
  username: {
    alignSelf: 'center',
  },
  badges: {
    flexDirection: 'row',
  },
  badge: {
    flex: 1,
  },
});
