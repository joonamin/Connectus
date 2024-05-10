import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  headlineSmall: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 24,
    letterSpacing: 0,
    lineHeight: 32,
  },
});

export default function HeadlineSmallText({
  style,
  children,
  ...props
}: TextProps) {
  return (
    <Text style={[styles.headlineSmall, style]} {...props}>
      {children}
    </Text>
  );
}
