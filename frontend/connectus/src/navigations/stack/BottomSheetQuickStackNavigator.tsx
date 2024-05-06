import {StyleSheet, Text, View} from 'react-native';
import React from 'react';
import {
  StackNavigationProp,
  createStackNavigator,
} from '@react-navigation/stack';
import QuickMenuHomeScreen from '@/screens/map/BottomSheet/QuickMenuHomeScreen';
import CreateFeedScreen from '@/screens/map/BottomSheet/CreateFeedScreen';
import CreateGatherScreen from '@/screens/map/BottomSheet/CreateGatherScreen';
import {fonts} from '@/constants';
import ChatListScreen from '@/screens/map/BottomSheet/ChatListScreen';
import {MaterialTopTabNavigationProp} from '@react-navigation/material-top-tabs';
import {MapBottomSheetTabParamList} from '../Tabs/MapBottomSheetNavigator';

type BottomSheetQuickStackNavigatorProps = {
  navigateToResultScreen: () => void; // navigateToResultScreen 함수를 props로 받음
};

export type BottomSheetStackParamList = {
  Home: {navigateToResultScreen: () => void};
  FeedCreate: undefined;
  GatherCreate: undefined;
  ChatList: undefined;
};

const Stack = createStackNavigator<BottomSheetStackParamList>();

/**
 * 바텀시트에서 빠른메뉴에서 표시할 스크린들을 관리할 네비게이터입니다.
 */
export default function BottomSheetQuickStackNavigator() {
  return (
    <Stack.Navigator
      screenOptions={{
        headerTitleAlign: 'center',
        headerTitleStyle: {
          fontFamily: fonts.medium,
        },
      }}>
      <Stack.Screen
        name={'Home'}
        component={QuickMenuHomeScreen}
        options={{
          headerTitle: '',
          headerShown: false,
        }}
      />
      <Stack.Screen
        name={'FeedCreate'}
        component={CreateFeedScreen}
        options={{
          headerTitle: '글작성',
        }}
      />
      <Stack.Screen
        name={'GatherCreate'}
        component={CreateGatherScreen}
        options={{
          headerTitle: '모여라',
        }}
      />
      <Stack.Screen
        name={'ChatList'}
        component={ChatListScreen}
        options={{
          headerTitle: '채팅',
        }}
      />
    </Stack.Navigator>
  );
}
