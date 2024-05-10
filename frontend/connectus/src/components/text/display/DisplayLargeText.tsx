import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  displayLarge: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 57,
    letterSpacing: -0.25,
    lineHeight: 64,
  },
});

export default function DisplayLargeText({
  style,
  children,
  ...props
}: TextProps) {
  return (
    <Text style={[styles.displayLarge, style]} {...props}>
      {children}
    </Text>
  );
}
