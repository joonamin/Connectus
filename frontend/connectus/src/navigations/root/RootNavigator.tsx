import React from 'react';
import MapBottomTabsNavigator from '../bottomTabs/MapBottomTabsNavigator';
import AuthStackNavigator from '../stack/AuthStackNavigator';

export default function RootNavigator() {
  return (
    <>
      {/* <AuthStackNavigator /> */}
      <MapBottomTabsNavigator />
    </>
  );
}
