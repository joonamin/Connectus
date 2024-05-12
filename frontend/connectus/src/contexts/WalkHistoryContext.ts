import {WalkHistoryProps} from '@/components/my/WalkHistory';
import {createContext} from 'react';

/**
 * 외출 기록의 연도를 저장하는 context입니다
 */
export const WalkHistoryYearContext = createContext<number>(0);

/**
 * 외출 기록의 월 정보를 저장하는 context입니다
 */
export const WalkHistoryMonthContext = createContext<number>(0);

/**
 * 외출 기록 선택 시 실행할 callback 객체를 저장하는 context입니다
 */
export const OnWalkSelectedContext =
  createContext<NonNullable<WalkHistoryProps['onWalkSelected']>>((walkId: number) => {});
