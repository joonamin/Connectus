import QuickMenuHomeScreen from '@/screens/map/BottomSheet/QuickMenuHomeScreen';
import BottomSheetAchievmentsScreen from '@/screens/map/BottomSheetAchievmentsScreen';
import {createMaterialTopTabNavigator} from '@react-navigation/material-top-tabs';
import React from 'react';
import BottomSheetQuickStackNavigator from '../stack/BottomSheetQuickStackNavigator';

export type MapTabParamList = {
  Achievements: undefined;
  QuickMenu: undefined;
  Event: undefined;
};
const Tab = createMaterialTopTabNavigator<MapTabParamList>();

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
        options={{
          title: '빠른메뉴',
        }}
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
