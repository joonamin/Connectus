import {
  Platform,
  StyleSheet,
  TouchableHighlight,
  TouchableNativeFeedback,
  TouchableWithoutFeedbackProps,
  View,
  ViewProps,
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
  /**
   * Touchable 요소를 감싸는 container의 style을 지정합니다
   */
  containerStyle?: ViewProps['style'];
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
  containerStyle,
  ...props
}: CustomButtonProps) {
  const styles = StyleSheet.create({
    container: {
      borderRadius: 15,
      justifyContent: 'center',
      alignItems: 'stretch',
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
    <View style={[styles.container, containerStyle]}>
      <Touchable {...props}>
        <View style={[styles.button, style]}>{children}</View>
      </Touchable>
    </View>
  );
}
