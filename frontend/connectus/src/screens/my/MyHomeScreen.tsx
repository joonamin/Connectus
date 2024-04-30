import {StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import MainContainer from '@/components/containers/MainContainer';
import CustomButton from '@/components/buttons/CustomButton';
import ListItem from '@/components/containers/ListItem';
import colors from '@/constants/colors';
import MainText from '@/components/text/MainText';
import IconListItem from '@/components/containers/IconListItem';
import IconItemButton from '@/components/containers/IconItemButton';

export default function MyHomeScreen() {
  const styles = StyleSheet.create({
    maincontainer: {
      gap: 30,
    },
    listGroup: {
      gap: 5,
    },
  });

  return (
    <MainContainer style={styles.maincontainer}>
      <MainContainer style={styles.listGroup}>
        <IconItemButton
          backgroundColor={colors.buttonBackground}
          iconType="MaterialCommunityIcons"
          iconName="table-key"
          text="이벤트 등록 권한 신청"
        />
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
  );
}
