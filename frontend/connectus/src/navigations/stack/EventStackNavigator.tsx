import React from 'react';
import EventHomeScreen from '@/screens/event/EventHomeScreen';
import {createStackNavigator} from '@react-navigation/stack';
import colors from '@/constants/colors';
import {fonts} from '@/constants';

type EventStackParamList = {
  EventHome: undefined;
  EventDetail: undefined;
  EVentCreate: undefined;
};

const Stack = createStackNavigator<EventStackParamList>();

export default function EventStackNavigator() {
  return (
    <Stack.Navigator
      screenOptions={{
        headerStyle: {
          backgroundColor: colors.background,
          borderColor: colors.dividerColor,
        },
        headerTitleStyle: {fontFamily: fonts.medium},
        headerTitleAlign: 'center',
      }}>
      <Stack.Screen
        component={EventHomeScreen}
        name="EventHome"
        options={{
          title: '진행중인 이벤트',
        }}
      />
    </Stack.Navigator>
  );
}
