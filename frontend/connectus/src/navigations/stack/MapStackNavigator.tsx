import {StyleSheet, Text, View} from 'react-native';
import React, {useLayoutEffect} from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import MapHomeScreen from '@/screens/map/MapHomeScreen';
import MapWalkScreen from '@/screens/map/MapWalkScreen';

export type MapStackParamList = {
  MapHome: undefined;
  MapWalk: undefined;
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
    </Stack.Navigator>
  );
}
