import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  headlineMedium: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 28,
    letterSpacing: 0,
    lineHeight: 36,
  },
});

export default function HeadlineMediumText({
  style,
  children,
  ...props
}: TextProps) {
  return (
    <Text style={[styles.headlineMedium, style]} {...props}>
      {children}
    </Text>
  );
}
