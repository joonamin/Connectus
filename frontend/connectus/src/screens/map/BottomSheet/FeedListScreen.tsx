import {StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import BottomSheetFeedPreview from '@/components/map/BottomSheetFeedPreview';
import {BottomSheetScrollView} from '@gorhom/bottom-sheet';
import colors from '@/constants/colors';

/**
 * map screen에서 마커를 클릭시 보여줄 FeedListScreen입니다
 */
export default function FeedListScreen() {
  return (
    <BottomSheetScrollView>
      <MainContainer style={styles.mainContainer}>
        <BottomSheetFeedPreview
          isLiked={false}
          commentNumber={32}
          likeNumber={21}
        />
      </MainContainer>
    </BottomSheetScrollView>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    backgroundColor: colors.background,
  },
});
