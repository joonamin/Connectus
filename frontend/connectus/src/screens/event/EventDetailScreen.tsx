import {StyleSheet, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import {fonts, queryKeys} from '@/constants';
import {StackScreenProps} from '@react-navigation/stack';
import {EventStackParamList} from '@/navigations/stack/EventStackNavigator';
import {useQuery} from '@tanstack/react-query';
import {getEventDetails} from '@/api/event';

/**
 * 이벤트 id혹은 이벤트 상세내용을 받아와서 이벤트 상세 내용을 표시합니다
 */
export default function EventDetailScreen({
  route,
}: StackScreenProps<EventStackParamList, 'EventDetail'>) {
  const eventId = route.params.eventId;

  const {isPending, isError, data} = useQuery({
    queryKey: [queryKeys.GET_EVENT_DETAILS, eventId],
    queryFn: async () => (await getEventDetails(eventId)).data,
  });

  if (isPending) {
    return (
      <MainContainer style={styles.mainContainer}>
        <MainText>이벤트 정보를 불러오는 중입니다</MainText>
      </MainContainer>
    );
  } else if (isError) {
    return (
      <MainContainer style={styles.mainContainer}>
        <MainText>이벤트 정보를 불러오는 데 실패했습니다</MainText>
      </MainContainer>
    );
  }

  const formatter = Intl.DateTimeFormat('ko-KR', {dateStyle: 'long'});

  return (
    <MainContainer style={styles.mainContainer}>
      <View style={[styles.center, styles.borderBottom]}>
        <MainText>{data.title}</MainText>
      </View>
      <View style={[styles.center, styles.content]}>
        <MainText>{data.description}</MainText>
      </View>
      <View style={[styles.center, styles.borderBottom]}>
        <MainText>참여 보상</MainText>
      </View>
      <View>
        <MainText>{`${data.reward} 포인트`}</MainText>
      </View>
      {/* {isParticipated && (
        <Pressable style={styles.submitButton}>
          <MainText>이미 참여한 이벤트입니다</MainText>
        </Pressable>
      )}
      {!isParticipated && (
        <Pressable style={styles.submitButton}>
          <MainText>이벤트 참여하기</MainText>
        </Pressable>
      )} */}
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  mainContainer: {flex: 1, alignItems: 'center', gap: 15},
  center: {
    width: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  borderBottom: {
    borderBottomWidth: 5,
    paddingBottom: 10,
    borderColor: colors.white,
  },
  durationContainer: {
    paddingTop: 5,
  },
  durationText: {
    fontFamily: fonts.medium,
    color: colors.white,
  },
  content: {
    height: 250,
    borderStyle: 'dotted',
    borderWidth: 3,
    borderColor: colors.white,
  },
  submitButton: {
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 15,
    borderRadius: 15,
    backgroundColor: colors.primaryColorBlue,
    position: 'absolute',
    bottom: 15,
  },
});
