import {
  Platform,
  StyleSheet,
  TouchableHighlight,
  TouchableNativeFeedback,
  TouchableWithoutFeedbackProps,
  View,
  ViewStyle,
} from 'react-native';
import React from 'react';
import colors from '@/constants/colors';

function Touchable({...buttonProps}: TouchableWithoutFeedbackProps) {
  // Android일 시 feedback이 존재하는 Touchable 요소를 사용합니다
  if (Platform.OS === 'android') {
    return (
      <TouchableNativeFeedback {...buttonProps}>
        {buttonProps.children}
      </TouchableNativeFeedback>
    );
  } else {
    return (
      <TouchableHighlight {...buttonProps}>
        {buttonProps.children}
      </TouchableHighlight>
    );
  }
}

/**
 * CustomButton 생성 시 전달 인자 타입을 지정합니다
 */
export interface CustomButtonProps extends TouchableWithoutFeedbackProps {
  /**
   * CustomButton의 배경 색을 지정합니다
   */
  backgroundColor?: ViewStyle['backgroundColor'];
}

/**
 * CustomButton을 생성합니다
 *
 * @returns CustomButton
 */
export default function CustomButton({
  backgroundColor,
  children,
  style,
  ...props
}: CustomButtonProps) {
  const styles = StyleSheet.create({
    container: {
      borderRadius: 15,
      justifyContent: 'center',
      alignItems: 'center',
      backgroundColor: backgroundColor
        ? backgroundColor
        : colors.primaryColorBlue,
      overflow: 'hidden',
    },
    button: {
      backgroundColor: 'transparent',
    },
  });

  return (
    <View style={[styles.container, style]}>
      <Touchable style={styles.button} {...props}>
        {children}
      </Touchable>
    </View>
  );
}
