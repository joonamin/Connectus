import {
  Image,
  Keyboard,
  Pressable,
  StyleSheet,
  TextInput,
  View,
} from 'react-native';
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
import useUserLocation from '@/hooks/useUserLocation';

type Navigation = CompositeNavigationProp<
  MaterialTopTabNavigationProp<MapBottomSheetTabParamList>,
  StackNavigationProp<BottomSheetStackParamList>
>;

/** 방명록 작성을 위한 스크린 페이지입니다
 */
export default function CreateFeedScreen() {
  const navigation = useNavigation<Navigation>();
  const imagePicker = useImagePicker();
  // axios요청시 보낼 유저 위치
  const {userLocation} = useUserLocation();

  usePermission('PHOTO');

  const keyBoardDismiss = () => {
    Keyboard.dismiss();
  };

  const handlePressSubmit = () => {
    navigation.navigate('Home');
  };

  return (
    <TouchableWithoutFeedback onPress={keyBoardDismiss}>
      <MainContainer style={styles.mainContainer}>
        {imagePicker.imageData && (
          <View style={styles.imageContainer}>
            <Image
              style={styles.feedImage}
              source={{uri: imagePicker.imageData.path}}
            />
          </View>
        )}
        <View style={{width: '100%'}}>
          <TouchableOpacity
            style={styles.addImageButton}
            onPress={imagePicker.handleChange}>
            <MainText>
              {imagePicker.imageData ? '사진 변경하기' : '사진 첨부하기'}
            </MainText>
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
  imageContainer: {
    width: 150,
    height: 150,
  },
  feedImage: {
    width: '100%',
    height: '100%',
  },
});
