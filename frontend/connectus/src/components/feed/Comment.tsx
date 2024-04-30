import {Image, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import colors from '@/constants/colors';

type commentProps = {
  userName: string;
  comment: string;
};

/**
 * @param {string} userName
 * 댓글에 표시할 유저의 닉네임 입니다
 * @param {string} comment
 * 스크린에 출력할 댓글 내용입니다
 * @returns
 */
export default function Comment() {
  return (
    <View style={styles.commentContainer}>
      <View style={styles.profileImageContainer}>
        <Image
          source={require('@/assets/default-profile.png')}
          style={styles.profileImage}
        />
      </View>
      <View style={styles.commentTextContaier}>
        <Text style={styles.userText}>{'userName'}</Text>
        <Text style={styles.commentText}>{'comment'}</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  commentContainer: {
    flexDirection: 'row',
    gap: 15,
  },
  profileImageContainer: {
    width: 44,
    height: 44,
    borderRadius: 50,
  },
  profileImage: {
    width: '100%',
    height: '100%',
  },
  userText: {
    color: colors.white,
    fontFamily: 'GmarketSansTTFLight',
  },
  commentText: {
    color: colors.white,
    fontFamily: 'GmarketSansTTFMedium',
  },
  commentTextContaier: {
    justifyContent: 'center',
  },
});
