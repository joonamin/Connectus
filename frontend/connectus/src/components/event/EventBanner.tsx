import {Image, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '../containers/MainContainer';
import colors from '@/constants/colors';

export default function EventBanner() {
  return (
    <View style={styles.bannerContainer}>
      <View style={styles.imageContainer}>
        <Image
          style={styles.image}
          source={require('@/assets/giftImage.png')}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  bannerContainer: {
    backgroundColor: colors.buttonBackground,
    padding: 15,
    borderRadius: 15,
  },
  imageContainer: {
    width: 55,
    height: 55,
  },
  image: {
    width: '100%',
    height: '100%',
  },
});
