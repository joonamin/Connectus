import {ScrollView} from 'react-native';
import React from 'react';
import WalkHistory, {WalkHistoryProps} from '@/components/my/WalkHistory';

/**
 * 유저의 지난 산책 기록들을 선택해서 공유할 수 있는 스크린입니다.
 */
export default function ShareRecordScreen() {
  const onShareItemSelected: WalkHistoryProps['onWalkSelected'] = () => {
    // 공유할 기록 선택 시 관련 작업 수행
  };

  return (
    <ScrollView>
      <WalkHistory onWalkSelected={onShareItemSelected} showSummary={false} />
    </ScrollView>
  );
}
