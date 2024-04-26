import React from 'react';
import {StyleSheet, ViewProps} from 'react-native';
import colors from '@/constants/colors';
import MainContainer from './MainContainer';

/**
 * List item을 생성합니다
 *
 * @returns ListItem
 */
export default function ListItem({children, style, ...props}: ViewProps) {
  return (
    <MainContainer style={[styles.item, style]} {...props}>
      {children}
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  item: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: colors.buttonBackground,
    width: '100%',
    borderRadius: 15,
  },
});
