import {Pressable, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';

export default function QuickMenuHomeScreen() {
  return (
    <View style={styles.mainContainer}>
      <Pressable style={styles.moveButton}>
        <MainText>방명록 작성</MainText>
      </Pressable>
      <Pressable style={styles.moveButton}>
        <MainText>채팅</MainText>
      </Pressable>
      <Pressable style={styles.moveButton}>
        <MainText>모여라 작성</MainText>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    alignItems: 'center',
    padding: 15,
    gap: 15,
  },
  moveButton: {
    padding: 15,
    backgroundColor: colors.buttonBackground,
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 15,
  },
});
