import MainContainer from '@/components/containers/MainContainer';
import React from 'react';
import {Pressable, StyleSheet, Text, View} from 'react-native';

/**
 * 마커 클러스터를 press시, 클러스터가 가지고있는 Marker의 배열을 전부 전달받아
 * 버튼으로 연결시키고 방명록 / 모여라 / 이벤트 마커를 확인할 수 있습니다.
 * @returns
 */
export default function MarkerSelectScreen() {
  return (
    <MainContainer>
      <Pressable>
        <Text>방명록 목록 확인</Text>
      </Pressable>
      <Pressable>
        <Text>모여라 목록 확인</Text>
      </Pressable>
    </MainContainer>
  );
}

const styles = StyleSheet.create({});
