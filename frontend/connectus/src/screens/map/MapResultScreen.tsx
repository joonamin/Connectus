import {
  Pressable,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  View,
} from 'react-native';
import React, {useEffect, useRef, useState} from 'react';
import MainText from '@/components/text/MainText';
import WalkResult from '@/components/map/WalkResult';
import Achievement from '@/components/map/Achievement';
import EventResult from '@/components/map/EventResult';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import RecordedPost from '@/components/map/RecordedPost';
import {BottomTabScreenProps} from '@react-navigation/bottom-tabs';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import {convertSecondsToTime, formatTime} from '@/utils';
import {StackScreenProps} from '@react-navigation/stack';
import RouteMap from '@/components/map/RouteMap';
import {useMutation} from '@tanstack/react-query';
import {createRoute} from '@/api/walk';
import useAuthStore from '@/store/useAuthStore';
import {LatLng} from 'react-native-maps';
import usePostStore from '@/store/usePostStore';

const DUMMY_ACHIEVE = [
  {
    title: '우리팀 폭탄',
    description: '영욱님이 인정한 폭탄 돌리기의 폭탄 역할',
  },
];

type ScreenProps = StackScreenProps<MapStackParamList>;

/**
 *  산책 종료시 이동하게될 페이지입니다.
 */
export default function MapResultScreen({route}: ScreenProps) {
  const {seconds, minutes, hours} = convertSecondsToTime(route.params?.time);
  const [walkTitle, setWalkTitle] = useState<string>('');
  const indicateTime = formatTime(hours, minutes, seconds);
  const map = useRef<RouteMap | null>(null);
  const {user} = useAuthStore();
  const {posts, setDeletePost} = usePostStore();
  const [routeImage, setRouteImg] = useState<any>();

  // const [routeImg, setRouteImg] = useState();

  const onMapReady = async () => {
    await map.current?.takeSnapshot({result: 'file'}).then(uri => {
      setRouteImg(uri);
    });
    // const tempImage = await map.current?.takeSnapshot();
    // setRouteImg(tempImage);
    // console.log('이미지입니다', tempImage);
  };

  /**
   * 산책을 종료할 때 사용할 mutation함수입니다.
   * 산책 결과 이미지, 산책 거리, 산책 시간, post, 산책 제목을 params로 받아
   * 산책을 저장합니다.
   */
  const endWalk = useMutation({
    mutationFn: () =>
      createRoute({
        userId: user?.userId as number,
        title: walkTitle,
        route: route.params?.walkRoute as LatLng[],
        walkTime: route.params?.time as number,
        walkDistance: route.params?.distance as number,
        postList: posts,
        image: routeImage,
      }),
  });

  const handleEndWalk = () => {
    endWalk.mutate();
  };

  if (!route.params) {
    return <Text>살려줘요</Text>;
  }
  return (
    <SafeAreaView>
      <ScrollView>
        <View style={styles.mainContainer}>
          <MainText>산책 종료</MainText>
          <RouteMap
            routes={route.params?.walkRoute}
            ref={map}
            style={{alignSelf: 'stretch'}}
            onMapReady={onMapReady}
          />
          <WalkResult time={indicateTime} distance={route.params?.distance} />
          {/* <Achievement achievs={DUMMY_ACHIEVE} /> */}
          {/* <EventResult /> */}
          <RecordedPost />
          <View style={styles.titleInputContainer}>
            <MainText>이번 산책의 제목을 입력해주세요</MainText>
            <Text style={styles.subText}>
              산책의 이름은 피드 업로드시 활용됩니다
            </Text>
            <TextInput
              onChangeText={text => setWalkTitle(text)}
              style={styles.titleInput}
              textAlign="center"
            />
          </View>
          <Pressable style={styles.submitButton} onPress={handleEndWalk}>
            <MainText>산책 종료하기</MainText>
          </Pressable>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    width: '100%',
    padding: 15,
    alignItems: 'center',
    gap: 15,
  },
  titleInputContainer: {
    marginTop: 10,
    width: '100%',
    gap: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
  subText: {
    color: colors.white,
    fontFamily: fonts.light,
    marginBottom: 5,
  },
  titleInput: {
    color: colors.white,
    fontSize: 24,
    height: 60,
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 3,
    borderColor: colors.primaryColorBlue,
    borderRadius: 15,
    backgroundColor: colors.buttonBackground,
    padding: 15,
  },
  submitButton: {
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 15,
    borderRadius: 15,
    backgroundColor: colors.primaryColorBlue,
  },
});
