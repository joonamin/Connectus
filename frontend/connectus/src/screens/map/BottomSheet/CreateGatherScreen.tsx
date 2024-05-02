import {Pressable, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import CustomButton from '@/components/buttons/CustomButton';
import MainText from '@/components/text/MainText';
import CustomTextButton from '@/components/buttons/CustomTextButton';
import colors from '@/constants/colors';

export default function CreateGatherScreen() {
  return (
    <MainContainer style={styles.mainContainer}>
      <Pressable style={styles.gatherButton}>
        <MainText>모여라 모집</MainText>
      </Pressable>
    </MainContainer>
  );
}
const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  gatherButton: {
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primaryColorBlue,
    padding: 15,
    borderRadius: 15,
  },
});
