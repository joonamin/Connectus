import React from 'react';
import {Image, ImageSourcePropType, StyleSheet, ViewProps} from 'react-native';
import ListItem from '@/components/containers/ListItem';
import AchievementIcon from '@/components/my/AchievementIcon';
export interface AchievementItemProps extends ViewProps {
  icon?: string;
  title: string;
  description?: string;
  onPress?: CustomButtonProps['onPress'];
}

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
