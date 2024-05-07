import React, {useContext} from 'react';
import WalkHistoryThumbnail, {
  WalkHistoryThumbnailProps,
} from '@/components/my/WalkHistoryThumbnail';
import MainText from '@/components/text/MainText';
import {StyleSheet} from 'react-native';
import {WalkHistoryMonthContext} from '@/contexts/WalkHistoryContext';

/**
 * DailyWalkHistory의 인자를 지정합니다
 */
export interface DailyWalkHistoryProps {
  /**
   * 해당 날짜
   */
  day: number;
  /**
   * 해당 날짜 내 산책 기록 목록
   */
  list: WalkHistoryThumbnailProps[];
}

/**
 * 일별 산책 기록을 표시합니다
 *
 * @returns DailyWalkHistory
 */
export default function DailyWalkHistory({day, list}: DailyWalkHistoryProps) {
  const month = useContext(WalkHistoryMonthContext);

  return (
    <>
      {/* 산책 기록을 일별로 분리 */}
      <MainText style={styles.dateHeading}>
        {month}월 {day}일
      </MainText>
      {list.map(item => (
        <WalkHistoryThumbnail key={item.id} {...item} />
      ))}
    </>
  );
}

const styles = StyleSheet.create({
  dateHeading: {
    flex: 3,
    padding: 15,
    flexBasis: '100%',
    flexShrink: 0,
  },
});
