import React from 'react';
import {Image, StyleSheet, ViewProps} from 'react-native';
import ListItem from '@/components/containers/ListItem';
import MainText from '@/components/text/MainText';
import CustomButton, {
  CustomButtonProps,
} from '@/components/buttons/CustomButton';
import {Achievement} from '@/types';

/**
 * AchievementItem을 생성하기 위한 인자를 지정합니다
 */
export interface AchievementItemProps extends Achievement, ViewProps {
  /**
   * 버튼 선택 시 callback
   */
  onPress?: CustomButtonProps['onPress'];
}

/**
 * 하나의 업적을 표현하는 item 요소를 생성합니다
 *
 * @returns AchievementItem
 */
export default function AchievementItem({
  imageUrl,
  title,
  content,
  style,
  onPress,
  ...props
}: AchievementItemProps) {
  const styles = StyleSheet.create({
    item: {
      flexDirection: 'row',
      gap: 15,
    },
    icon: {
      flexGrow: 0,
    },
  });

  return (
    <CustomButton onPress={onPress}>
      <ListItem style={[styles.item, style]} {...props}>
        {imageUrl ? <Image style={styles.icon} src={imageUrl} /> : undefined}
        <MainText>{title}</MainText>
      </ListItem>
    </CustomButton>
  );
}
