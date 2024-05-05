import {StyleSheet, Text, View} from 'react-native';
import React from 'react';
import {fonts} from '@/constants';
import colors from '@/constants/colors';

type Props = {
  time: string;
  distance: number;
};

/**
 * 산책 종료 후, 산책 시간과 거리를 출력해줍니다
 * @param {time} 걸었던 시간을 string 형태로 받아옵니다.
 */
export default function WalkResult({time, distance}: Props) {
  return (
    <View style={styles.mainContainer}>
      <Text style={styles.header}>산책 결과</Text>
      <View style={styles.resultContainer}>
        <View style={styles.innerContainer}>
          <Text style={styles.innerText}>산책 거리</Text>
          <Text style={styles.innerText}>{distance}KM</Text>
        </View>
        <View style={styles.innerContainer}>
          <Text style={styles.innerText}>산책 시간</Text>
          <Text style={styles.innerText}>{time}</Text>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    width: '100%',
    gap: 15,
  },
  header: {
    fontFamily: fonts.medium,
    fontSize: 20,
    color: colors.white,
    justifyContent: 'center',
    alignItems: 'center',
  },
  innerText: {
    fontFamily: fonts.light,
    fontSize: 18,
    color: colors.white,
    justifyContent: 'center',
    alignItems: 'center',
  },
  resultContainer: {
    width: '100%',
    borderRadius: 15,
    padding: 15,
    gap: 15,
    backgroundColor: colors.buttonBackground,
  },
  innerContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
});
