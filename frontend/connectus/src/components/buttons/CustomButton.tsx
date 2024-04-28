import {Pressable, PressableProps, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import colors from '@/constants/colors';

interface CustomButtonProps extends PressableProps {
  label: string;
}

export default function CustomButton({label, ...props}: CustomButtonProps) {
  return (
    <Pressable style={styles.container} {...props}>
      <Text>{label}</Text>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  container: {
    width: '100%',
    borderRadius: 15,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primaryColorBlue
  },
});
