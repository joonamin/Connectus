import React, {useContext} from 'react';
import DailyWalkHistory, {
  DailyWalkHistoryProps,
} from '@/components/my/DailyWalkHistory';
import {StyleSheet, View} from 'react-native';
import MainContainer from '@/components/containers/MainContainer';
import HeadingText from '@/components/text/HeadingText';
import MonthlySummary from '@/components/my/MonthlySummary';
import {
  WalkHistoryMonthContext,
  WalkHistoryYearContext,
} from '@/contexts/WalkHistoryContext';

/**
 * MonthlyWalkHistory의 인자를 지정합니다
 */
export interface MonthlyWalkHistoryProps {
  /**
   * 해당 월 산책 기록 요약 정보
   */
  summary: {
    /**
     * 산책 횟수
     */
    daysWalked: number;
    /**
     * 외출 거리
     */
    distance: number;
    /**
     * 외출 시간
     */
    timeSpent: number;
  };
  /**
   * 해당 월 내 일별 산책 기록
   */
  list: DailyWalkHistoryProps[];
}

/**
 * 월별 산책 기록을 표시합니다
 *
 * @returns MonthlyWalkHistory
 */
export default function MonthlyWalkHistory({
  summary,
  list,
}: MonthlyWalkHistoryProps) {
  const year = useContext(WalkHistoryYearContext);
  const month = useContext(WalkHistoryMonthContext);

  return (
    <View style={styles.month}>
      {/* 해당 월 이름 */}
      <MainContainer style={styles.monthlyItem}>
        <HeadingText>
          {year}년 {month}월
        </HeadingText>
      </MainContainer>
      {/* 해당 월 요약 정보 */}
      <MainContainer style={styles.monthlyItem}>
        <MonthlySummary
          daysWalked={summary.daysWalked}
          distance={summary.distance}
          timeSpent={summary.timeSpent}
        />
      </MainContainer>
      {/* 해당 월 산책 기록 */}
      <View style={styles.historyGrid}>
        {list.map(item => (
          <DailyWalkHistory key={item.day} {...item} />
        ))}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  month: {
    gap: 15,
  },
  monthlyItem: {
    paddingVertical: 0,
  },
  historyGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'flex-start',
    gap: 0,
  },
});
