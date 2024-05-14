import React from 'react';
import MapBottomTabsNavigator from '../Tabs/MapBottomTabsNavigator';
import AuthStackNavigator from '../stack/AuthStackNavigator';
import useAuthStore from '@/store/useAuthStore';

export default function RootNavigator() {
  const {user} = useAuthStore();
  return <>{user ? <MapBottomTabsNavigator /> : <AuthStackNavigator />}</>;
}
