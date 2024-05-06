import MainContainer from '@/components/containers/MainContainer';
import MonthlySummary from '@/components/my/MonthlySummary';
import HeadingText from '@/components/text/HeadingText';
import MainText from '@/components/text/MainText';
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
          day: 15,
          list: [
            {
              id: 11,
            },
            {
              id: 10,
            },
          ],
        },
        {
          day: 14,
          list: [
            {
              id: 9,
            },
            {
              id: 8,
            },
            {
              id: 7,
            },
          ],
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
          day: 26,
          list: [
            {
              id: 6,
            },
            {
              id: 5,
            },
          ],
        },
        {
          day: 21,
          list: [
            {
              id: 4,
            },
          ],
        },
        {
          day: 18,
          list: [
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
              {item.list.map(historyMonth => (
                <>
                  <MainText style={styles.dateHeading}>
                    {item.month}월 {historyMonth.day}일
                  </MainText>
                  {historyMonth.list.map(historyDay => (
                    <Image
                      key={historyDay.id}
                      style={styles.historyItem}
                      source={defaultImage}
                    />
                  ))}
                </>
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
  dateHeading: {
    flex: 3,
    padding: 15,
    flexBasis: '100%',
    flexShrink: 0,
  },
});
