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
import useImagePicker from '@/hooks/useImagePicker';
import CustomButton from '@/components/buttons/CustomButton';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import {Image} from 'react-native';
import {useMutation} from '@tanstack/react-query';
import {MakeEventRequest, makeEvent} from '@/api/event';
import useAuthStore from '@/store/useAuthStore';

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
  // 이벤트 이미지
  const imagePicker = useImagePicker();

  // 이벤트 생성 진행 중
  const [progress, setProgress] = useState<boolean>(false);

  // 선택한 이벤트 기간 타입을 지정합니다
  const [selectedDate, setSelectedDate] = useState<selectedDateType>('start');
  const {isVisible, show, hide} = useModal();
  // 유저가 선택한 이벤트 좌표
  const {position} = useEventPosStore();

  // 사용자 정보 가져오기
  const {user} = useAuthStore();

  // Mutation 객체
  const mutation = useMutation({
    mutationFn: (request: MakeEventRequest) => makeEvent(request),
    onMutate: () => {
      setProgress(true);
    },
    onSuccess: () => {
      navigation.goBack();
    },
    onError: (error) => {
      console.log(error);
    },
    onSettled: () => {
      setProgress(false);
    },
  });

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
    console.log(
      title,
      reward,
      description,
      startDate,
      endDate,
      position,
      imagePicker.imageData,
    );

    if (typeof user?.userId === 'undefined') {
      return;
    } else if (!imagePicker.imageData) {
      return;
    }

    mutation.mutate({
      userId: user.userId,
      title: title,
      reward: reward,
      description: description,
      startDatetime: startDate,
      endDatetime: endDate,
      positions: position,
      image: imagePicker.imageData.path,
    });
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
        {imagePicker.imageData && (
          <View style={styles.imageContainer}>
            <Image
              style={styles.image}
              source={{uri: imagePicker.imageData.path}}
            />
          </View>
        )}
        <CustomButton
          style={[styles.selectButton, styles.row, styles.gap15]}
          backgroundColor={colors.buttonBackground}
          onPress={() => {
            imagePicker.useGallery();
          }}>
          <MaterialIcons name="image" size={24} color={colors.white} />
          <MainText>
            {imagePicker.imageData ? '이벤트 사진 변경' : '이벤트 사진 첨부'}
          </MainText>
        </CustomButton>
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
  imageContainer: {
    aspectRatio: '1 / 1',
    width: '50%',
    alignSelf: 'center',
  },
  image: {
    width: '100%',
    height: '100%',
  },
});
