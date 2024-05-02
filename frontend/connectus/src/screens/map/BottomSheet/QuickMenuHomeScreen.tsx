import React from 'react';
import {Pressable, StyleSheet, View} from 'react-native';
import {useNavigation} from '@react-navigation/core';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomSheetStackParamList} from '@/navigations/stack/BottomSheetQuickStackNavigator';

type Navigation = StackNavigationProp<BottomSheetStackParamList>;

export default function QuickMenuHomeScreen() {
  const navigation = useNavigation<Navigation>();

  const handlePressCreateFeed = () => {
    navigation.navigate('FeedCreate');
  };

  return (
    <View style={styles.mainContainer}>
      <Pressable style={styles.moveButton} onPress={handlePressCreateFeed}>
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
