import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  bodyLarge: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 16,
    letterSpacing: 0.5,
    lineHeight: 24,
  },
});

export default function BodyLargeText({style, children, ...props}: TextProps) {
  return (
    <Text style={[styles.bodyLarge, style]} {...props}>
      {children}
    </Text>
  );
}
