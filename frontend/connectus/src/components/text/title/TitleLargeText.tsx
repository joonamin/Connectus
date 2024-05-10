import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  titleLarge: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 22,
    letterSpacing: 0,
    lineHeight: 28,
  },
});

export default function TitleLargeText({style, children, ...props}: TextProps) {
  return (
    <Text style={[styles.titleLarge, style]} {...props}>
      {children}
    </Text>
  );
}
