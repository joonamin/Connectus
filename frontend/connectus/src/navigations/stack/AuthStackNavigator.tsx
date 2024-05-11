import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import AuthHomeScreen from '@/screens/auth/AuthHomeScreen';
import AuthLoginScreen from '@/screens/auth/AuthLoginScreen';
import AuthRegisterScreen from '@/screens/auth/AuthRegisterScreen';

export type AuthStackParamList = {
  AuthHome: undefined;
  AuthLogin: undefined;
  AuthRegister: undefined;
};

const Stack = createStackNavigator<AuthStackParamList>();

function AuthStackNavigator() {
  return (
    <Stack.Navigator>
      <Stack.Screen
        name="AuthHome"
        component={AuthHomeScreen}
        options={{
          headerTitle: '',
          headerShown: false,
        }}
      />
      <Stack.Screen
        name="AuthLogin"
        component={AuthLoginScreen}
        options={{
          headerTitle: '로그인',
        }}
      />
      <Stack.Screen
        name="AuthRegister"
        component={AuthRegisterScreen}
        options={{
          headerTitle: '회원가입',
        }}
      />
    </Stack.Navigator>
  );
}

export default AuthStackNavigator;
