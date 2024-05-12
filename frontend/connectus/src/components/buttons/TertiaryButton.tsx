import {StyleSheet, TextProps} from 'react-native';
import React from 'react';
import {defaultColors} from '@/constants/colors';
import LabelLargeText from '@/components/text/label/LabelLargeText';
import ButtonBase, {ButtonBaseProps} from '@/components/buttons/ButtonBase';

export type TertiaryButtonProps = ButtonBaseProps;

export default function TertiaryButton({...props}: ButtonBaseProps) {
  return <ButtonBase wrapperStyle={styles.buttonWrapper} {...props} />;
}

export function TertiaryButtonText({style, ...props}: TextProps) {
  return <LabelLargeText style={[styles.text, style]} {...props} />;
}

const styles = StyleSheet.create({
  buttonWrapper: {
    backgroundColor: defaultColors.tertiaryContainer,
  },
  text: {
    color: defaultColors.onTertiaryContainer,
  },
});
