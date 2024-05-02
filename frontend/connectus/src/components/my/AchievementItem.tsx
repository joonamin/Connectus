import React from 'react';
import {Image, ImageSourcePropType, StyleSheet, ViewProps} from 'react-native';
import ListItem from '@/components/containers/ListItem';
import MainText from '../text/MainText';

export interface AchievementItemProps extends ViewProps {
  icon?: string;
  title: string;
  description?: string;
}

export default function AchievementItem({
  icon,
  title,
  style,
  ...props
}: AchievementItemProps) {
  const styles = StyleSheet.create({
    item: {
      flexDirection: 'row',
      gap: 15,
    },
    icon: {
      flexBasis: 64,
      flexGrow: 0,
      aspectRatio: '1 / 1',
    },
  });

  // 업적 이미지 지정
  let source: ImageSourcePropType | undefined;
  if (typeof icon !== 'undefined') {
    source = {uri: icon};
  } else {
    source = require('@/assets/default-achievement.png');
  }

  return (
    <ListItem style={[styles.item, style]} {...props}>
      <Image style={styles.icon} source={source} />
      <MainText>{title}</MainText>
    </ListItem>
  );
}
