import React from 'react';
import {TouchableHighlight} from 'react-native';
import {
  Platform,
  TouchableNativeFeedback,
  TouchableWithoutFeedbackProps,
} from 'react-native';

export type CustomTouchableProps = TouchableWithoutFeedbackProps;

/**
 * OS에 알맞는 Touchable 요소를 반환합니다
 *
 * @returns Touchable
 */
export default function CustomTouchable({...props}: CustomTouchableProps) {
  // Android일 시 feedback이 존재하는 Touchable 요소를 사용합니다
  if (Platform.OS === 'android') {
    return <TouchableNativeFeedback {...props} />;
  } else {
    return <TouchableHighlight {...props} />;
  }
}
