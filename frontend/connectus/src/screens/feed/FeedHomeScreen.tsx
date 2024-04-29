import {
  Button,
  Dimensions,
  FlatList,
  Modal,
  Pressable,
  SafeAreaView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import React, {useState} from 'react';
import Carousel from 'react-native-reanimated-carousel';
import FeedPreview from '@/components/feed/FeedPreview';
import colors from '@/constants/colors';
import useModal from '@/hooks/useModal';

export default function FeedHomeScreen() {
  const carouselWidth = Dimensions.get('window').width;
  const carouselHeight = Dimensions.get('window').height;
  // 보러가기 버튼을 눌럿을 때, update되어 이동 id를 modal에 전달해줄 state
  const [selectFeed, setSelectFeed] = useState<number | undefined>(undefined);
  // 보러가기 버튼 클릭시 실행할 update 함수
  const handlePressViewButton = (id: number) => {
    setSelectFeed(id);
  };

  const {
    isVisible: isMoveModalVisible,
    show: moveModalShow,
    hide: moveModalHide,
  } = useModal();

  const dummyData = [
    {isLiked: 42, likeNumber: 12, commentNumber: 22},
    {isLiked: 12, likeNumber: 82, commentNumber: 12},
    {isLiked: 22, likeNumber: 42, commentNumber: 12},
  ];
  return (
    <SafeAreaView>
      <FlatList
        data={dummyData}
        renderItem={({item}) => (
          <View style={{marginBottom: 30}}>
            <Carousel
              width={carouselWidth}
              height={carouselWidth + 80}
              mode="parallax"
              panGestureHandlerProps={{
                activeOffsetX: [-10, 10],
              }}
              data={[1, 2, 3]}
              renderItem={({index}) => (
                <FeedPreview
                  isLiked={false}
                  likeNumber={12}
                  commentNumber={53}
                  show={moveModalShow}
                />
              )}
            />
          </View>
        )}>
        {/* <Carousel
          width={carouselWidth}
          mode="parallax"
          data={test}
          renderItem={({index}) => (
            <FeedPreview
              isLiked={false}
              likeNumber={12}
              commentNumber={53}
              show={moveModalShow}
            />
          )}
        /> */}
      </FlatList>
      <Modal
        visible={isMoveModalVisible}
        transparent={true}
        animationType="slide">
        <SafeAreaView style={styles.modalBackground} onTouchEnd={moveModalHide}>
          <View style={styles.confirmModal}>
            <Text style={styles.confirmText}>
              해당 방명록의 위치를 확인하시겠습니까?
            </Text>
            <View style={styles.confirmButtonContainer}>
              <Pressable
                style={styles.confirimButton}
                onPress={() => console.log('helo')}>
                <Text style={styles.buttonText}>네</Text>
              </Pressable>
              <Pressable style={styles.confirimButton} onPress={moveModalHide}>
                <Text style={styles.buttonText}>아니오</Text>
              </Pressable>
            </View>
          </View>
        </SafeAreaView>
      </Modal>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  modalBackground: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  confirmModal: {
    borderRadius: 20,
    padding: 35,
    gap: 10,
    backgroundColor: colors.buttonBackground,
  },
  confirmText: {
    fontFamily: 'GmarketSansTTFLight',
    fontSize: 16,
    color: colors.white,
  },
  confirmButtonContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    gap: 30,
  },
  confirimButton: {
    borderRadius: 8,
    paddingHorizontal: 15,
    paddingVertical: 10,
    backgroundColor: colors.background,
  },
  buttonText: {
    fontFamily: 'GmarketSansTTFMedium',
    color: colors.white,
    fontSize: 16,
  },
});
