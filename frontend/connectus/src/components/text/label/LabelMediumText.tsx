import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  labelMedium: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 500,
    fontSize: 12,
    letterSpacing: 0.5,
    lineHeight: 16,
  },
});

export default function LabelMediumText({
  style,
  children,
  ...props
}: TextProps) {
  return (
    <Text style={[styles.labelMedium, style]} {...props}>
      {children}
    </Text>
  );
}
