import {Image, Pressable, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import colors from '@/constants/colors';
import MainText from '@/components/text/MainText';

export default function FeedHomeScreen() {
  return (
    <MainContainer>
      <View style={styles.feedContainer}>
        <View style={styles.profileContainer}>
          <View style={styles.imageContainer}>
            <Image
              source={require('@/assets/default-profile.png')}
              style={styles.profileImage}
            />
          </View>
          <View style={styles.feedInfoContainer}>
            <MainText>{'userName'}</MainText>
            <Text style={styles.postDate}>{'2024-04-29'}</Text>
          </View>
          <Pressable style={styles.moveButton}>
            <Text>보러가기</Text>
          </Pressable>
        </View>
        <View style={styles.postImageContainer}></View>
      </View>
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  feedContainer: {borderColor: 'white', borderWidth: 1},
  profileContainer: {
    flexDirection: 'row',
    height: 44,
  },
  imageContainer: {
    width: 44,
    height: 44,
    borderColor: colors.white,
    borderWidth: 1,
    borderRadius: 50,
  },
  profileImage: {
    width: '100%',
    height: '100%',
  },
  feedInfoContainer: {justifyContent: 'center', paddingLeft: 10},
  userName: {fontSize: 24, color: colors.white},
  postDate: {
    fontFamily: 'GmarketSansTTFMedium',
    fontSize: 12,
    color: colors.white,
  },
  moveButton: {
    position: 'absolute',
    right: 0,
    height: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
    paddingHorizontal: 5,
  },
  postImageContainer: {},
});
