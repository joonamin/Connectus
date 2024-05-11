import React, {useState} from 'react';
import {Pressable, ScrollView, StyleSheet, View} from 'react-native';
import {NavigationProp, useNavigation} from '@react-navigation/native';
import {EventStackParamList} from '@/navigations/stack/EventStackNavigator';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';

import colors from '@/constants/colors';
import useModal from '@/hooks/useModal';
import MainText from '@/components/text/MainText';
import InputWithLabel from '@/components/text/InputWithLabel';
import MainContainer from '@/components/containers/MainContainer';
import CustomDatePicker from '@/components/text/CustomDatePicker';
import useEventPosStore from '@/store/useEventPosStore';
import {dateToString} from '@/utils';

type selectedDateType = 'start' | 'end';
type Navigation = NavigationProp<EventStackParamList>;

export default function EventCreateScreen() {
  const navigation = useNavigation<Navigation>();
  const [title, setTitle] = useState<string>('');
  const [reward, setReward] = useState<number>(0);
  const [description, setDescription] = useState<string>('');
  // 이벤트 기간을 설정할 두 state
  const [startDate, setStartDate] = useState<Date>(new Date());
  const [startTouched, setStartTouched] = useState<boolean>(false);
  const [endDate, setEndDate] = useState<Date>(new Date());
  const [endTouched, setEndtTouched] = useState<boolean>(false);

  // 선택한 이벤트 기간 타입을 지정합니다
  const [selectedDate, setSelectedDate] = useState<selectedDateType>('start');
  const {isVisible, show, hide} = useModal();
  // 유저가 선택한 이벤트 좌표
  const {position} = useEventPosStore();

  /**
   * 이벤트 개최 기간을 설정하기 위한 함수
   * 시작기간인지 종료기간인지 타입을 받고 난 후 모달을 엽니다
   */
  const handleOpen = (dateType: selectedDateType) => {
    dateType === 'start' ? setStartTouched(true) : setEndtTouched(true);
    setSelectedDate(dateType);
    show();
  };

  const handleSelectPos = () => {
    navigation.navigate('EventPosSelect');
  };

  /**
   * @todo api 개발 완료시 제출 코드 수정
   */
  const handleSubmit = () => {
    console.log(title, reward, description, startDate, endDate, position);
  };

  return (
    <ScrollView>
      <MainContainer style={styles.mainContainer}>
        <InputWithLabel label="이벤트 이름" handleChange={setTitle} />
        <View style={styles.dateContainer}>
          <MainText>이벤트 기간</MainText>
          <View style={styles.selectButtonContainer}>
            <Pressable
              style={styles.selectButton}
              onPress={() => handleOpen('start')}>
              <MainText>
                {startTouched ? dateToString(startDate) : '시작 날짜'}
              </MainText>
            </Pressable>
            <MainText>~</MainText>
            <Pressable
              style={styles.selectButton}
              onPress={() => handleOpen('end')}>
              <MainText>
                {endTouched ? dateToString(endDate) : '종료 날짜'}
              </MainText>
            </Pressable>
          </View>
        </View>
        <Pressable
          style={[styles.selectButton, styles.row, styles.gap15]}
          onPress={handleSelectPos}>
          <FontAwesome5 name="map-marker-alt" size={24} color={colors.white} />
          <MainText>이벤트 위치 설정하기</MainText>
        </Pressable>
        <InputWithLabel
          label="리워드 설정"
          handleChange={setReward}
          keyboardType="number-pad"
        />
        <InputWithLabel
          label="이벤트 설명"
          handleChange={setDescription}
          multiline
          numberOfLines={5}
        />
        <Pressable style={styles.submitButton} onPress={handleSubmit}>
          <MainText>이벤트 개최</MainText>
        </Pressable>
        <CustomDatePicker
          isVisible={isVisible}
          date={selectedDate === 'start' ? startDate : endDate}
          onChangeDate={selectedDate === 'start' ? setStartDate : setEndDate}
          onConfirmDate={hide}
        />
      </MainContainer>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  row: {flexDirection: 'row'},
  gap15: {gap: 15},
  mainContainer: {
    flex: 1,
    gap: 15,
  },
  submitButton: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primaryColorBlue,
    padding: 15,
    borderRadius: 15,
  },
  dateContainer: {
    gap: 15,
  },
  selectButtonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
    gap: 15,
  },
  selectButton: {
    borderRadius: 15,
    padding: 15,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: colors.buttonBackground,
  },
});
