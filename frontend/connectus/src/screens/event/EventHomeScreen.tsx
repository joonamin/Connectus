import {Pressable, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';
import MainContainer from '@/components/containers/MainContainer';
import EventBanner from '@/components/event/EventBanner';
import colors from '@/constants/colors';
import {NavigationProp, useNavigation} from '@react-navigation/native';
import {EventStackParamList} from '@/navigations/stack/EventStackNavigator';
import {useQuery} from '@tanstack/react-query';
import {queryKeys} from '@/constants';
import {getOngoingEventList} from '@/api/event';
import LightText from '@/components/text/LightText';

type Navigation = NavigationProp<EventStackParamList>;

/**
 * 현재 진행중인 이벤트 들을 표시하고 우측하단 버튼으로 허가받은 유저에는 이벤트를 생성할 수 있습니다.
 */
export default function EventHomeScreen() {
  const navigation = useNavigation<Navigation>();
  const handlePressCreate = () => {
    navigation.navigate('EventCreate');
  };

  const {isPending, isError, data} = useQuery({
    queryKey: [queryKeys.GET_ONGOING_EVENT_LIST],
    queryFn: async () => (await getOngoingEventList()).data,
  });

  if (isPending) {
    return (
      <MainContainer style={styles.mainContainer}>
        <LightText>이벤트 목록을 불러오는 중입니다</LightText>
      </MainContainer>
    );
  } else if (isError) {
    return (
      <MainContainer style={styles.mainContainer}>
        <LightText>이벤트 목록을 불러오는 데 실패했습니다</LightText>
      </MainContainer>
    );
  }

  const list = data.eventList;

  return (
    <MainContainer style={styles.mainContainer}>
      {isPending ? (
        <LightText>이벤트 목록을 불러오는 중입니다</LightText>
      ) : isError ? (
        <LightText>이벤트 목록을 불러오는 데 실패했습니다</LightText>
      ) : list.length === 0 ? (
        <LightText>진행중인 이벤트가 없습니다</LightText>
      ) : (
        <EventBanner />
      )}
      <Pressable style={styles.rbButton} onPress={handlePressCreate}>
        <FontAwesome5 name="calendar-check" size={44} color={colors.white} />
      </Pressable>
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    alignContent: 'center',
  },
  rbButton: {
    position: 'absolute',
    justifyContent: 'center',
    alignItems: 'center',
    bottom: 15,
    right: 15,
    width: 77,
    height: 77,
    borderRadius: 50,
    backgroundColor: colors.primaryColorBlue,
  },
});
