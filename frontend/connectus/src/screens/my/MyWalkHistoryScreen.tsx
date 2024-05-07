import MonthlyWalkHistory from '@/components/my/MonthlyWalkHistory';
import {
  WalkHistoryMonthContext,
  WalkHistoryYearContext,
} from '@/contexts/WalkHistoryContext';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import {MyStackParamList} from '@/navigations/stack/MyStackNavigator';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {CompositeNavigationProp} from '@react-navigation/native';
import {StackNavigationProp} from '@react-navigation/stack';
import React from 'react';
import {ScrollView, StyleSheet, View} from 'react-native';

export type Navigation = CompositeNavigationProp<
  StackNavigationProp<MyStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

/**
 * 산책 기록을 표시합니다
 *
 * @returns MyWalkHistoryScreen
 */
export default function MyWalkHistoryScreen() {
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

  return (
    <ScrollView>
      <View style={styles.page}>
        {/* 월별 데이터 반복 출력 */}
        {history.map(item => (
          <WalkHistoryYearContext.Provider
            key={new Date(item.year, item.month - 1).toISOString()}
            value={item.year}>
            <WalkHistoryMonthContext.Provider value={item.month}>
              <MonthlyWalkHistory summary={item.summary} list={item.list} />
            </WalkHistoryMonthContext.Provider>
          </WalkHistoryYearContext.Provider>
        ))}
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  page: {
    paddingTop: 15,
    gap: 30,
  },
});
