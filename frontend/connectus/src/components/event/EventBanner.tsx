import {Image, Pressable, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '../containers/MainContainer';
import colors from '@/constants/colors';
import MainText from '../text/MainText';
import {fonts} from '@/constants';
import {NavigationProp, useNavigation} from '@react-navigation/native';
import {EventStackParamList} from '@/navigations/stack/EventStackNavigator';

type Navigation = NavigationProp<EventStackParamList>;

/**
 * 이벤트에서 보일 이벤트 배너입니다. 해당 컴포넌트를 press해서 상세 페이지로 이동 가능합니다
 * @todo api가 어떻게 요청되는지에 따라서 props를 다르게 적용해야기때문에 레이아웃만 작성했습니다.
 * 추후 props또는 api수정 필요
 */
export default function EventBanner() {
  const navigation = useNavigation<Navigation>();

  /**
   * onPress 이벤트 발생 시, id를 props로
   */
  const handlePressBanner = () => {
    navigation.navigate('EventDetail');
  };

  return (
    <Pressable style={styles.bannerContainer} onPress={handlePressBanner}>
      <View style={styles.textContainer}>
        <MainText>{'이벤트 메인 타이틀'}</MainText>
        <Text style={styles.subText}>{'2024-05-10 ~ 2024-05-15'}</Text>
      </View>
      <View style={styles.imageContainer}>
        <Image
          style={styles.image}
          source={require('@/assets/giftImage.png')}
        />
      </View>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  bannerContainer: {
    justifyContent: 'space-between',
    backgroundColor: colors.buttonBackground,
    padding: 15,
    borderRadius: 15,
    flexDirection: 'row',
  },
  imageContainer: {
    width: 100,
    height: 100,
  },
  image: {
    width: '100%',
    height: '100%',
  },
  textContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    gap: 5,
  },
  subText: {
    fontFamily: fonts.light,
    color: colors.white,
  },
});
