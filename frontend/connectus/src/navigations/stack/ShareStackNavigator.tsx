import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import ShareHomeScreen from '@/screens/share/ShareHomeScreen';
import colors from '@/constants/colors';
import ShareDetailScreen from '@/screens/share/ShareDetailScreen';
import ShareRecordScreen from '@/screens/share/ShareRecordScreen';

export type ShareStackParamList = {
  ShareHome: undefined;
  ShareDetail: undefined;
  ShareRecord: undefined;
};

const Stack = createStackNavigator<ShareStackParamList>();

export default function ShareStackNavigator() {
  return (
    <Stack.Navigator
      initialRouteName="ShareHome"
      screenOptions={{
        headerStyle: {
          backgroundColor: colors.background,
          borderColor: colors.dividerColor,
        },
        headerTitleAlign: 'center',
      }}>
      <Stack.Screen
        name="ShareHome"
        component={ShareHomeScreen}
        options={{title: '기록 공유'}}
      />
      <Stack.Screen
        name="ShareDetail"
        component={ShareDetailScreen}
        options={{title: '', headerShown: false}}
      />
      <Stack.Screen
        name="ShareRecord"
        component={ShareRecordScreen}
        options={{title: '내 산책기록 공유'}}
      />
    </Stack.Navigator>
  );
}
