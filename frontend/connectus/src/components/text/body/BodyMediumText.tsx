import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  bodyMedium: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 14,
    letterSpacing: 0.25,
    lineHeight: 20,
  },
});

export default function BodyMediumText({style, children, ...props}: TextProps) {
  return (
    <Text style={[styles.bodyMedium, style]} {...props}>
      {children}
    </Text>
  );
}
