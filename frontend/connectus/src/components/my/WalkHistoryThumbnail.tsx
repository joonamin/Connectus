import React, {useContext} from 'react';
import CustomButton from '@/components/buttons/CustomButton';
import {Image, StyleSheet} from 'react-native';
import colors from '@/constants/colors';
import {OnWalkSelectedContext} from '@/contexts/WalkHistoryContext';

/**
 * WalkHistoryThumbnail의 인자를 지정합니다
 */
export interface WalkHistoryThumbnailProps {
  /**
   * 산책 기록 ID
   */
  id: number;
}

/**
 * 하나의 산책 기록에 대한 요약 이미지를 표시합니다
 *
 * @returns WalkHistoryThumbnail
 */
export default function WalkHistoryThumbnail({id}: WalkHistoryThumbnailProps) {
  const onWalkSelected = useContext(OnWalkSelectedContext);

  return (
    <CustomButton
      style={styles.historyItem}
      containerStyle={styles.historyItemContainer}
      backgroundColor="transparent"
      onPress={() => {
        if (typeof onWalkSelected !== 'undefined') {
          onWalkSelected(id);
        }
      }}>
      <Image style={styles.historyItemImage} source={defaultImage} />
    </CustomButton>
  );
}

const styles = StyleSheet.create({
  historyItemContainer: {
    flex: 1,
    flexBasis: '33.33333%',
    flexGrow: 0,
    flexShrink: 0,
    borderWidth: 1,
    borderColor: colors.background,
    borderRadius: 0,
  },
  historyItem: {
    flexBasis: '100%',
    flexShrink: 0,
    aspectRatio: '1 / 1',
    resizeMode: 'cover',
  },
  historyItemImage: {
    flexBasis: '100%',
    flexShrink: 0,
    resizeMode: 'cover',
  },
});

/**
 * 기본 이미지
 */
const defaultImage = require('@/assets/map.png');
