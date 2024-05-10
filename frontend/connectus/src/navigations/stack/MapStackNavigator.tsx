import {StyleSheet, Text, View} from 'react-native';
import React, {useLayoutEffect} from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import MapHomeScreen from '@/screens/map/MapHomeScreen';
import MapWalkScreen from '@/screens/map/MapWalkScreen';
import MapResultScreen from '@/screens/map/MapResultScreen';
import BottomSheetQuickStackNavigator from './BottomSheetQuickStackNavigator';
import MapBottomSheetNavigator from '../Tabs/MapBottomSheetNavigator';
import CreateFeedScreen from '@/screens/map/BottomSheet/CreateFeedScreen';
import {LatLng} from 'react-native-maps';
import TestMapWalkScreen from '@/screens/map/TestMapWalkScreen';

export type MapStackParamList = {
  MapHome: undefined;
  MapWalk: undefined;
  MapResult: {time: number; distance: number; walkRoute: LatLng[]};
  BottomSheet: undefined;
  WalkTest: undefined;
};

const Stack = createStackNavigator<MapStackParamList>();

export default function MapStackNavigator() {
  return (
    <Stack.Navigator screenOptions={{gestureEnabled: false}}>
      <Stack.Screen
        name="MapHome"
        component={MapHomeScreen}
        options={{
          headerTitle: '',
          headerShown: false,
        }}
      />
      <Stack.Screen
        name="MapWalk"
        component={MapWalkScreen}
        options={{
          headerTitle: '',
          headerShown: false,
        }}
      />
      <Stack.Screen
        name="MapResult"
        component={MapResultScreen}
        options={{
          headerTitle: '',
          headerShown: false,
        }}
      />
      <Stack.Screen
        name="BottomSheet"
        component={BottomSheetQuickStackNavigator}
      />
      <Stack.Screen name="WalkTest" component={TestMapWalkScreen} />
    </Stack.Navigator>
  );
}
