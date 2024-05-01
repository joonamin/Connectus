import React from 'react';
import {StyleSheet, TextProps} from 'react-native';
import MainText from '@/components/text/MainText';

/**
 * MainText보다 작은 텍스트를 포함하는 요소를 생성합니다
 *
 * @returns LightText
 */
export default function LightText({children, style, ...props}: TextProps) {
  return (
    <MainText style={[styles.mainText, style]} {...props}>
      {children}
    </MainText>
  );
}

const styles = StyleSheet.create({
  mainText: {
    fontSize: 16,
    fontFamily: 'GmarketSansTTFLight',
  },
});
