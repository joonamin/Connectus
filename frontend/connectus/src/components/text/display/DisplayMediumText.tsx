import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  displayMedium: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 45,
    letterSpacing: 0,
    lineHeight: 52,
  },
});

export default function DisplayMediumText({
  style,
  children,
  ...props
}: TextProps) {
  return (
    <Text style={[styles.displayMedium, style]} {...props}>
      {children}
    </Text>
  );
}
