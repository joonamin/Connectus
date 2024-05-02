import {StyleSheet, Text, View} from 'react-native';
import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import QuickMenuHomeScreen from '@/screens/map/BottomSheet/QuickMenuHomeScreen';
import CreateFeedScreen from '@/screens/map/BottomSheet/CreateFeedScreen';
import CreateGatherScreen from '@/screens/map/BottomSheet/CreateGatherScreen';

export type BottomSheetStackParamList = {
  Home: undefined;
  FeedCreate: undefined;
  GatherCreate: undefined;
};

const Stack = createStackNavigator();

export default function BottomSheetQuickStackNavigator() {
  return (
    <Stack.Navigator>
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
          headerTitle: '',
          headerShown: false,
        }}
      />
      <Stack.Screen
        name={'GatherCreate'}
        component={CreateGatherScreen}
        options={{
          headerTitle: '',
          headerShown: false,
        }}
      />
    </Stack.Navigator>
  );
}
