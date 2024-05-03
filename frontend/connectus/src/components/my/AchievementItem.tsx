import React from 'react';
import {StyleSheet, ViewProps} from 'react-native';
import ListItem from '@/components/containers/ListItem';
import MainText from '@/components/text/MainText';
import CustomButton, {
  CustomButtonProps,
} from '@/components/buttons/CustomButton';
import AchievementIcon from '@/components/my/AchievementIcon';

/**
 * AchievementItem을 생성하기 위한 인자를 지정합니다
 */
export interface AchievementItemProps extends ViewProps {
  /**
   * 업적을 표시할 icon
   *
   * Image 요소의 source 속성으로 지정됩니다
   */
  icon?: string;
  /**
   * 업적 이름
   */
  title: string;
  /**
   * 업적에 대한 설명
   */
  description?: string;
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
  icon,
  title,
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
        <AchievementIcon style={styles.icon} icon={icon} />
        <MainText>{title}</MainText>
      </ListItem>
    </CustomButton>
  );
}
