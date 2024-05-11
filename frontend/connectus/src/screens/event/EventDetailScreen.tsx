import {Pressable, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import {fonts} from '@/constants';

/**
 * 이벤트 id혹은 이벤트 상세내용을 받아와서 이벤트 상세 내용을 표시합니다
 */
export default function EventDetailScreen() {
  const isParticipated = false;
  return (
    <MainContainer style={styles.mainContainer}>
      <View style={[styles.center, styles.borderBottom]}>
        <MainText>{'이벤트 타이틀'}</MainText>
      </View>
      <View style={[styles.center, styles.durationContainer]}>
        <Text style={styles.durationText}>
          개최기간 : {'2024-05-10'} ~ {'2024-05-17'}
        </Text>
      </View>
      <View style={[styles.center, styles.content]}>
        <MainText>{'이벤트 소개 내용'}</MainText>
      </View>
      <View style={[styles.center, styles.borderBottom]}>
        <MainText>참여 보상</MainText>
      </View>
      <View>
        <MainText>{'500포인트'}</MainText>
      </View>
      {isParticipated && (
        <Pressable style={styles.submitButton}>
          <MainText>이미 참여한 이벤트입니다</MainText>
        </Pressable>
      )}
      {!isParticipated && (
        <Pressable style={styles.submitButton}>
          <MainText>이벤트 참여하기</MainText>
        </Pressable>
      )}
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
