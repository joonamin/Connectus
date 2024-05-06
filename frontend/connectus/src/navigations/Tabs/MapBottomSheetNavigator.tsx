import QuickMenuHomeScreen from '@/screens/map/BottomSheet/QuickMenuHomeScreen';
import BottomSheetAchievmentsScreen from '@/screens/map/BottomSheetAchievmentsScreen';
import {createMaterialTopTabNavigator} from '@react-navigation/material-top-tabs';
import React from 'react';
import BottomSheetQuickStackNavigator from '../stack/BottomSheetQuickStackNavigator';
import {getFocusedRouteNameFromRoute} from '@react-navigation/core';

export type MapBottomSheetTabParamList = {
  QuickMenu: {navigateToResultScreen: () => void};
  Achievements: undefined;
  Event: undefined;
};

type BottomSheetQuickStackNavigatorProps = {
  navigateToResultScreen: () => void; // navigateToResultScreen 함수를 props로 받음
};

const Tab = createMaterialTopTabNavigator<MapBottomSheetTabParamList>();

/**
 * 바텀 시트에서 초기 접근시 상단 탭을 표시해줄 네비게이터입니다
 */
export default function MapBottomSheetNavigator({
  navigateToResultScreen,
}: BottomSheetQuickStackNavigatorProps) {
  return (
    <Tab.Navigator
      initialRouteName={'QuickMenu'}
      screenOptions={{
        tabBarLabelStyle: {
          fontSize: 18,
          fontFamily: 'GmarketSansTTFMedium',
        },
      }}>
      <Tab.Screen
        name="QuickMenu"
        component={BottomSheetQuickStackNavigator}
        initialParams={{navigateToResultScreen}}
        options={({route}) => ({
          tabBarStyle: (route => {
            const routeName = getFocusedRouteNameFromRoute(route) ?? '';
            if (
              routeName === 'FeedCreate' ||
              routeName === 'GatherCreate' ||
              routeName === 'ChatList'
            ) {
              return {display: 'none'};
            }
            return {
              display: 'flex',
            };
          })(route),
          title: '빠른메뉴',
        })}
      />
      <Tab.Screen
        name="Achievements"
        component={BottomSheetAchievmentsScreen}
        options={{
          title: '업적',
        }}
      />
    </Tab.Navigator>
  );
}
