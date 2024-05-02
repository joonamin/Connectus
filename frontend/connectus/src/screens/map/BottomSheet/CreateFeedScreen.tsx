import {Keyboard, Pressable, StyleSheet, TextInput, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import {TouchableOpacity, TouchableWithoutFeedback} from '@gorhom/bottom-sheet';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/core';
import {MapBottomSheetTabParamList} from '@/navigations/Tabs/MapBottomSheetNavigator';
import {MaterialTopTabNavigationProp} from '@react-navigation/material-top-tabs';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomSheetStackParamList} from '@/navigations/stack/BottomSheetQuickStackNavigator';
import useImagePicker from '@/hooks/useImagePicker';
import usePermission from '@/hooks/usePermission';

type Navigation = CompositeNavigationProp<
  MaterialTopTabNavigationProp<MapBottomSheetTabParamList>,
  StackNavigationProp<BottomSheetStackParamList>
>;

/** 방명록 작성을 위한 스크린 페이지입니다
 */
export default function CreateFeedScreen() {
  const navigation = useNavigation<Navigation>();
  const imagePicker = useImagePicker();

  usePermission('PHOTO');

  const keyBoardDismiss = () => {
    Keyboard.dismiss();
  };

  const handlePressSubmit = () => {
    console.log('is work?');
    navigation.navigate('Home');
  };

  return (
    <TouchableWithoutFeedback onPress={() => Keyboard.dismiss()}>
      <MainContainer style={styles.mainContainer}>
        <View style={{width: '100%'}}>
          <TouchableOpacity
            style={styles.addImageButton}
            onPress={imagePicker.handleChange}>
            <MainText>사진 첨부하기</MainText>
          </TouchableOpacity>
        </View>
        <TextInput
          style={styles.contentInput}
          returnKeyType="done"
          multiline={true}
          numberOfLines={5}
        />
        <View style={styles.saveFeedButtonContainer}>
          <TouchableOpacity
            style={styles.addImageButton}
            onPress={handlePressSubmit}>
            <MainText>피드 저장</MainText>
          </TouchableOpacity>
        </View>
      </MainContainer>
    </TouchableWithoutFeedback>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    height: '100%',
    alignItems: 'center',
    gap: 15,
  },
  addImageButton: {
    width: '100%',
    backgroundColor: colors.buttonBackground,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 10,
    borderRadius: 15,
  },
  saveFeedButtonContainer: {
    width: 200,
  },
  contentInput: {
    width: '100%',
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
    color: colors.white,
    padding: 15,
    fontSize: 18,
    fontFamily: fonts.medium,
  },
});
