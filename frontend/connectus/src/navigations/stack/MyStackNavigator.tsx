import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import colors from '@/constants/colors';
import MyHomeScreen from '@/screens/my/MyHomeScreen';
import MyAchievementsScreen from '@/screens/my/MyAchievementsScreen';

export type MyStackParamList = {
  MyHome: undefined;
  MyAchievements: undefined;
};

const Stack = createStackNavigator<MyStackParamList>();

function MyStackNavigator() {
  return (
    <Stack.Navigator
      initialRouteName="MyHome"
      screenOptions={{
        headerStyle: {
          backgroundColor: colors.background,
          borderColor: colors.dividerColor,
        },
      }}>
      <Stack.Screen
        name="MyHome"
        component={MyHomeScreen}
        options={{
          title: '마이페이지',
          headerShown: false,
        }}
      />
      <Stack.Screen
        name="MyAchievements"
        component={MyAchievementsScreen}
        options={{title: '업적'}}
      />
    </Stack.Navigator>
  );
}

export default MyStackNavigator;
