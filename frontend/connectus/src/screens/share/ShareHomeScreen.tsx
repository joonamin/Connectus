import {
  FlatList,
  Modal,
  Pressable,
  SafeAreaView,
  StyleSheet,
  View,
} from 'react-native';
import React, {useState} from 'react';
import {
  CompositeNavigationProp,
  NavigationProp,
  useNavigation,
} from '@react-navigation/native';
import {LatLng} from 'react-native-maps';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import SharePost from '@/components/share/SharePost';
import colors from '@/constants/colors';
import {ShareStackParamList} from '@/navigations/stack/ShareStackNavigator';
import useRouteStore from '@/store/useRouteStore';
import MainText from '@/components/text/MainText';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';

type Navigation = CompositeNavigationProp<
  StackNavigationProp<ShareStackParamList>,
  StackNavigationProp<MapStackParamList>
>;

export default function ShareHomeScreen() {
  const navigation = useNavigation<Navigation>();
  const [isVisible, setIsVisible] = useState<boolean>(false);
  const {setRoute, setDeleteRoute} = useRouteStore();

  // 모달을 열고 기록에 있는 루트정보를 전역으로 설정
  // const handleModalOpen = (route: LatLng[]) => {
  //   setIsVisible(true);
  //   setRoute(route);
  // };

  const handleModalOpen = () => {
    setIsVisible(true);
  };

  // 모달을 닫기위한 코드
  const handleModalClose = () => {
    setIsVisible(false);
  };

  // 화면을 이동시키고 map에 poly라인 설정
  const handleFollowApprove = () => {
    setIsVisible(false);
    navigation.navigate('MapWalk');
  };

  // store에 저장된 루트를 삭제하고 modal창을 close
  const handleFollowDeny = () => {
    setIsVisible(false);
    setDeleteRoute();
  };

  const handlePressShare = () => {
    navigation.navigate('ShareRecord');
  };

  return (
    <>
      <SafeAreaView style={styles.mainContainer}>
        <FlatList
          data={[1, 5, 2, 1, 1]}
          renderItem={({item}) => {
            return <SharePost modalOpen={handleModalOpen} />;
          }}
        />
        <Pressable
          style={styles.shareButtonContainer}
          onPress={handlePressShare}>
          <MaterialCommunityIcons
            name="book-plus"
            color={colors.white}
            size={42}
          />
        </Pressable>
      </SafeAreaView>
      <Modal visible={isVisible} transparent={true} animationType="slide">
        <SafeAreaView
          style={styles.modalContainer}
          onTouchEnd={handleModalClose}>
          <View style={styles.modalView} onTouchEnd={e => e.stopPropagation()}>
            <MainText>해당루트를 걸어보시겠어요?</MainText>
            <View style={styles.modalButtonContainer}>
              <Pressable
                style={styles.modalButton}
                onPress={handleFollowApprove}>
                <MainText>지도로 이동</MainText>
              </Pressable>
              <Pressable style={styles.modalButton} onPress={handleFollowDeny}>
                <MainText>취소</MainText>
              </Pressable>
            </View>
          </View>
        </SafeAreaView>
      </Modal>
    </>
  );
}

const styles = StyleSheet.create({
  mainContainer: {flex: 1, gap: 15},
  shareButtonContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    position: 'absolute',
    width: 72,
    height: 72,
    right: 15,
    bottom: 15,
    backgroundColor: colors.primaryColorBlue,
    borderRadius: 50,
  },
  modalContainer: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalView: {
    width: '90%',
    height: 200,
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
    gap: 15,
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalButtonContainer: {
    flexDirection: 'row',
    gap: 15,
  },
  modalButton: {
    padding: 15,
    borderRadius: 8,
    backgroundColor: colors.background,
    justifyContent: 'center',
    alignItems: 'center',
  },
});
