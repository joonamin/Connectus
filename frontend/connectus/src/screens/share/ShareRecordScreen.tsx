import {ScrollView, StyleSheet, View} from 'react-native';
import React, {useState} from 'react';
import WalkHistory, {WalkHistoryProps} from '@/components/my/WalkHistory';
import PrimaryButton, {
  PrimaryButtonText,
} from '@/components/buttons/PrimaryButton';
import SecondaryButton, {
  SecondaryButtonText,
} from '@/components/buttons/SecondaryButton';
import LightText from '@/components/text/LightText';
import Dialog from '@/components/containers/Dialog';
import {useMutation} from '@tanstack/react-query';
import useAuthStore from '@/store/useAuthStore';
import {shareRoute} from '@/api/walk';
import queryClient from '@/api/queryClient';
import {queryKeys} from '@/constants';

/**
 * 유저의 지난 산책 기록들을 선택해서 공유할 수 있는 스크린입니다.
 */
export default function ShareRecordScreen() {
  // 사용자 정보 불러오기
  const {user} = useAuthStore();

  // 선택 시 설정 될 산책 ID
  const [walkId, setWalkId] = useState<number | undefined>();

  // modal 관련 설정
  const [shareDialogVisibility, setShareDialogVisibility] =
    useState<boolean>(false);
  const [closeTimout, setCloseTimeout] = useState<
    ReturnType<typeof setTimeout> | undefined
  >();
  const closeShareDialog = () => {
    setShareDialogVisibility(false);
    clearTimeout(closeTimout);
  };

  const onShareItemSelected: WalkHistoryProps['onWalkSelected'] = (
    selectedWalkId: number,
  ) => {
    // 공유할 기록 선택 시 관련 작업 수행
    setWalkId(selectedWalkId);
    setShareDialogVisibility(true);
  };

  // 공유 mutation 설정
  const mutation = useMutation({
    mutationFn: (shareId: number) =>
      shareRoute(shareId, user?.userId as number),
    onSuccess: () => {
      queryClient.invalidateQueries({queryKey: [queryKeys.GET_ROUTE_LIST]});
    },
    onSettled: () => {
      // 메세지 표시 3초 후 modal 닫기
      setCloseTimeout(
        setTimeout(() => {
          closeShareDialog();
          mutation.reset();
        }, 3000),
      );
    },
  });

  // 경로 공유 시 실행
  const onShare = () => {
    if (typeof walkId === 'undefined') {
      return;
    }

    // 경로 공유 작업 진행
    mutation.mutate(walkId);
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
        {mutation.isIdle ? (
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
          <LightText>
            {mutation.isPending ? '경로 공유 중' : undefined}
            {mutation.isError ? '오류가 발생했습니다' : undefined}
            {mutation.isSuccess ? '공유가 완료되었습니다' : undefined}
          </LightText>
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
