import {ScrollView, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import CustomButton from '@/components/buttons/CustomButton';
import colors from '@/constants/colors';
import IconItemButton from '@/components/containers/IconItemButton';
import ProfileOverview from '@/components/my/ProfileOverview';
import LightText from '@/components/text/LightText';
import Ionicons from 'react-native-vector-icons/Ionicons';

export default function MyHomeScreen() {
  const styles = StyleSheet.create({
    maincontainer: {
      gap: 30,
    },
    detailButton: {
      padding: 10,
      alignSelf: 'baseline',
      flexDirection: 'row',
      alignItems: 'center',
      gap: 5,
    },
    listGroup: {
      padding: 0,
      gap: 5,
    },
  });

  return (
    <ScrollView>
      <MainContainer style={styles.maincontainer}>
        <ProfileOverview />
        <MainContainer style={styles.listGroup}>
          <IconItemButton
            iconType="MaterialCommunityIcons"
            iconName="table-key"
            text="이벤트 등록 권한 신청"
          />
          <CustomButton backgroundColor="transparent">
            <View style={styles.detailButton}>
              <Ionicons name="information-circle" size={24} color="white" />
              <LightText>이벤트 등록 권한 신청?</LightText>
            </View>
          </CustomButton>
        </MainContainer>
        <MainContainer style={styles.listGroup}>
          <IconItemButton
            backgroundColor={colors.buttonBackground}
            iconType="MaterialIcons"
            iconName="person"
            text="아바타 변경"
          />
          <IconItemButton
            backgroundColor={colors.buttonBackground}
            iconType="Ionicons"
            iconName="trophy"
            text="업적"
          />
          <IconItemButton
            backgroundColor={colors.buttonBackground}
            iconType="MaterialIcons"
            iconName="list"
            text="외출 기록"
          />
          <IconItemButton
            backgroundColor={colors.buttonBackground}
            iconType="MaterialIcons"
            iconName="chat"
            text="작성 댓글"
          />
          <IconItemButton
            backgroundColor={colors.buttonBackground}
            iconType="Ionicons"
            iconName="heart"
            text="좋아요 기록"
          />
        </MainContainer>
      </MainContainer>
    </ScrollView>
  );
}
