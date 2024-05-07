import {
  Pressable,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  View,
} from 'react-native';
import React, {useState} from 'react';
import MainText from '@/components/text/MainText';
import WalkResult from '@/components/map/WalkResult';
import Achievement from '@/components/map/Achievement';
import EventResult from '@/components/map/EventResult';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import RecordedPost from '@/components/map/RecordedPost';

const DUMMY_ACHIEVE = [
  {
    title: '우리팀 폭탄',
    description: '영욱님이 인정한 폭탄 돌리기의 폭탄 역할',
  },
];
/**
 *  산책 종료시 이동하게될 페이지입니다.
 */
export default function MapResultScreen() {
  const [walkTitle, setWalkTitle] = useState<string>('');
  return (
    <SafeAreaView style={{flex: 1}}>
      <ScrollView>
        <View style={styles.mainContainer}>
          <MainText>산책 종료</MainText>
          <WalkResult time="03:35:42" distance={13.42} />
          <Achievement achievs={DUMMY_ACHIEVE} />
          <EventResult />
          <RecordedPost />
          <View style={styles.titleInputContainer}>
            <MainText>이번 산책의 제목을 입력해주세요</MainText>
            <Text style={styles.subText}>
              산책의 이름은 피드 업로드시 활용됩니다
            </Text>
            <TextInput
              onChangeText={text => setWalkTitle(text)}
              style={styles.titleInput}
              textAlign="center"
            />
          </View>
          <Pressable style={styles.submitButton}>
            <MainText>산책 종료하기</MainText>
          </Pressable>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    width: '100%',
    padding: 15,
    alignItems: 'center',
    gap: 15,
  },
  titleInputContainer: {
    marginTop: 10,
    width: '100%',
    gap: 5,
    justifyContent: 'center',
    alignItems: 'center',
  },
  subText: {
    color: colors.white,
    fontFamily: fonts.light,
    marginBottom: 5,
  },
  titleInput: {
    color: colors.white,
    fontSize: 24,
    height: 60,
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 3,
    borderColor: colors.primaryColorBlue,
    borderRadius: 15,
    backgroundColor: colors.buttonBackground,
    padding: 15,
  },
  submitButton: {
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 15,
    borderRadius: 15,
    backgroundColor: colors.primaryColorBlue,
  },
});
