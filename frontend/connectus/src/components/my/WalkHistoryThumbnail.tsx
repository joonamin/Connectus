import React from 'react';
import CustomButton from '@/components/buttons/CustomButton';
import {Image, StyleSheet} from 'react-native';
import {Navigation} from '@/screens/my/MyWalkHistoryScreen';
import {useNavigation} from '@react-navigation/native';
import colors from '@/constants/colors';

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
  const navigation = useNavigation<Navigation>();

  return (
    <CustomButton
      style={styles.historyItem}
      backgroundColor="transparent"
      onPress={() => {
        navigation.navigate('MyWalkDetail', {
          walkId: id,
        });
      }}>
      <Image style={styles.historyItemImage} source={defaultImage} />
    </CustomButton>
  );
}

const styles = StyleSheet.create({
  historyItem: {
    flex: 1,
    aspectRatio: '1 / 1',
    flexBasis: '33.33333%',
    flexGrow: 0,
    flexShrink: 0,
    resizeMode: 'cover',
    borderWidth: 1,
    borderColor: colors.background,
    borderRadius: 0,
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
