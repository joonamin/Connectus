import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  displaySmall: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 400,
    fontSize: 36,
    letterSpacing: 0,
    lineHeight: 44,
  },
});

export default function DisplaySmallText({
  style,
  children,
  ...props
}: TextProps) {
  return (
    <Text style={[styles.displaySmall, style]} {...props}>
      {children}
    </Text>
  );
}
