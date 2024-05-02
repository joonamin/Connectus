import React from 'react';
import {StyleSheet, View} from 'react-native';
import CustomButton, {
  CustomButtonProps,
} from '@/components/buttons/CustomButton';
import IconListItem, {
  IconListItemProps,
} from '@/components/containers/IconListItem';
import colors from '@/constants/colors';

/**
 * IconItemButton 생성 지 전달 인자 타입을 지정합니다
 */
type IconItemButtonProps = CustomButtonProps & IconListItemProps;

/**
 * Icon을 포함한 list item의 버튼을 생성합니다
 *
 * @returns IconItemButton
 */
export default function IconItemButton({style, ...props}: IconItemButtonProps) {
  const styles = StyleSheet.create({
    buttonContainer: {
      borderRadius: 15,
      overflow: 'hidden',
    },
    listItem: {
      backgroundColor: 'transparent',
    },
  });

  const buttonProps: CustomButtonProps = {...props};
  const containerProps: IconListItemProps = {...props};

  // 기본 설정을 지정합니다
  if (!('backgroundColor' in buttonProps)) {
    buttonProps.backgroundColor = colors.buttonBackground;
  }
  if (!('actionable' in containerProps)) {
    containerProps.actionable = true;
  }

  return (
    <View style={styles.buttonContainer}>
      <CustomButton {...buttonProps}>
        <IconListItem style={[styles.listItem, style]} {...containerProps} />
      </CustomButton>
    </View>
  );
}
