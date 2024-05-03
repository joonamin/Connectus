import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import FeedHomeScreen from '@/screens/feed/FeedHomeScreen';
import FeedDetailScreen from '@/screens/feed/FeedDetailScreen';
import colors from '@/constants/colors';

export type FeedStackParamList = {
  FeedHome: undefined;
  FeedDetail: undefined;
};

const Stack = createStackNavigator<FeedStackParamList>();

function FeedStackNavigator() {
  return (
    <Stack.Navigator
      initialRouteName="FeedHome"
      screenOptions={{
        gestureEnabled: false,
        headerStyle: {
          backgroundColor: colors.background,
          borderColor: colors.dividerColor,
        },
        headerTitleAlign: 'center',
      }}>
      <Stack.Screen
        name="FeedHome"
        component={FeedHomeScreen}
        options={{
          title: '방명록 공유',
        }}
      />
      <Stack.Screen
        name="FeedDetail"
        component={FeedDetailScreen}
        options={{
          title: '',
        }}
      />
    </Stack.Navigator>
  );
}

export default FeedStackNavigator;
