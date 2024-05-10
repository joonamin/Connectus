import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  bodySmall: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 12,
    letterSpacing: 0.4,
    lineHeight: 16,
  },
});

export default function BodySmallText({style, children, ...props}: TextProps) {
  return (
    <Text style={[styles.bodySmall, style]} {...props}>
      {children}
    </Text>
  );
}
