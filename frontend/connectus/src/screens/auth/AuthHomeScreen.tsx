import {StyleSheet, View} from 'react-native';
import React from 'react';
import {defaultColors} from '@/constants/colors';
import MainContainer from '@/components/containers/MainContainer';
import TitleMediumText from '@/components/text/title/TitleMediumText';
import DisplayMediumText from '@/components/text/display/DisplayMediumText';
import PrimaryButton, { PrimaryButtonText } from '@/components/buttons/PrimaryButton';
export default function AuthHomeScreen() {
  return (
    <MainContainer style={styles.page}>
      <View style={styles.titleContainer}>
        <DisplayMediumText style={styles.title}>Connectus</DisplayMediumText>
        <TitleMediumText style={styles.subtitle}>
          우리들의 소통 앱
        </TitleMediumText>
      </View>
      <View style={styles.buttonContainer}>
        <PrimaryButton>
          <PrimaryButtonText>로그인</PrimaryButtonText>
        </PrimaryButton>
        <PrimaryButton>
          <PrimaryButtonText>회원가입</PrimaryButtonText>
        </PrimaryButton>
      </View>
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  page: {
    width: '100%',
    height: '100%',
    backgroundColor: defaultColors.surface,
    gap: 45,
    justifyContent: 'center',
    alignItems: 'center',
  },
  titleContainer: {
    gap: 5,
    alignItems: 'flex-start',
  },
  title: {
    color: defaultColors.primary,
  },
  subtitle: {
    color: defaultColors.secondary,
  },
  buttonContainer: {
    flexDirection: 'row',
    gap: 15,
  },
  button: {
    color: defaultColors.onPrimaryContainer,
  },
});
