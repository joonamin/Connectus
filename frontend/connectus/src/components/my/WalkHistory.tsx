import {
  OnWalkSelectedContext,
  WalkHistoryMonthContext,
  WalkHistoryYearContext,
} from '@/contexts/WalkHistoryContext';
import React from 'react';
import {StyleSheet, View, ViewProps} from 'react-native';
import MonthlyWalkHistory from '@/components/my/MonthlyWalkHistory';
import {useQuery} from '@tanstack/react-query';
import {queryKeys} from '@/constants';
import useAuthStore from '@/store/useAuthStore';
import {getUserRoute} from '@/api/walk';
import {groupBy} from '@/utils/arrays';
import MainContainer from '../containers/MainContainer';
import LightText from '../text/LightText';

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
 * Query 사용 중 표시되는 페이지 영역을 지정합니다
 *
 * @returns Status
 */
const Status = ({children, ...props}: ViewProps) => (
  <MainContainer style={styles.statusPage} {...props}>
    {children}
  </MainContainer>
);

/**
 * 외출 기록을 표시합니다
 *
 * @returns WalkHistory
 */
export default function WalkHistory({
  showSummary,
  onWalkSelected,
}: WalkHistoryProps) {
  // 기본 설정 지정
  if (typeof showSummary === 'undefined') {
    showSummary = true;
  }

  // 데이터 얻어오기
  const {user} = useAuthStore();
  const {isPending, isError, data} = useQuery({
    queryKey: [queryKeys.GET_WALKS_BY_USER],
    queryFn: () => getUserRoute(user?.userId as number),
  });

  // 산책 기록 query 관련 상황 대비
  if (isPending) {
    return (
      <Status>
        <LightText>산책 기록을 불러오는 중입니다</LightText>
      </Status>
    );
  } else if (isError) {
    return (
      <Status>
        <LightText>산책 기록을 불러오는 데 실패했습니다</LightText>
      </Status>
    );
  }

  // 산책 기록 존재 여부 확인
  if (data.walks.length === 0) {
    return (
      <Status>
        <LightText>산책 기록이 없습니다</LightText>
      </Status>
    );
  }

  // 데이터 정리하기
  const groups = groupBy(
    typeof data?.walks !== 'undefined' ? data.walks : [],
    walk =>
      `${walk.updatedAt.getFullYear()}` +
      `${walk.updatedAt.getMonth() + 1}`.padStart(2, '0'),
  );

  return (
    <View style={styles.page}>
      <OnWalkSelectedContext.Provider
        value={onWalkSelected ? onWalkSelected : (_walkId: number) => {}}>
        {/* 월별 데이터 반복 출력 */}
        {Object.keys(groups)
          .sort()
          .reverse()
          .map(month => (
            <WalkHistoryYearContext.Provider
              key={month}
              value={Number.parseInt(month.substring(0, 4), 10)}>
              <WalkHistoryMonthContext.Provider
                value={
                  month.charAt(4) === '0'
                    ? Number.parseInt(month.substring(5), 10)
                    : Number.parseInt(month.substring(4), 10)
                }>
                <MonthlyWalkHistory
                  data={groups[month]}
                  showSummary={showSummary}
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
  statusPage: {
    alignItems: 'center',
  },
});
