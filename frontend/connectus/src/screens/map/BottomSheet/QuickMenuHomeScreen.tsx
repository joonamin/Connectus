import React from 'react';
import {StackNavigationProp} from '@react-navigation/stack';
import {
  DeviceEventEmitter,
  Pressable,
  StyleSheet,
  Switch,
  View,
} from 'react-native';
import {useNavigation, CompositeNavigationProp} from '@react-navigation/core';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5';
import Ionicons from 'react-native-vector-icons/Ionicons';

import colors from '@/constants/colors';
import useRouteStore from '@/store/useRouteStore';
import MainText from '@/components/text/MainText';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import {BottomSheetStackParamList} from '@/navigations/stack/BottomSheetQuickStackNavigator';

type Navigation = CompositeNavigationProp<
  StackNavigationProp<BottomSheetStackParamList>,
  StackNavigationProp<MapStackParamList>
>;

export default function QuickMenuHomeScreen() {
  const navigation = useNavigation<Navigation>();
  const {setDeleteRoute} = useRouteStore();

  const handlePressCreateFeed = () => {
    navigation.navigate('FeedCreate');
  };

  const handlePressCreateGather = () => {
    navigation.navigate('GatherCreate');
  };

  // 아아 그는 좋은 채팅 기능이었습니다.
  // const handlePressChatList = () => {
  //   navigation.navigate('ChatList');
  // };

  /**
   * 산책 종료시 저장되어 있던, 따라걷기 정보를 지웁니다.
   * 그 후, 산책 종료를 위한 Modal compoenent의 isVisible 속성을 true로 전환합니다.
   */
  const handlePressQuit = async () => {
    DeviceEventEmitter.emit('openBottomSheetModal');
    setDeleteRoute();
  };

  return (
    <View style={styles.mainContainer}>
      <MainText style={styles.header}>빠른 메뉴</MainText>
      <View style={styles.moveButtonContainer}>
        <Pressable style={styles.moveButton} onPress={handlePressCreateFeed}>
          <Ionicons name="book-outline" color={colors.background} size={32} />
          <MainText style={styles.bottomSheetOptionText}>방명록 작성</MainText>
        </Pressable>
        <Pressable style={styles.moveButton} onPress={handlePressCreateGather}>
          <Ionicons name="radio" color={colors.background} size={32} />
          <MainText style={styles.bottomSheetOptionText}>모여라 작성</MainText>
        </Pressable>
      </View>
      <View style={styles.toogleButtonContainer}>
        <FontAwesome5
          name="map-marked-alt"
          color={colors.background}
          size={32}
        />
        <MainText style={styles.bottomSheetOptionText}>
          사용자 위치 공유
        </MainText>
        <Switch style={{transform: [{scaleX: 1.5}, {scaleY: 1.5}]}} />
      </View>
      <Pressable
        style={[styles.endWalkButton, styles.quitButton]}
        onPress={handlePressQuit}>
        <MainText>산책 종료</MainText>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    alignItems: 'center',
    padding: 15,
    gap: 15,
  },
  header: {color: colors.background, marginBottom: 20},
  bottomSheetOptionText: {
    color: colors.background,
    fontSize: 18,
  },
  moveButtonContainer: {
    flexDirection: 'row',
    gap: 15,
    alignItems: 'flex-start',
  },
  moveButton: {
    backgroundColor: '#f4f9fd',
    padding: 15,
    width: '45%',
    justifyContent: 'center',
    alignItems: 'center',
    gap: 15,
    borderRadius: 15,
    borderWidth: 1,
  },
  toogleButtonContainer: {
    width: '100%',
    marginTop: 30,
    padding: 15,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  endWalkButton: {
    padding: 15,
    backgroundColor: colors.buttonBackground,
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 15,
  },
  quitButton: {
    position: 'absolute',
    bottom: 20,
    backgroundColor: colors.primaryColorBlue,
  },
});
