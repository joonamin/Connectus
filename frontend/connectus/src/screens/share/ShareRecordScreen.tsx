import {Modal, SafeAreaView, ScrollView, StyleSheet, View} from 'react-native';
import React, {useState} from 'react';
import WalkHistory, {WalkHistoryProps} from '@/components/my/WalkHistory';
import {StackScreenProps} from '@react-navigation/stack';
import {ShareStackParamList} from '@/navigations/stack/ShareStackNavigator';
import MainContainer from '@/components/containers/MainContainer';
import {defaultColors} from '@/constants/colors';
import PrimaryButton, {
  PrimaryButtonText,
} from '@/components/buttons/PrimaryButton';
import SecondaryButton, {
  SecondaryButtonText,
} from '@/components/buttons/SecondaryButton';
import LightText from '@/components/text/LightText';
import Dialog from '@/components/containers/Dialog';

/**
 * 유저의 지난 산책 기록들을 선택해서 공유할 수 있는 스크린입니다.
 */
export default function ShareRecordScreen() {
  // 선택 시 설정 될 산책 ID
  const [walkId, setWalkId] = useState<number | undefined>();

  // modal 관련 설정
  const [shareDialogVisibility, setShareDialogVisibility] =
    useState<boolean>(false);
  const [dialogMessage, setDialogMessage] = useState<string | undefined>();
  const [closeTimout, setCloseTimeout] = useState<
    ReturnType<typeof setTimeout> | undefined
  >();
  const closeShareDialog = () => {
    setShareDialogVisibility(false);
    clearTimeout(closeTimout);
    setDialogMessage(undefined);
  };

  // Axios 요청을 흉내내기 위한 시험 코드
  const testShare = () => {
    return new Promise<undefined>((resolve, reject) => {
      setTimeout(() => {
        if (walkId === 6) {
          reject('I do not like number six');
        } else {
          resolve(undefined);
        }
      }, 1500);
    });
  };

  const onShareItemSelected: WalkHistoryProps['onWalkSelected'] = (
    selectedWalkId: number,
  ) => {
    // 공유할 기록 선택 시 관련 작업 수행
    setWalkId(selectedWalkId);
    setShareDialogVisibility(true);
  };

  // 경로 공유 시 실행
  const onShare = () => {
    console.debug(walkId);

    setDialogMessage('경로 공유 중');

    // 경로 공유 작업 진행
    testShare()
      .then(() => {
        setDialogMessage('공유가 완료되었습니다');
      })
      .catch(() => {
        setDialogMessage('오류가 발생했습니다');
      })
      .finally(() => {
        // 메세지 표시 3초 후 modal 닫기
        setCloseTimeout(
          setTimeout(() => {
            closeShareDialog();
          }, 3000),
        );
      });
  };

  return (
    <ScrollView>
      <WalkHistory onWalkSelected={onShareItemSelected} showSummary={false} />
      {/* 업적 상세 정보 modal */}
      <Dialog
        style={styles.modal}
        visible={shareDialogVisibility}
        transparent={true}
        animationType="fade"
        onRequestClose={closeShareDialog}>
        {typeof dialogMessage === 'undefined' ? (
          <>
            <LightText>해당 경로를 공유하시겠어요?</LightText>
            <View style={styles.buttonRow}>
              <PrimaryButton onPress={onShare}>
                <PrimaryButtonText>예</PrimaryButtonText>
              </PrimaryButton>
              <SecondaryButton onPress={closeShareDialog}>
                <SecondaryButtonText>아니오</SecondaryButtonText>
              </SecondaryButton>
            </View>
          </>
        ) : (
          <LightText>{dialogMessage}</LightText>
        )}
      </Dialog>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  modal: {
    gap: 30,
    alignItems: 'center',
  },
  buttonRow: {
    gap: 15,
    flexDirection: 'row',
  },
});
