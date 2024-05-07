import React from 'react';
import CustomButton, {
  CustomButtonProps,
} from '@/components/buttons/CustomButton';
import MainText from '@/components/text/MainText';
import {StyleSheet} from 'react-native';

/**
 * CustomTextButton 생성 시 전달 인자 타입을 지정합니다
 */
export interface CustomTextButtonProps extends CustomButtonProps {
  /**
   * CustomTextButton 내 텍스트를 지정합니다
   */
  label: string;
}

/**
 * CustomTextButton을 생성합니다
 *
 * 텍스트를 포함한 CustomButton을 생성합니다
 *
 * @returns CustomTextButton
 */
export default function CustomTextButton({
  label,
  style,
  ...props
}: CustomTextButtonProps) {
  return (
    <CustomButton style={[styles.button, style]} {...props}>
      <MainText>{label}</MainText>
    </CustomButton>
  );
}

const styles = StyleSheet.create({
  button: {
    padding: 15,
    alignItems: 'center',
  },
  label: {
    padding: 15,
    textAlign: 'center',
  },
});
