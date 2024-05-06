import MainContainer from '@/components/containers/MainContainer';
import MonthlySummary from '@/components/my/MonthlySummary';
import HeadingText from '@/components/text/HeadingText';
import colors from '@/constants/colors';
import React from 'react';
import {Image, ScrollView, StyleSheet, View} from 'react-native';

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
          id: 5,
        },
        {
          id: 4,
        },
        {
          id: 3,
        },
        {
          id: 2,
        },
        {
          id: 1,
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
          id: 5,
        },
        {
          id: 4,
        },
        {
          id: 3,
        },
        {
          id: 2,
        },
        {
          id: 1,
        },
      ],
    },
  ];
  // 기본 이미지
  const defaultImage = require('@/assets/map.png');

  return (
    <ScrollView>
      <View style={styles.page}>
        {history.map(item => (
          <View
            key={
              item.year + '-' + (item.month < 10)
                ? '0' + item.month
                : item.month
            }
            style={styles.month}>
            <MainContainer style={styles.monthlyItem}>
              <HeadingText>
                {item.year}년 {item.month}월
              </HeadingText>
            </MainContainer>
            <MainContainer style={styles.monthlyItem}>
              <MonthlySummary
                daysWalked={item.summary.daysWalked}
                distance={item.summary.distance}
                timeSpent={item.summary.timeSpent}
              />
            </MainContainer>
            <View style={styles.historyGrid}>
              {item.list.map(historyItem => (
                <Image
                  key={historyItem.id}
                  style={styles.historyItem}
                  source={defaultImage}
                />
              ))}
            </View>
          </View>
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
  historyItem: {
    flex: 1,
    aspectRatio: '1 / 1',
    flexBasis: '33.33333%',
    flexGrow: 0,
    flexShrink: 0,
    resizeMode: 'cover',
    borderWidth: 1,
    borderColor: colors.background,
  },
});
