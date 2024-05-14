import React, {useEffect, useState} from 'react';
import {Alert, Image, StyleSheet, Text, View} from 'react-native';
import {CountdownCircleTimer} from 'react-native-countdown-circle-timer';
import MainContainer from '@/components/containers/MainContainer';
import colors from '@/constants/colors';
import CustomTextButton from '@/components/buttons/CustomTextButton';
import {fonts} from '@/constants';
import MainText from '@/components/text/MainText';
import {gatherDetail, gatherDone, gatherWant, gatherReach} from '@/api/gather';
import {ScreenProps} from 'react-native-screens';
import {BottomSheetStackParamList} from '@/navigations/stack/BottomSheetQuickStackNavigator';
import {StackScreenProps} from '@react-navigation/stack';
import {useQuery} from '@tanstack/react-query';
import {queryKeys} from '@/constants/keys';

type GatherScreenProps = StackScreenProps<BottomSheetStackParamList, 'Gather'>;

/**
 * 맵에있는 모여라 마커를 press했을 시, 이동하게될 screen입니다.
 * @todo duration에 남은시간 초로 전달
 * @todo 해당 마커를 클릭한 유저가 게시자인지 판별
 * @todo 해당 마커의 좌표와 현재 유저의 좌표(거리) 계산 후, 거리에 따른 참여버튼 수정 필요
 */
export default function GatherScreen({route}: GatherScreenProps) {
  // 게시자를 확인하고 모집인원 아래의 ui를 변하게 합니다.
  const [isPublisher, setIsPublisher] = useState<boolean>(false);

  /**
   * 데이터를 요청할 useQuery
   * data에 정보들이 담겨있습니다.
   */
  const {data, isLoading} = useQuery({
    queryFn: () => gatherDetail(route.params.gatherId),
    queryKey: [queryKeys.GET_GATHER],
  });

  /**
   * 모여라의 id와 user의 id를 제공해 모여라를 close
   * @todo useId 수정
   */
  const handleGatherClose = async () => {
    if (data) {
      await gatherDone({
        gatherId: data?.gatherId,
        userId: 1,
      });
    }
  };

  /**
   * 요청을 희망하는 유저가 호출할 axios요청입니다
   * @todo useId 수정
   */
  const handleWantJoin = async () => {
    if (data) {
      console.log('asfasgagda?');
      const res = await gatherWant({gatherId: data.gatherId, userId: 1});
      if (res.msg === `Gather was already closed : ${data.gatherId}`) {
        Alert.alert('이미 종료된 모여라입니다');
      }
    }
  };

  /**
   * @todo 거리계산 후, 어느정도 거리 안에 들어온 유저가 호출할수있도록 수정필요
   */
  const handleGatherReach = async () => {
    await gatherReach({gatherId: 1, userId: 1});
  };

  if (isLoading) {
    return (
      <MainContainer style={styles.mainContainer}>
        <MainText>모여라 데이터를 불러오는중입니다.</MainText>
      </MainContainer>
    );
  }

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
                <MainText>{`${data?.joinerList.length}/${data?.maxJoiner}`}</MainText>
              </View>
            );
          }}
        </CountdownCircleTimer>
      </View>
      <View style={styles.countContainer}>
        <MainText>{`${data?.candidateList.length}명이 지금 오고 있습니다..`}</MainText>
      </View>
      <View style={styles.gatherContainer}>
        <Text style={styles.gatherFont}>{data?.content}</Text>
      </View>
      {isPublisher && (
        <CustomTextButton label="모여라 종료" onPress={handleGatherClose} />
      )}
      {!isPublisher && (
        <CustomTextButton label="모여라 신청" onPress={handleWantJoin} />
      )}
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
