import React from 'react';
import AuthStackNavigator from '../stack/AuthStackNavigator';
import MapBottomTabsNavigator from '../bottomTabs/MapBottomTabsNavigator';

export default function RootNavigator() {
  return (
    <>
      {/* <AuthStackNavigator /> */}
      <MapBottomTabsNavigator />
    </>
  );
}
