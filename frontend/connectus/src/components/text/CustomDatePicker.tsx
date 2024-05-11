import {Modal, Pressable, SafeAreaView, StyleSheet, View} from 'react-native';
import React from 'react';
import DatePicker from 'react-native-date-picker';
import colors from '@/constants/colors';
import MainText from './MainText';

interface DatePickerProps {
  isVisible: boolean;
  date: Date;
  onChangeDate: (date: Date) => void;
  onConfirmDate: () => void;
}

export default function CustomDatePicker({
  isVisible,
  date,
  onChangeDate,
  onConfirmDate,
}: DatePickerProps) {
  return (
    <Modal visible={isVisible} transparent animationType="slide">
      <SafeAreaView style={styles.background}>
        <View style={styles.optionContainer}>
          <DatePicker
            minimumDate={new Date()}
            mode="date"
            date={date}
            onDateChange={onChangeDate}
            locale="ko"
          />
        </View>
        <View style={styles.optionContainer}>
          <Pressable style={styles.button} onPress={onConfirmDate}>
            <MainText style={styles.confirmText}>확인</MainText>
          </Pressable>
        </View>
      </SafeAreaView>
    </Modal>
  );
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
    justifyContent: 'flex-end',
    backgroundColor: 'rgba(0 0 0 / 0.5)',
  },
  optionContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 15,
    marginBottom: 10,
    backgroundColor: colors.white,
  },
  button: {
    flexDirection: 'row',
    alignContent: 'center',
    justifyContent: 'center',
    padding: 15,
  },
  confirmText: {
    color: colors.background,
  },
});
