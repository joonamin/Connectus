import {Pressable, StyleSheet, Text, View} from 'react-native';
import React from 'react';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';
import MainContainer from '@/components/containers/MainContainer';
import EventBanner from '@/components/event/EventBanner';
import colors from '@/constants/colors';
import {NavigationProp, useNavigation} from '@react-navigation/native';
import {EventStackParamList} from '@/navigations/stack/EventStackNavigator';

type Navigation = NavigationProp<EventStackParamList>;

/**
 * 현재 진행중인 이벤트 들을 표시하고 우측하단 버튼으로 허가받은 유저에는 이벤트를 생성할 수 있습니다.
 */
export default function EventHomeScreen() {
  const navigation = useNavigation<Navigation>();
  const handlePressCreate = () => {
    navigation.navigate('EventCreate');
  };

  return (
    <MainContainer style={styles.mainContainer}>
      <EventBanner />
      <Pressable style={styles.rbButton} onPress={handlePressCreate}>
        <FontAwesome5 name="calendar-check" size={44} color={colors.white} />
      </Pressable>
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    alignContent: 'center',
  },
  rbButton: {
    position: 'absolute',
    justifyContent: 'center',
    alignItems: 'center',
    bottom: 15,
    right: 15,
    width: 77,
    height: 77,
    borderRadius: 50,
    backgroundColor: colors.primaryColorBlue,
  },
});
