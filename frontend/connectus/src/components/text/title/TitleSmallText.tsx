import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  titleSmall: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 500,
    fontSize: 14,
    letterSpacing: 0.1,
    lineHeight: 20,
  },
});

export default function TitleSmallText({style, children, ...props}: TextProps) {
  return (
    <Text style={[styles.titleSmall, style]} {...props}>
      {children}
    </Text>
  );
}
