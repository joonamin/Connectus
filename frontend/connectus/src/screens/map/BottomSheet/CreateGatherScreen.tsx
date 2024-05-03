import {
  Alert,
  Keyboard,
  Pressable,
  ScrollView,
  StyleSheet,
  TextInput,
  View,
} from 'react-native';
import React, {useState} from 'react';
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

// 유저가 설정 가능한 시간
const GATHERTIME = [5, 10, 15, 20, 30];

/**
 * 모여라 모집 스크린입니다.
 */
export default function CreateGatherScreen() {
  // 모집 인원
  const [headCount, setHeadCount] = useState<number>(2);
  // 모집 시간
  const [gatherTime, setGatherTime] = useState<number | null>(null);
  // 모집 설명
  const [content, setContent] = useState<string>();

  const keyBoardDismiss = () => {
    Keyboard.dismiss();
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

  const handleGatherPress = () => {
    console.log(`gather info : ${headCount}. ${gatherTime}, ${content}`);
    console.log(new Date());
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
});
