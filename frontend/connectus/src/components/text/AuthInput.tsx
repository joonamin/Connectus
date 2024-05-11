import {
  Pressable,
  StyleSheet,
  Text,
  TextInput,
  TextInputProps,
} from 'react-native';
import React, {useEffect, useRef, useState} from 'react';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import {validateInput} from '@/utils/validate';

export type authType = 'email' | 'password' | 'passwordConfirm';

interface AuthInputProps extends TextInputProps {
  value: string;
  checkValue?: string;
  inputType: authType;
  handleChange: React.Dispatch<React.SetStateAction<any>>;
}

export default function AuthInput({
  inputType,
  value,
  checkValue,
  handleChange,
  ...props
}: AuthInputProps) {
  const inputRef = useRef<TextInput | null>(null);
  const [error, setError] = useState<string>('');
  const handlePressInput = () => {
    inputRef.current?.focus();
  };

  useEffect(() => {
    if (!checkValue) {
      setError(validateInput(inputType, value));
    } else {
      setError(validateInput(inputType, {value, checkValue}));
    }
  }, [value]);

  return (
    <Pressable style={styles.inputContainer} onPress={handlePressInput}>
      <TextInput
        ref={inputRef}
        onChangeText={text => handleChange(text)}
        placeholderTextColor={colors.white}
        spellCheck={false}
        autoCorrect={false}
        autoCapitalize={'none'}
        style={styles.inputStyle}
        {...props}
      />
      {error && <Text style={styles.errorText}>{error}</Text>}
    </Pressable>
  );
}

const styles = StyleSheet.create({
  inputContainer: {
    width: '100%',
    borderWidth: 2,
    borderColor: colors.white,
    padding: 15,
  },
  inputStyle: {
    color: colors.white,
    fontFamily: fonts.medium,
    fontSize: 24,
    height: 40,
  },
  errorText: {
    paddingTop: 10,
    color: colors.white,
  },
});
