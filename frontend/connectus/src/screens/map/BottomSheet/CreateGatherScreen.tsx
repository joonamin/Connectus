import {
  Keyboard,
  Modal,
  Pressable,
  SafeAreaView,
  StyleSheet,
  TextInput,
  View,
} from 'react-native';
import React, {useEffect, useState} from 'react';
import MainContainer from '@/components/containers/MainContainer';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import {
  BottomSheetScrollView,
  TouchableWithoutFeedback,
} from '@gorhom/bottom-sheet';
import HeadCountInput from '@/components/map/HeadCountInput';
import {NativeViewGestureHandler} from 'react-native-gesture-handler';
import {getSubmitDate} from '@/utils/date';
import {gatherStart} from '@/api/gather';
import Geolocation from '@react-native-community/geolocation';
import {LatLng} from 'react-native-maps';
import useAuthStore from '@/store/useAuthStore';
import useModal from '@/hooks/useModal';
import {NavigationProp, useNavigation} from '@react-navigation/native';
import {BottomSheetStackParamList} from '@/navigations/stack/BottomSheetQuickStackNavigator';
import {StackNavigationProp} from '@react-navigation/stack';

// 유저가 설정 가능한 시간
const GATHERTIME = [5, 10, 15, 20, 30];

type Navigate = StackNavigationProp<BottomSheetStackParamList>;

/**
 * 모여라 모집 스크린입니다.
 */
export default function CreateGatherScreen() {
  // 모집 인원
  const [headCount, setHeadCount] = useState<number>(2);
  // 모집 시간
  const [gatherTime, setGatherTime] = useState<number | null>(null);
  // 모집 설명
  const [content, setContent] = useState<string>('');
  const [gatherPos, getGatherPos] = useState<LatLng>();
  const {user} = useAuthStore();
  // 모달 관련 state
  const {isVisible, show, hide} = useModal();
  // 모집 종료 후 메인으로 이동할때 쓸 네비게이터
  const navigation = useNavigation<Navigate>();

  // 모집 시, 전달해줄 LatLng 데이터를 가져오는 과정
  useEffect(() => {
    Geolocation.getCurrentPosition(
      info => {
        console.log(info);
        getGatherPos({
          latitude: info.coords.latitude,
          longitude: info.coords.longitude,
        });
      },
      () => {
        console.log('error');
      },
      {
        // 상세 좌표를 요청하는 코드
        enableHighAccuracy: true,
        distanceFilter: 0,
      },
    );
  }, []);

  const keyBoardDismiss = () => {
    Keyboard.dismiss();
  };

  /**
   * 모집 완료 후 메인으로 돌아갈 함수
   */
  const handlePressConfirm = () => {
    hide();
    navigation.navigate('Home');
  };

  /**
   * 멤버의 수를 입력받고 6을 초과할 시, 0으로 되돌리고 유저에게 알림을 내보냅니다
   * @param member 필요한 멤버의 수를 입력받습니다
   */
  const handleChangeCount = (member: number) => {
    setHeadCount(member);
  };

  const handleTimeButtonPress = (index: number) => {
    setGatherTime(GATHERTIME[index]);
  };

  /**
   * 모여라 모집을 눌렀을때 현재시간 + 모여라 시간을 적용한 endTime을 계산해 서버에 전송합니다
   * @todo hostId수정 필요
   */
  const handleGatherPress = async () => {
    if (gatherTime !== null && gatherPos && user) {
      const endTime = getSubmitDate(gatherTime);
      await gatherStart({
        hostId: user?.userId,
        content: content,
        maxJoiner: headCount,
        endTime: endTime,
        latitude: gatherPos?.latitude,
        longitude: gatherPos?.longitude,
      }).then(() => show());
    }
  };

  return (
    <TouchableWithoutFeedback onPress={keyBoardDismiss}>
      <MainContainer style={styles.mainContainer}>
        <NativeViewGestureHandler disallowInterruption={true}>
          <View style={styles.subContainer}>
            <HeadCountInput
              headCount={headCount}
              onChangeCount={handleChangeCount}
            />
          </View>
        </NativeViewGestureHandler>
        <View style={styles.subContainer}>
          <MainText style={styles.subLabel}>모집 시간</MainText>
          <BottomSheetScrollView
            horizontal={true}
            style={styles.timeButtonContainer}>
            {GATHERTIME.map((time, index) => {
              return (
                <Pressable
                  key={index}
                  style={[
                    styles.timeButton,
                    time === gatherTime ? styles.selected : undefined,
                  ]}
                  onPress={() => handleTimeButtonPress(index)}>
                  <MainText>{time}분</MainText>
                </Pressable>
              );
            })}
          </BottomSheetScrollView>
        </View>
        <View style={styles.subContainer}>
          <MainText style={styles.subLabel}>모집 설명</MainText>
          <TextInput
            value={content}
            autoCapitalize="none"
            spellCheck={false}
            autoCorrect={false}
            multiline
            onChangeText={text => setContent(text)}
            style={styles.memberInput}
          />
        </View>
        <Pressable style={styles.gatherButton} onPress={handleGatherPress}>
          <MainText>모여라 모집</MainText>
        </Pressable>
        <>
          <Modal visible={isVisible} transparent={true} animationType="slide">
            <SafeAreaView style={styles.modalBackground}>
              <View style={styles.confirmModal}>
                <>
                  <MainText>모여라 등록이 완료되었습니다.</MainText>
                  <Pressable
                    style={styles.confirimButton}
                    onPress={handlePressConfirm}>
                    <MainText>학인</MainText>
                  </Pressable>
                </>
              </View>
            </SafeAreaView>
          </Modal>
        </>
      </MainContainer>
    </TouchableWithoutFeedback>
  );
}
const styles = StyleSheet.create({
  mainContainer: {
    height: '100%',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 15,
  },
  gatherButton: {
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primaryColorBlue,
    padding: 15,
    borderRadius: 15,
  },
  subContainer: {
    width: '100%',
    height: 100,
  },
  subLabel: {
    color: colors.background,
  },
  memberInput: {
    width: '100%',
    height: 70,
    backgroundColor: colors.buttonBackground,
    padding: 15,
    borderRadius: 15,
    fontFamily: fonts.medium,
    color: colors.white,
    fontSize: 24,
  },
  timeButtonContainer: {
    rowGap: 20,
  },
  timeButton: {
    backgroundColor: colors.buttonBackground,
    paddingHorizontal: 10,
    paddingVertical: 1,
    borderRadius: 15,
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 5,
  },
  selected: {
    opacity: 0.7,
  },
  modalBackground: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  confirmModal: {
    width: '90%',
    height: 200,
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
    gap: 15,
    justifyContent: 'center',
    alignItems: 'center',
  },
  confirimButton: {
    borderRadius: 8,
    paddingHorizontal: 15,
    paddingVertical: 10,
    backgroundColor: colors.background,
  },
});
