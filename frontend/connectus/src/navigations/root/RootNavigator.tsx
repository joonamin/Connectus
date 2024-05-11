import React from 'react';
import MapBottomTabsNavigator from '../Tabs/MapBottomTabsNavigator';
import AuthStackNavigator from '../stack/AuthStackNavigator';
import useAuthStore from '@/store/useAuthStore';

export default function RootNavigator() {
  const {isLogin} = useAuthStore();
  return <>{isLogin ? <MapBottomTabsNavigator /> : <AuthStackNavigator />}</>;
}
