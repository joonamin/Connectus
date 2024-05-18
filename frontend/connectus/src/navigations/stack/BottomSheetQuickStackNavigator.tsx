import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import QuickMenuHomeScreen from '@/screens/map/BottomSheet/QuickMenuHomeScreen';
import CreateFeedScreen from '@/screens/map/BottomSheet/CreateFeedScreen';
import CreateGatherScreen from '@/screens/map/BottomSheet/CreateGatherScreen';
import {fonts} from '@/constants';
import ChatListScreen from '@/screens/map/BottomSheet/ChatListScreen';
import FeedListScreen from '@/screens/map/BottomSheet/FeedListScreen';
import FeedDetailScreen from '@/screens/map/BottomSheet/FeedDetailScreen';
import MarkerSelectScreen from '@/screens/map/BottomSheet/MarkerSelectScreen';
import GatherScreen from '@/screens/map/BottomSheet/GatherScreen';
import {LatLng} from 'react-native-maps';

export type BottomSheetStackParamList = {
  Home: undefined;
  FeedCreate: undefined;
  GatherCreate: undefined;
  ChatList: undefined;
  MarkerList: undefined;
  FeedList: undefined;
  Feed: {feedId: number; coordinate: LatLng};
  Gather: {gatherId: number};
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
      <Stack.Screen name={'MarkerList'} component={MarkerSelectScreen} />
      <Stack.Screen name={'FeedList'} component={FeedListScreen} />
      <Stack.Screen
        name={'Feed'}
        component={FeedDetailScreen}
        options={{
          headerTitle: '',
        }}
      />
      <Stack.Screen
        name={'Gather'}
        component={GatherScreen}
        options={{
          headerTitle: '',
        }}
      />
    </Stack.Navigator>
  );
}
