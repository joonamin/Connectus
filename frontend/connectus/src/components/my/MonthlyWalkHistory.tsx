import React, {useContext} from 'react';
import DailyWalkHistory from '@/components/my/DailyWalkHistory';
import {StyleSheet, View} from 'react-native';
import MainContainer from '@/components/containers/MainContainer';
import HeadingText from '@/components/text/HeadingText';
import MonthlySummary, {
  MonthlySummaryProps,
} from '@/components/my/MonthlySummary';
import {
  WalkHistoryMonthContext,
  WalkHistoryYearContext,
} from '@/contexts/WalkHistoryContext';
import {Walk} from '@/types';
import {groupBy} from '@/utils/arrays';

/**
 * MonthlyWalkHistory의 인자를 지정합니다
 */
export interface MonthlyWalkHistoryProps {
  /**
   * 월별 산책 데이터
   */
  data: Walk[];
  /**
   * 월별 요약 정보 표시 여부를 지정합니다
   */
  showSummary?: boolean;
}

/**
 * 월별 산책 기록을 표시합니다
 *
 * @returns MonthlyWalkHistory
 */
export default function MonthlyWalkHistory({
  data,
  showSummary,
}: MonthlyWalkHistoryProps) {
  const year = useContext(WalkHistoryYearContext);
  const month = useContext(WalkHistoryMonthContext);

  // 요약 정보 계산
  const summary: MonthlySummaryProps = {
    daysWalked: data.length,
    distance: 0,
    timeSpent: 0,
  };
  data.forEach(walk => {
    summary.distance += walk.walkDistance;
    summary.timeSpent += walk.walkTime;
  });

  // 일별 데이터 그룹화
  const groups = groupBy(data, walk =>
    walk.updatedAt.getDate().toString(10).padStart(2, '0'),
  );

  return (
    <View style={styles.month}>
      {/* 해당 월 이름 */}
      <MainContainer style={styles.monthlyItem}>
        <HeadingText>
          {year}년 {month}월
        </HeadingText>
      </MainContainer>
      {/* 해당 월 요약 정보 */}
      {showSummary ? (
        <MainContainer style={styles.monthlyItem}>
          <MonthlySummary {...summary} />
        </MainContainer>
      ) : (
        <></>
      )}
      {/* 해당 월 산책 기록 */}
      <View style={styles.historyGrid}>
        {Object.keys(groups)
          .sort()
          .reverse()
          .map(day => (
            <DailyWalkHistory
              key={day}
              day={
                day.charAt(0) === '0'
                  ? Number.parseInt(day.substring(1), 10)
                  : Number.parseInt(day, 10)
              }
              data={groups[day]}
            />
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
