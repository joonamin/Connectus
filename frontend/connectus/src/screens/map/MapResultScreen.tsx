import {StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import MainText from '@/components/text/MainText';
import WalkResult from '@/components/map/WalkResult';
import Achievement from '@/components/map/Achievement';

const DUMMY_ACHIEVE = [
  {
    title: '우리팀 폭탄',
    description: '영욱님이 인정한 폭탄 돌리기의 폭탄 역할',
  },
];
/**
 *  산책 종료시 이동하게될 페이지입니다.
 */
export default function MapResultScreen() {
  return (
    <MainContainer style={styles.mainContainer}>
      <MainText>산책 종료</MainText>
      <WalkResult time="03:35:42" distance={13.42} />
      <Achievement achievs={[]} />
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    alignItems: 'center',
    gap: 20,
  },
});
