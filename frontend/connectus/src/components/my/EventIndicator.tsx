import {Image, Platform, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import colors from '@/constants/colors';
import MainText from '../text/MainText';
import {LatLng} from 'react-native-maps';

// 피그마에 있는 이벤트테이블을 참고해서 받아올 정보를 미리 적어놓은 타입입니다.
type Props = {
  title: string;
  description: string;
  spotList: LatLng[];
};

/**
 * 산책 화면에서 상단에 표시할 이벤트 인디케이터 입니다.
 * 자세한 텍스트는 이벤트페이지에서 props로 내려받을 예정이며, 상단에 간단한 타이틀만 보여주고
 * 이벤트의 정보가 어떻게 전달될 지 몰라 터치시 모달로 상세정보를 보여줘야하나 고민중입니다.
 */
export default function EventIndicator() {
  return (
    <View style={styles.mainContainer}>
      <View style={styles.imageContainer}>
        <Image
          source={require('@/assets/giftImage.png')}
          style={styles.image}
        />
      </View>
      <View style={styles.textContainer}>
        <Text>{'title'}도전중!</Text>
        <MainText style={styles.description}>{'description'}</MainText>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    width: '70%',
    height: 70,
    backgroundColor: colors.white,
    borderRadius: 30,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 15,
    flexDirection: 'row',
    shadowColor: colors.primaryColorBlue,
    shadowOffset: {
      width: 0,
      height: 6,
    },
    shadowOpacity: 0.39,
    shadowRadius: 8.3,
    elevation: 13,
  },
  imageContainer: {
    width: 44,
    height: 44,
  },
  image: {
    width: '100%',
    height: '100%',
  },
  textContainer: {
    justifyContent: 'center',
    alignItems: 'center',
  },
  description: {
    color: colors.background,
  },
});
