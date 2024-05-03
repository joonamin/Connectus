import React from 'react';
import {StyleSheet, View, Text} from 'react-native';
import Slider from '@react-native-community/slider';
import {NativeViewGestureHandler} from 'react-native-gesture-handler';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import MainText from '../text/MainText';

interface HeadCountInputProps {
  headCount: number;
  onChangeCount: (value: number) => void;
}

export default function HeadCountInput({
  headCount,
  onChangeCount,
}: HeadCountInputProps) {
  return (
    <View style={styles.container}>
      <View style={styles.labelContainer}>
        <MainText style={styles.labelText}>모집인원</MainText>
        <MainText style={styles.labelText}>{headCount}명</MainText>
      </View>
      <Slider
        value={headCount}
        onValueChange={onChangeCount}
        step={1}
        minimumValue={2}
        maximumValue={6}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  labelContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 15,
  },
  labelText: {
    fontFamily: fonts.medium,
    color: colors.buttonBackground,
  },
});
