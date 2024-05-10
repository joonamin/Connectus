import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  titleMedium: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 500,
    fontSize: 16,
    letterSpacing: 0.15,
    lineHeight: 24,
  },
});

export default function TitleMediumText({
  style,
  children,
  ...props
}: TextProps) {
  return (
    <Text style={[styles.titleMedium, style]} {...props}>
      {children}
    </Text>
  );
}
