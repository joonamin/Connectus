import React from 'react';
import {StyleSheet, ViewProps} from 'react-native';
import colors from '@/constants/colors';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import Ionicons from 'react-native-vector-icons/Ionicons';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import {IconProps} from 'react-native-vector-icons/Icon';
import ListItem from '@/components/containers/ListItem';
import MainText from '@/components/text/MainText';

export interface IconListItemProps extends ViewProps {
  /**
   * 지원하는 아이콘 타입
   */
  iconType?: 'MaterialIcons' | 'Ionicons' | 'MaterialCommunityIcons';
  /**
   * MaterialIcons 내 icon 이름
   */
  iconName: IconProps['name'];
  /**
   * ListItem 내 텍스트
   */
  text: string;
  /**
   * ListItem 내 arrow를 표시합니다
   */
  actionable?: boolean;
}

/**
 * Icon을 포함한 list item을 생성합니다
 *
 * @returns IconListItem
 */
export default function IconListItem({
  style,
  iconType,
  iconName,
  text,
  actionable,
  ...props
}: IconListItemProps) {
  const styles = StyleSheet.create({
    listItem: {
      gap: 15,
    },
    listItemIcon: {
      flexBasis: 30,
      flexGrow: 0,
      flexShrink: 0,
    },
    listItemText: {
      flexGrow: 1,
    },
  });

  // icon 설정
  let icon;
  const iconProps: IconProps = {
    name: iconName,
    size: 30,
    color: colors.white,
    style: styles.listItemIcon,
  };
  switch (iconType) {
    case 'Ionicons':
      icon = <Ionicons {...iconProps} />;
      break;

    case 'MaterialCommunityIcons':
      icon = <MaterialCommunityIcons {...iconProps} />;
      break;

    case 'MaterialIcons':
    default:
      icon = <MaterialIcons {...iconProps} />;
  }

  return (
    <ListItem style={[styles.listItem, style]} {...props}>
      {icon}
      <MainText
        ellipsizeMode="tail"
        numberOfLines={1}
        style={styles.listItemText}>
        {text}
      </MainText>
      {actionable ? (
        <MaterialIcons
          name="keyboard-arrow-right"
          size={30}
          color={colors.white}
        />
      ) : undefined}
    </ListItem>
  );
}
