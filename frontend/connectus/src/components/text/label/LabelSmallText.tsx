import React from 'react';
import {StyleSheet, Text, TextProps} from 'react-native';

export const styles = StyleSheet.create({
  labelSmall: {
    fontFamily: 'GmarketSansTTFMedium',
    fontWeight: 500,
    fontSize: 11,
    letterSpacing: 0.5,
    lineHeight: 16,
  },
});

export default function LabelSmallText({style, children, ...props}: TextProps) {
  return (
    <Text style={[styles.labelSmall, style]} {...props}>
      {children}
    </Text>
  );
}
