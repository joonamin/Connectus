import WalkHistory from '@/components/my/WalkHistory';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import {MyStackParamList} from '@/navigations/stack/MyStackNavigator';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {CompositeNavigationProp} from '@react-navigation/native';
import {StackNavigationProp, StackScreenProps} from '@react-navigation/stack';
import React from 'react';
import {ScrollView} from 'react-native';

export type Navigation = CompositeNavigationProp<
  StackNavigationProp<MyStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

/**
 * 산책 기록을 표시합니다
 *
 * @returns MyWalkHistoryScreen
 */
export default function MyWalkHistoryScreen({
  navigation,
}: StackScreenProps<MyStackParamList>) {
  const onWalkSelected = (walkId: number) => {
    navigation.navigate('MyWalkDetail', {
      walkId: walkId,
    });
  };

  return (
    <ScrollView>
      <WalkHistory onWalkSelected={onWalkSelected} />
    </ScrollView>
  );
}
