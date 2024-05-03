import React from 'react';
import {Image, ImageProps, StyleSheet} from 'react-native';

/**
 * AchievementIcon을 생성할 시 사용하는 인자를 지정합니다
 */
export interface AchievementIconProps extends ImageProps {
  /**
   * 아이콘의 URL 또는 이미지 source
   */
  icon?: string | ImageProps['source'];
}

/**
 * 업적의 아이콘을 생성합니다
 *
 * @returns AchievementIcon
 */
export default function AchievementIcon({
  icon,
  source,
  style,
  ...props
}: AchievementIconProps) {
  if (typeof icon === 'string') {
    source = {uri: icon};
  } else if (typeof icon !== 'undefined') {
    source = icon;
  } else {
    if (typeof source === 'undefined') {
      source = require('@/assets/default-achievement.png');
    }
  }

  return <Image source={source} style={[styles.icon, style]} {...props} />;
}

const styles = StyleSheet.create({
  icon: {
    flexBasis: 64,
    aspectRatio: '1 / 1',
  },
});
