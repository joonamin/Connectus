import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  headlineLarge: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 32,
    letterSpacing: 0,
    lineHeight: 40,
  },
});

export default function HeadlineLargeText({
  style,
  children,
  ...props
}: TextProps) {
  return (
    <Text style={[styles.headlineLarge, style]} {...props}>
      {children}
    </Text>
  );
}
