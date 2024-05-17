import CustomTextButton from '@/components/buttons/CustomTextButton';
import MainContainer from '@/components/containers/MainContainer';
import Achievement from '@/components/map/Achievement';
import EventResult from '@/components/map/EventResult';
import RecordedPost from '@/components/map/RecordedPost';
import WalkResult from '@/components/map/WalkResult';
import HeadingText from '@/components/text/HeadingText';
import colors from '@/constants/colors';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import {MyStackParamList} from '@/navigations/stack/MyStackNavigator';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import {StackNavigationProp, StackScreenProps} from '@react-navigation/stack';
import React, {useRef} from 'react';
import {StyleSheet, View} from 'react-native';
import {ScrollView} from 'react-native-gesture-handler';
import RouteMap from '@/components/map/RouteMap';
import routes from '@/assets/sample-route.json';
import CustomButton from '@/components/buttons/CustomButton';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import LightText from '@/components/text/LightText';
import Share from 'react-native-share';

export type Navigation = CompositeNavigationProp<
  StackNavigationProp<MyStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

/**
 * MyWalkDetailScreen 생성 시 전달되는 prop을 지정합니다
 */
export type MyWalkDetailProps = StackScreenProps<
  MyStackParamList,
  'MyWalkDetail'
>;

/**
 * 지정된 산책에 대한 상세 정보를 표시하는 화면입니다
 *
 * @returns MyWalkDetailScreen
 */
export default function MyWalkDetailScreen({route}: MyWalkDetailProps) {
  const navigation = useNavigation<Navigation>();

  // parameter로 산책 ID 받아오기
  const {walkId} = route.params;
  console.debug(walkId);

  // 테스트 데이터
  const data = {
    title: '산책 나가버릴거야',
    distance: 1.83,
    elapsed: 5742,
    achievements: [
      {
        title: '우리팀 폭탄',
        description: '영욱님이 인정한 폭탄 돌리기의 폭탄 역할',
      },
      {
        title: '스택 오버플로우',
        description: '실망 스택이 쌓이다 못해 넘쳐버렸어요!',
      },
    ],
  };

  // 소요 시간 문자열화
  const hours = Math.floor(data.elapsed / 3600);
  const minutes = Math.floor((data.elapsed - hours * 3600) / 60);
  const seconds = data.elapsed - hours * 3600 - minutes * 60;
  const elapsed =
    (hours < 10 ? '0' + hours : hours) +
    ':' +
    (minutes < 10 ? '0' + minutes : minutes) +
    ':' +
    (seconds < 10 ? '0' + seconds : seconds);

  // 지도 이미지 공유
  const map = useRef<RouteMap | null>(null);
  const onMapShare = async () => {
    try {
      const base64 = await map.current?.takeSnapshot({
        format: 'jpg',
        result: 'base64',
      });
      console.debug(
        await Share.open({
          title: '지도 공유',
          url: base64,
          type: 'image/jpeg',
          filename: 'map',
          failOnCancel: false,
        }),
      );
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <ScrollView>
      <MainContainer style={styles.page}>
        <HeadingText
          adjustsFontSizeToFit={true}
          numberOfLines={1}
          style={styles.title}>
          {data.title}
        </HeadingText>

        <View style={styles.mapContainer}>
          <RouteMap routes={routes} ref={map} />
          <CustomButton
            backgroundColor="transparent"
            containerStyle={styles.shareButtonContainer}
            style={styles.shareButton}
            onPress={onMapShare}>
            <MaterialIcons name="share" size={16} color={colors.white} />
            <LightText>지도 공유</LightText>
          </CustomButton>
        </View>

        <WalkResult time={elapsed} distance={data.distance} />
        <Achievement achievs={data.achievements} />
        <EventResult />
        <RecordedPost />
        <CustomTextButton
          backgroundColor={colors.primaryColorBlue}
          label="확인"
          onPress={() => {
            navigation.goBack();
          }}
        />
      </MainContainer>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  page: {
    paddingTop: 30,
    gap: 30,
    alignItems: 'stretch',
  },
  title: {
    textAlign: 'center',
  },
  mapContainer: {
    gap: 10,
  },
  shareButtonContainer: {
    alignSelf: 'flex-end',
  },
  shareButton: {
    flexDirection: 'row',
    padding: 10,
    gap: 5,
  },
});
