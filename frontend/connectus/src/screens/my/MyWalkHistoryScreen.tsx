import CustomButton from '@/components/buttons/CustomButton';
import MainContainer from '@/components/containers/MainContainer';
import MonthlySummary from '@/components/my/MonthlySummary';
import HeadingText from '@/components/text/HeadingText';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import {MyStackParamList} from '@/navigations/stack/MyStackNavigator';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import {StackNavigationProp} from '@react-navigation/stack';
import React from 'react';
import {Image, ScrollView, StyleSheet, View} from 'react-native';

type Navigation = CompositeNavigationProp<
  StackNavigationProp<MyStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

/**
 * 산책 기록을 표시합니다
 *
 * @returns MyWalkHistoryScreen
 */
export default function MyWalkHistoryScreen() {
  const navigation = useNavigation<Navigation>();

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
        {/* 월별 데이터 반복 출력 */}
        {history.map(item => (
          <View
            key={
              item.year + '-' + (item.month < 10)
                ? '0' + item.month
                : item.month
            }
            style={styles.month}>
            {/* 해당 월 이름 */}
            <MainContainer style={styles.monthlyItem}>
              <HeadingText>
                {item.year}년 {item.month}월
              </HeadingText>
            </MainContainer>
            {/* 해당 월 요약 정보 */}
            <MainContainer style={styles.monthlyItem}>
              <MonthlySummary
                daysWalked={item.summary.daysWalked}
                distance={item.summary.distance}
                timeSpent={item.summary.timeSpent}
              />
            </MainContainer>
            {/* 해당 월 산책 기록 */}
            <View style={styles.historyGrid}>
              {item.list.map(historyDay => (
                <>
                  {/* 산책 기록을 일별로 분리 */}
                  <MainText style={styles.dateHeading}>
                    {item.month}월 {historyDay.day}일
                  </MainText>
                  {historyDay.list.map(historyItem => (
                    <CustomButton
                      style={styles.historyItem}
                      backgroundColor="transparent"
                      onPress={() => {
                        navigation.navigate('MyWalkDetail', {
                          walkId: historyItem.id,
                        });
                      }}>
                      <Image
                        key={historyItem.id}
                        style={styles.historyItemImage}
                        source={defaultImage}
                      />
                    </CustomButton>
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
    borderRadius: 0,
  },
  historyItemImage: {
    flexBasis: '100%',
    flexShrink: 0,
    resizeMode: 'cover',
  },
  dateHeading: {
    flex: 3,
    padding: 15,
    flexBasis: '100%',
    flexShrink: 0,
  },
});
