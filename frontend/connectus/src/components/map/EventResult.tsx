import {Image, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainText from '../text/MainText';
import colors from '@/constants/colors';
import {fonts} from '@/constants';

type resultIcon = {
  isSuccess: boolean;
};

/**
 * 산책 결과 페이지에서 사용할 이벤트 참여결과 컴포넌트입니다.
 */
export default function EventResult() {
  const DUMMY_EVENT_STATE = true;

  function ResultIcon({isSuccess}: resultIcon) {
    return (
      <View style={[styles.outline, isSuccess ? styles.success : styles.fail]}>
        <Text style={styles.resultText}>{isSuccess ? '성공' : '실패'}</Text>
      </View>
    );
  }
  return (
    <View style={styles.mainContainer}>
      <Text style={styles.headerText}>이벤트 참여 결과</Text>
      <View style={styles.resultContainer}>
        <View style={styles.imageContainer}>
          <Image
            style={styles.image}
            source={require('@/assets/giftImage.png')}
          />
        </View>
        <View style={styles.textContainer}>
          <View style={styles.titleContainer}>
            <MainText style={styles.titleText}>별별 수집가</MainText>
            <ResultIcon isSuccess={DUMMY_EVENT_STATE} />
          </View>
          <Text style={styles.progressText}>스타벅스 매장을 방문하세요</Text>
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
  headerText: {
    fontFamily: fonts.medium,
    fontSize: 20,
    color: colors.white,
  },
  resultContainer: {
    backgroundColor: colors.buttonBackground,
    width: '100%',
    borderRadius: 15,
    flexDirection: 'row',
    padding: 15,
    gap: 15,
  },
  imageContainer: {
    width: 50,
    height: 50,
  },
  image: {
    width: '100%',
    height: '100%',
  },
  textContainer: {
    width: '80%',
    gap: 15,
  },
  titleContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 15,
  },
  titleText: {
    fontSize: 20,
  },
  progressText: {
    fontFamily: fonts.light,
    color: colors.white,
  },
  outline: {
    justifyContent: 'center',
    alignItems: 'center',
    width: 50,
    borderRadius: 15,
    padding: 5,
  },
  success: {
    backgroundColor: colors.primaryColorPink,
  },
  fail: {
    backgroundColor: colors.primaryColorBlue,
  },
  resultText: {
    fontSize: 14,
    fontFamily: fonts.medium,
    color: colors.white,
  },
});
