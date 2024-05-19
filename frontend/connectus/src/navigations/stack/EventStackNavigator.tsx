import React from 'react';
import EventHomeScreen from '@/screens/event/EventHomeScreen';
import {createStackNavigator} from '@react-navigation/stack';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import EventDetailScreen from '@/screens/event/EventDetailScreen';
import EventCreateScreen from '@/screens/event/EventCreateScreen';
import EventSelectPosScreen from '@/screens/event/EventSelectPosScreen';

export type EventStackParamList = {
  EventHome: undefined;
  EventDetail: {eventId: number};
  EventCreate: undefined;
  EventPosSelect: undefined;
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
        headerTitleStyle: {fontFamily: fonts.medium, fontSize: 24},
        headerTitleAlign: 'center',
      }}>
      <Stack.Screen
        component={EventHomeScreen}
        name="EventHome"
        options={{
          title: '진행중인 이벤트',
        }}
      />
      <Stack.Screen
        component={EventDetailScreen}
        name="EventDetail"
        options={{
          title: '이벤트 상세정보',
        }}
      />
      <Stack.Screen
        component={EventCreateScreen}
        name="EventCreate"
        options={{
          title: '이벤트 생성',
          headerBackTitle: '',
        }}
      />
      <Stack.Screen
        component={EventSelectPosScreen}
        name="EventPosSelect"
        options={{
          headerTitle: '',
          headerShown: false,
        }}
      />
    </Stack.Navigator>
  );
}
