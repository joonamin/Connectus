import {Image, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import colors from '@/constants/colors';
import {fonts} from '@/constants';

// 피그마의 테이블을 참고해 타이틀과 해당 업적의 요약텍스트만 입력을 해두었습니다
type achievType = {
  title: string;
  description: string;
};

// props로 받아올 업적들을 배열의 형태로 받아옵니다
interface PropsType {
  achievs: achievType[];
}

/**
 * 산책 완료 시, 완료된 업적들의 배열을 받아오고, 그것을 통해 달성업적을 표시해 줄 컴포넌트입니다.
 * @param {achievs} 달성 업적들의 배열
 * @returns
 */
export default function Achievement({achievs}: PropsType) {
  return (
    <View style={styles.mainContainer}>
      <Text style={styles.header}>달성 업적</Text>
      {achievs.length === 0 && (
        <View style={styles.subContainer}>
          <Text numberOfLines={1} ellipsizeMode="tail" style={styles.subText}>
            완료된 업적이 존재하지 않습니다.
          </Text>
        </View>
      )}
      {achievs &&
        achievs.map((achieve, index) => {
          return (
            <View style={styles.subContainer} key={index}>
              <View style={styles.iconContainer}>
                <Image
                  style={styles.icon}
                  source={require('@/assets/giftImage.png')}
                />
              </View>
              <View style={styles.textContainer}>
                <Text style={styles.mainText}>{achieve.title}</Text>
                <Text
                  numberOfLines={1}
                  ellipsizeMode={'middle'}
                  style={styles.subText}>
                  {achieve.description}
                </Text>
              </View>
            </View>
          );
        })}
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
  mainText: {
    fontFamily: fonts.medium,
    fontSize: 20,
    color: colors.white,
    justifyContent: 'center',
    alignItems: 'center',
  },
  subText: {
    width: '100%',
    fontFamily: fonts.light,
    fontSize: 18,
    color: colors.white,
    justifyContent: 'center',
    alignItems: 'center',
  },
  iconContainer: {
    // 추후 window로 수정 필요
    width: 50,
    height: 50,
    borderRadius: 50,
  },
  icon: {width: '100%', height: '100%'},
  subContainer: {
    flexDirection: 'row',
    width: '100%',
    borderRadius: 15,
    padding: 15,
    gap: 15,
    backgroundColor: colors.buttonBackground,
  },
  textContainer: {
    width: '80%',
    justifyContent: 'space-between',
  },
});
