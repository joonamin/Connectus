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
import CustomButton from '@/components/buttons/CustomButton';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import LightText from '@/components/text/LightText';
import Share from 'react-native-share';
import {useQuery} from '@tanstack/react-query';
import {queryKeys} from '@/constants';
import {getRouteDetail} from '@/api/walk';

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
  const map = useRef<RouteMap | null>(null);

  // parameter로 산책 ID 받아오기
  const {walkId} = route.params;

  // 산책 정보 불러오기
  const {isPending, isError, data} = useQuery({
    queryKey: [queryKeys.GET_ROUTE_DETAIL],
    queryFn: () => getRouteDetail(walkId),
  });

  // 소요 시간 문자열화
  const duration = data?.walkTime ? data.walkTime : 0;
  const hours = Math.floor(duration / 3600);
  const minutes = Math.floor((duration - hours * 3600) / 60);
  const seconds = duration - hours * 3600 - minutes * 60;
  const elapsed =
    (hours < 10 ? '0' + hours : hours) +
    ':' +
    (minutes < 10 ? '0' + minutes : minutes) +
    ':' +
    (seconds < 10 ? '0' + seconds : seconds);

  // 지도 이미지 공유
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

  if (isPending) {
    return (
      <MainContainer>
        <LightText>산책 정보를 불러오는 중입니다</LightText>
      </MainContainer>
    );
  } else if (isError) {
    return (
      <MainContainer>
        <LightText>산책 정보를 불러오는 데 실패했습니다</LightText>
      </MainContainer>
    );
  }

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
          <RouteMap routes={data.route} ref={map} />
          <CustomButton
            backgroundColor="transparent"
            containerStyle={styles.shareButtonContainer}
            style={styles.shareButton}
            onPress={onMapShare}>
            <MaterialIcons name="share" size={16} color={colors.white} />
            <LightText>지도 공유</LightText>
          </CustomButton>
        </View>

        <WalkResult time={elapsed} distance={data.walkDistance} />
        {/* <Achievement achievs={data.achievements} /> */}
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
