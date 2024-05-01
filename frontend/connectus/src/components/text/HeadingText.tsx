import React from 'react';
import {StyleSheet, TextProps} from 'react-native';
import MainText from '@/components/text/MainText';

/**
 * MainText보다 큰 텍스트를 포함하는 요소를 생성합니다
 *
 * @returns HeadingText
 */
export default function HeadingText({children, style, ...props}: TextProps) {
  return (
    <MainText style={[styles.mainText, style]} {...props}>
      {children}
    </MainText>
  );
}

const styles = StyleSheet.create({
  mainText: {
    fontSize: 32,
    fontFamily: 'GmarketSansTTFBold',
  },
});
