import QuickMenuHomeScreen from '@/screens/map/BottomSheet/QuickMenuHomeScreen';
import BottomSheetAchievmentsScreen from '@/screens/map/BottomSheetAchievmentsScreen';
import {createMaterialTopTabNavigator} from '@react-navigation/material-top-tabs';
import React from 'react';
import BottomSheetQuickStackNavigator from '../stack/BottomSheetQuickStackNavigator';
import {getFocusedRouteNameFromRoute} from '@react-navigation/core';

export type MapBottomSheetTabParamList = {
  Achievements: undefined;
  QuickMenu: undefined;
  Event: undefined;
};
const Tab = createMaterialTopTabNavigator<MapBottomSheetTabParamList>();

export default function MapBottomSheetNavigator() {
  return (
    <Tab.Navigator
      initialRouteName="QuickMenu"
      screenOptions={{
        tabBarLabelStyle: {
          fontSize: 18,
          fontFamily: 'GmarketSansTTFMedium',
        },
      }}>
      <Tab.Screen
        name="QuickMenu"
        component={BottomSheetQuickStackNavigator}
        options={({route}) => ({
          tabBarStyle: (route => {
            const routeName = getFocusedRouteNameFromRoute(route) ?? '';
            if (routeName === 'FeedCreate') {
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
