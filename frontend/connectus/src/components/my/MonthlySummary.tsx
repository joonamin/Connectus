import React from 'react';
import {StyleSheet, View, ViewProps} from 'react-native';
import ListItem from '@/components/containers/ListItem';
import MainText from '@/components/text/MainText';
import LightText from '@/components/text/LightText';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import colors from '@/constants/colors';

/**
 * Badge를 생성 시 전달하는 인자를 지정합니다
 */
interface BadgeProps {
  /**
   * Badge 내 표시할 icon
   */
  icon: React.ReactNode;
  /**
   * 표시할 값
   */
  value: number | string;
  /**
   * 값에 대한 설명
   */
  label: string;
}

/**
 * 월별 요약 지수 표시 시 하나의 수치를 생성합니다
 *
 * @returns Badge
 */
function Badge({icon, value, label}: BadgeProps) {
  return (
    <View style={styles.badge}>
      {icon}
      <MainText>{value}</MainText>
      <LightText>{label}</LightText>
    </View>
  );
}

/**
 * MonthlySummary를 생성 시 전달하는 인자를 지정합니다
 */
export interface MonthlySummaryProps extends ViewProps {
  /**
   * 산책 횟수
   */
  daysWalked: number;
  /**
   * 산책 거리
   */
  distance: number;
  /**
   * 산책 시간
   */
  timeSpent: number;
}

/**
 * 월별 산책 요약 지수를 표시합니다
 *
 * @returns MonthlySummary
 */
export default function MonthlySummary({
  daysWalked,
  distance,
  timeSpent,
  ...props
}: MonthlySummaryProps) {
  const hours = Math.floor(timeSpent / 3600);
  const minutes = Math.floor((timeSpent - hours * 3600) / 60);

  return (
    <ListItem {...props}>
      <Badge
        icon={<MaterialCommunityIcons name="calendar" style={styles.icon} />}
        value={daysWalked}
        label="산책 횟수"
      />
      <Badge
        icon={<MaterialIcons name="location-pin" style={styles.icon} />}
        value={distance.toFixed(2)}
        label="산책 거리 (km)"
      />
      <Badge
        icon={
          <MaterialCommunityIcons name="timer-outline" style={styles.icon} />
        }
        value={
          hours.toString().padStart(2, '0') +
          ':' +
          minutes.toString().padStart(2, '0')
        }
        label="산책 시간"
      />
    </ListItem>
  );
}

const styles = StyleSheet.create({
  icon: {
    fontSize: 36,
    color: colors.white,
  },
  badge: {
    flex: 1,
    gap: 5,
    alignItems: 'center',
  },
});
