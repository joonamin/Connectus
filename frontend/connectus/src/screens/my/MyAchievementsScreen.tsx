import MainContainer from '@/components/containers/MainContainer';
import AchievementItem, {
  AchievementItemProps,
} from '@/components/my/AchievementItem';
import MainText from '@/components/text/MainText';
import React from 'react';
import {ScrollView, StyleSheet, View} from 'react-native';

/**
 * 업적 페이지입니다
 *
 * @returns MyAchievementsScreen
 */
export default function MyAchievementsScreen() {
  const styles = StyleSheet.create({
    container: {
      gap: 30,
    },
    section: {
      gap: 15,
    },
  });

  // 업적 시험 데이터
  const achievements: AchievementItemProps[] = [
    {title: '꾸준한 운동가'},
    {title: '위대한 첫걸음'},
  ];
  const incompleteAchievements: AchievementItemProps[] = [
    {title: '그를 찬양해야해'},
    {title: '위대한 갓진주'},
    {title: 'CI/CD의 신'},
  ];

  return (
    <ScrollView>
      <MainContainer style={styles.container}>
        {/* 달성 업적 */}
        <View style={styles.section}>
          <MainText>
            {'한방 옻닭'}님의 업적 달성 갯수는 {2}개
          </MainText>
          {achievements.map((item: AchievementItemProps) => (
            <AchievementItem key={item.title} title={item.title} />
          ))}
        </View>
        {/* 미달성 업적 */}
        <View style={styles.section}>
          <MainText>{'한방 옻닭'}님의 미달성 업적</MainText>
          {incompleteAchievements.map((item: AchievementItemProps) => (
            <AchievementItem key={item.title} title={item.title} />
          ))}
        </View>
      </MainContainer>
    </ScrollView>
  );
}
