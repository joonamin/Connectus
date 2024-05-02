import {
  Image,
  Keyboard,
  Modal,
  Pressable,
  SafeAreaView,
  StyleSheet,
  Text,
  TextInput,
  View,
} from 'react-native';
import React, {useState} from 'react';
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
import useModal from '@/hooks/useModal';

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
  const [content, setContent] = useState<string>('');
  const {isVisible, show, hide} = useModal();
  usePermission('PHOTO');

  const keyBoardDismiss = () => {
    Keyboard.dismiss();
  };

  const handlePressSubmit = () => {
    console.log(
      `userLocation: ${userLocation.latitude}, ${userLocation.longitude} , content : ${content}`,
    );
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
          <TouchableOpacity style={styles.addImageButton} onPress={show}>
            <MainText>
              {imagePicker.imageData ? '사진 변경하기' : '사진 첨부하기'}
            </MainText>
          </TouchableOpacity>
        </View>
        <TextInput
          style={styles.contentInput}
          returnKeyType="done"
          placeholder="피드 내용을 입력해주세요"
          value={content}
          onChangeText={text => setContent(text)}
          placeholderTextColor={colors.white}
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
        <Modal visible={isVisible} transparent={true} animationType="slide">
          <SafeAreaView style={styles.centeredView} onTouchEnd={hide}>
            <View style={styles.modalView}>
              <Pressable
                style={styles.addImageButton}
                onPress={imagePicker.useCamera}>
                <MainText>카메라</MainText>
              </Pressable>
              <Pressable
                style={styles.addImageButton}
                onPress={() => {
                  hide();
                  imagePicker.useGallery();
                }}>
                <MainText>갤러리</MainText>
              </Pressable>
            </View>
          </SafeAreaView>
        </Modal>
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
  centeredView: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalView: {
    width: '70%',
    height: 200,
    margin: 20,
    backgroundColor: 'white',
    borderRadius: 20,
    padding: 35,
    alignItems: 'center',
    justifyContent: 'center',
    shadowColor: '#000',
    gap: 15,
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5,
  },
});
