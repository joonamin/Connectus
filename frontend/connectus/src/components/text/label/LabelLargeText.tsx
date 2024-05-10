import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  labelLarge: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 500,
    fontSize: 14,
    letterSpacing: 0.1,
    lineHeight: 20,
  },
});

export default function LabelLargeText({style, children, ...props}: TextProps) {
  return (
    <Text style={[styles.labelLarge, style]} {...props}>
      {children}
    </Text>
  );
}
