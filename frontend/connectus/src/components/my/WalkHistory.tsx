import {
  OnWalkSelectedContext,
  WalkHistoryMonthContext,
  WalkHistoryYearContext,
} from '@/contexts/WalkHistoryContext';
import React from 'react';
import {StyleSheet, View} from 'react-native';
import MonthlyWalkHistory from '@/components/my/MonthlyWalkHistory';

/**
 * {@link WalkHistory}를 생성할 시 전달하는 인자를 지정합니다
 */
export interface WalkHistoryProps {
  /**
   * 월별 요약 정보 표시 여부를 지정합니다
   */
  showSummary?: boolean;
  /**
   * 특정한 산책 기록을 선택할 시 실행할 callback 객체입니다
   *
   * @param walkId 전달되는 산책 ID
   */
  onWalkSelected?: (walkId: number) => void;
}

/**
 * 외출 기록을 표시합니다
 *
 * @returns WalkHistory
 */
export default function WalkHistory({
  showSummary,
  onWalkSelected,
}: WalkHistoryProps) {
  // 시험용 데이터
  const history = [
    {
      year: 2024,
      month: 4,
      summary: {
        daysWalked: 14,
        distance: 12.43,
        timeSpent: 155520,
      },
      list: [
        {
          day: 15,
          list: [{id: 11}, {id: 10}],
        },
        {
          day: 14,
          list: [{id: 9}, {id: 8}, {id: 7}],
        },
      ],
    },
    {
      year: 2024,
      month: 3,
      summary: {
        daysWalked: 14,
        distance: 12.43,
        timeSpent: 155520,
      },
      list: [
        {
          month: 3,
          day: 26,
          list: [{id: 6}, {id: 5}],
        },
        {
          day: 21,
          list: [{id: 4}],
        },
        {
          day: 18,
          list: [{id: 3}, {id: 2}, {id: 1}],
        },
      ],
    },
  ];

  // 기본 설정 지정
  if (typeof showSummary === 'undefined') {
    showSummary = true;
  }

  return (
    <View style={styles.page}>
      <OnWalkSelectedContext.Provider
        value={onWalkSelected ? onWalkSelected : (walkId: number) => {}}>
        {/* 월별 데이터 반복 출력 */}
        {history.map(item => (
          <WalkHistoryYearContext.Provider
            key={new Date(item.year, item.month - 1).toISOString()}
            value={item.year}>
            <WalkHistoryMonthContext.Provider value={item.month}>
              <MonthlyWalkHistory
                summary={item.summary}
                showSummary={showSummary}
                list={item.list}
              />
            </WalkHistoryMonthContext.Provider>
          </WalkHistoryYearContext.Provider>
        ))}
      </OnWalkSelectedContext.Provider>
    </View>
  );
}

const styles = StyleSheet.create({
  page: {
    paddingTop: 15,
    gap: 30,
  },
});
