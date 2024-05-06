import MainText from '@/components/text/MainText';
import {MyStackParamList} from '@/navigations/stack/MyStackNavigator';
import {StackScreenProps} from '@react-navigation/stack';
import React from 'react';

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
  // parameter로 산책 ID 받아오기
  const {walkId} = route.params;

  return <MainText>{walkId}</MainText>;
}
