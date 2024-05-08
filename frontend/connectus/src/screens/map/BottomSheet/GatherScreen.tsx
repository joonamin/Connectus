import React, {useState} from 'react';
import {Image, StyleSheet, Text, View} from 'react-native';
import {CountdownCircleTimer} from 'react-native-countdown-circle-timer';
import MainContainer from '@/components/containers/MainContainer';
import colors from '@/constants/colors';
import CustomTextButton from '@/components/buttons/CustomTextButton';
import {fonts} from '@/constants';
import MainText from '@/components/text/MainText';

type GatherScreenType = {
  id: number;
};
/**
 * 맵에있는 모여라 마커를 press했을 시, 이동하게될 screen입니다.
 */
export default function GatherScreen() {
  // 게시자를 확인하고 모집인원 아래의 ui를 변하게 합니다.
  const [isPublisher, setIsPublisher] = useState<boolean>(false);
  return (
    <MainContainer style={styles.mainContainer}>
      <View style={styles.topIndicator}>
        <View style={styles.imageContainer}>
          <Image
            source={require('@/assets/default-profile.png')}
            style={styles.image}
          />
        </View>
        <View style={styles.userInfo}>
          <Text style={styles.userName}>{'username'}</Text>
          <Text style={styles.postTime}>{'postTime'}</Text>
        </View>
      </View>
      <View style={styles.countContainer}>
        <CountdownCircleTimer
          isPlaying
          duration={3600}
          colors={['#004777', '#F7B801', '#A30000', '#A30000']}
          colorsTime={[3600, 5, 2, 0]}>
          {({remainingTime}) => {
            const hours = Math.floor(remainingTime / 3600);
            const minutes = Math.floor((remainingTime % 3600) / 60);
            const seconds = remainingTime % 60;

            return (
              <View style={styles.countText}>
                <MainText>
                  {hours}:{minutes}:{seconds}
                </MainText>
                <MainText>{'3/5'}</MainText>
              </View>
            );
          }}
        </CountdownCircleTimer>
      </View>
      <View style={styles.countContainer}>
        <MainText>{'n명이 지금 오고 있습니다..'}</MainText>
      </View>
      <View style={styles.gatherContainer}>
        <Text style={styles.gatherFont}>
          {'이곳에 설명이 들어올 것이다 닝겐...'}
        </Text>
      </View>
      {isPublisher && <CustomTextButton label="모여라 종료" />}
      {!isPublisher && <CustomTextButton label="모여라 신청" />}
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    backgroundColor: colors.background,
    gap: 15,
    flex: 1,
  },
  topIndicator: {
    alignItems: 'center',
    width: '100%',
    flexDirection: 'row',
    gap: 15,
  },
  imageContainer: {
    width: 44,
    height: 44,
    borderRadius: 50,
  },
  image: {width: '100%', height: '100%'},
  userInfo: {
    justifyContent: 'center',
  },
  userName: {fontSize: 24, color: colors.white},
  postTime: {fontSize: 18, color: colors.white},
  gatherContainer: {
    width: '100%',
    backgroundColor: colors.white,
    borderRadius: 15,
    padding: 15,
    justifyContent: 'center',
    alignItems: 'center',
  },
  gatherFont: {
    fontFamily: fonts.medium,
    textAlign: 'center',
  },
  countContainer: {
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
  },
  countText: {
    justifyContent: 'center',
    alignItems: 'center',
    gap: 15,
  },
});
