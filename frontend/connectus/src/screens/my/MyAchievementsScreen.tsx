import {getUserCompletedAchievement} from '@/api/user';
import CustomTextButton from '@/components/buttons/CustomTextButton';
import MainContainer from '@/components/containers/MainContainer';
import AchievementItem, {
  AchievementItemProps,
} from '@/components/my/AchievementItem';
import HeadingText from '@/components/text/HeadingText';
import LightText from '@/components/text/LightText';
import MainText from '@/components/text/MainText';
import {queryKeys} from '@/constants';
import colors from '@/constants/colors';
import useModal from '@/hooks/useModal';
import useAuthStore from '@/store/useAuthStore';
import {Achievement} from '@/types';
import {useQuery} from '@tanstack/react-query';
import React, {useState} from 'react';
import {
  Image,
  Modal,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  View,
} from 'react-native';

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
  const [selectedAchievement, setSelectedAchievement] = useState<
    Achievement | undefined
  >();

  const openAchievement = (achievement: AchievementItemProps) => {
    setSelectedAchievement(achievement);
    showDetails();
  };
  const closeAchievement = () => {
    setSelectedAchievement(undefined);
    hideDetails();
  };

  // 사용자 정보 불러오기
  const {user} = useAuthStore();

  // 업적 정보 불러오기
  const {isLoading, isError, data} = useQuery({
    queryKey: [queryKeys.GET_USER_COMPLETED_ACHIEVEMENT, user?.userId],
    queryFn: async ({queryKey}) => {
      const id = queryKey[1];

      if (typeof id !== 'number') {
        throw new Error('유효한 사용자 ID가 지정되어 있지 않습니다');
      }
      return (await getUserCompletedAchievement(id)).data;
    },
  });

  if (isLoading) {
    return (
      <MainContainer style={styles.container}>
        <LightText style={styles.statusText}>
          업적 정보를 불러오는 중입니다
        </LightText>
      </MainContainer>
    );
  } else if (isError || typeof data === 'undefined') {
    return (
      <MainContainer style={styles.container}>
        <LightText style={styles.statusText}>
          업적 정보를 불러오는 데 실패했습니다
        </LightText>
      </MainContainer>
    );
  }

  // 업적 데이터
  const achievements = data.completedAchievement;
  const incompleteAchievements = data.uncompletedAchievement;

  return (
    <ScrollView>
      <MainContainer style={styles.container}>
        {/* 달성 업적 */}
        <View style={styles.section}>
          <MainText>달성 업적</MainText>
          {achievements.map(item => (
            <AchievementItem
              key={item.achievementCode}
              {...item}
              onPress={() => {
                openAchievement(item);
              }}
            />
          ))}
        </View>
        {/* 미달성 업적 */}
        <View style={styles.section}>
          <MainText>미달성 업적</MainText>
          {incompleteAchievements.map(item => (
            <AchievementItem
              key={item.achievementCode}
              {...item}
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
                {typeof selectedAchievement !== 'undefined' ? (
                  <>
                    <HeadingText>{selectedAchievement.title}</HeadingText>
                    {selectedAchievement.imageUrl ? (
                      <Image
                        style={styles.achievementIcon}
                        src={selectedAchievement.imageUrl}
                      />
                    ) : undefined}
                    {'content' in selectedAchievement ? (
                      <MainText>{selectedAchievement.content}</MainText>
                    ) : (
                      <></>
                    )}
                  </>
                ) : undefined}
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
  statusText: {
    alignSelf: 'center',
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
