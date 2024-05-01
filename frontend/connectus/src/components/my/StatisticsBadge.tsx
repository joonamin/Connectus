import React from "react";
import { StyleSheet, View, ViewProps } from "react-native";
import MainText from "@/components/text/MainText";
import LightText from "@/components/text/LightText";

/**
 * StatisticsBadge를 생성하기 위한 인자 값을 지정합니다
 */
export interface StatisticsBadgeProps extends ViewProps {
  /**
   * Badge에 표시할 값
   */
  value: number,
  /**
   * 값에 대한 설명
   */
  label: string
}

/**
 * 특정한 값에 대한 badge를 생성합니다
 *
 * @returns StatisticsBadge
 */
export default function StatisticsBadge({value, label, style, ...props}: StatisticsBadgeProps) {
  const styles = StyleSheet.create({
    badge: {
      alignItems: 'center',
      gap: 5,
    },
  })

  return (
    <View style={[styles.badge, style]} {...props}>
      <MainText>{value}</MainText>
      <LightText>{label}</LightText>
    </View>
  )
}
