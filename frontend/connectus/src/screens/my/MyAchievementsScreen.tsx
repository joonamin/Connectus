import CustomTextButton from '@/components/buttons/CustomTextButton';
import MainContainer from '@/components/containers/MainContainer';
import AchievementIcon from '@/components/my/AchievementIcon';
import AchievementItem, {
  AchievementItemProps,
} from '@/components/my/AchievementItem';
import HeadingText from '@/components/text/HeadingText';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';
import useModal from '@/hooks/useModal';
import React, {useState} from 'react';
import {Modal, SafeAreaView, ScrollView, StyleSheet, View} from 'react-native';

/**
 * 업적 페이지입니다
 *
 * @returns MyAchievementsScreen
 */
export default function MyAchievementsScreen() {
  // 업적 상세 정보 표시를 위한 modal 설정
  const {
    isVisible: detailVisibility,
    show: showDetails,
    hide: hideDetails,
  } = useModal();
  const [selectedAchievement, setSelectedAchievement] =
    useState<AchievementItemProps>({title: ''});

  const openAchievement = (achievement: AchievementItemProps) => {
    setSelectedAchievement(achievement);
    showDetails();
  };
  const closeAchievement = () => {
    setSelectedAchievement({title: ''});
    hideDetails();
  };

  // 업적 시험 데이터
  const achievements: AchievementItemProps[] = [
    {title: '꾸준한 운동가'},
    {title: '위대한 첫걸음'},
  ];
  const incompleteAchievements: AchievementItemProps[] = [
    {title: '그를 찬양해야해'},
    {title: '위대한 갓진주', description: 'CI/CD 이틀컷'},
    {title: 'CI/CD의 신'},
  ];

  return (
    <ScrollView>
      <MainContainer style={styles.container}>
        {/* 달성 업적 */}
        <View style={styles.section}>
          <MainText>
            {'한방 옻닭'}님의 업적 달성 갯수는 {2}개
          </MainText>
          {achievements.map((item: AchievementItemProps) => (
            <AchievementItem
              key={item.title}
              title={item.title}
              onPress={() => {
                openAchievement(item);
              }}
            />
          ))}
        </View>
        {/* 미달성 업적 */}
        <View style={styles.section}>
          <MainText>{'한방 옻닭'}님의 미달성 업적</MainText>
          {incompleteAchievements.map((item: AchievementItemProps) => (
            <AchievementItem
              key={item.title}
              title={item.title}
              onPress={() => {
                openAchievement(item);
              }}
            />
          ))}
        </View>
        {/* 업적 상세 정보 modal */}
        <Modal
          visible={detailVisibility}
          transparent={true}
          animationType="fade"
          onRequestClose={closeAchievement}>
          <SafeAreaView
            style={styles.modalContainer}
            onTouchEnd={closeAchievement}>
            <MainContainer
              style={styles.modal}
              onTouchEnd={e => {
                // stopPropagation으로 modal 내 touch 시
                // modal이 닫히는 현상 방지
                e.stopPropagation();
              }}>
              <View style={styles.modalContents}>
                <HeadingText>{selectedAchievement.title}</HeadingText>
                <AchievementIcon
                  icon={selectedAchievement?.icon}
                  style={styles.achievementIcon}
                />
                {'description' in selectedAchievement ? (
                  <MainText>{selectedAchievement.description}</MainText>
                ) : (
                  <></>
                )}
              </View>
              <CustomTextButton
                backgroundColor={colors.background}
                label="확인"
                onPress={closeAchievement}
              />
            </MainContainer>
          </SafeAreaView>
        </Modal>
      </MainContainer>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    gap: 30,
  },
  section: {
    gap: 15,
  },
  modalContainer: {
    flex: 1,
    flexDirection: 'column',
    width: '100%',
    justifyContent: 'center',
    alignItems: 'stretch',
    height: '100%',
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
  },
  modal: {
    borderRadius: 15,
    margin: 15,
    gap: 30,
    alignItems: 'stretch',
    backgroundColor: colors.buttonBackground,
  },
  modalContents: {
    gap: 30,
    alignItems: 'center',
  },
  achievementIcon: {
    flexBasis: 128,
  },
});
