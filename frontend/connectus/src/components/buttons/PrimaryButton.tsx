import {StyleSheet, TextProps} from 'react-native';
import React from 'react';
import {defaultColors} from '@/constants/colors';
import LabelLargeText from '@/components/text/label/LabelLargeText';
import ButtonBase, {ButtonBaseProps} from '@/components/buttons/ButtonBase';

export type PrimaryButtonProps = ButtonBaseProps;

export default function PrimaryButton({...props}: ButtonBaseProps) {
  return <ButtonBase wrapperStyle={styles.buttonWrapper} {...props} />;
}

export function PrimaryButtonText({style, ...props}: TextProps) {
  return <LabelLargeText style={[styles.text, style]} {...props} />;
}

const styles = StyleSheet.create({
  buttonWrapper: {
    backgroundColor: defaultColors.primaryContainer,
  },
  text: {
    color: defaultColors.onPrimaryContainer,
  },
});
