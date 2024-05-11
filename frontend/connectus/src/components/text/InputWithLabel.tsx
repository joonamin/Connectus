import {StyleSheet, TextInputProps, View, TextInput} from 'react-native';
import React from 'react';
import MainText from './MainText';
import colors from '@/constants/colors';
import {fonts} from '@/constants';

interface PropsType extends TextInputProps {
  label: String;
  handleChange: React.Dispatch<React.SetStateAction<any>>;
}

/**
 * 상단에 MainText형태의 Label을 표시하고 state와 setState를 받아와 state의 상태를 수정합니다.
 * @param label 상단에 표시할 label
 * @param handleChange 관리할 state의 setState
 * @param props TextInput에 들어갈 추가 옵션들
 */
export default function InputWithLabel({
  label,
  handleChange,
  ...props
}: PropsType) {
  return (
    <View style={styles.inputContainer}>
      <MainText>{label}</MainText>
      <TextInput
        onChangeText={text => handleChange(text)}
        style={styles.inputBox}
        autoCapitalize="none"
        spellCheck={false}
        autoCorrect={false}
        textAlign="center"
        {...props}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  inputContainer: {
    gap: 15,
  },
  inputBox: {
    backgroundColor: colors.buttonBackground,
    padding: 15,
    borderRadius: 15,
    color: colors.white,
    fontFamily: fonts.medium,
    fontSize: 24,
  },
});
