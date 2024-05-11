import React, {useState} from 'react';
import MapBottomTabsNavigator from '../Tabs/MapBottomTabsNavigator';
import AuthStackNavigator from '../stack/AuthStackNavigator';

export default function RootNavigator() {
  const [isLogin, setIsLogin] = useState<boolean>(false);
  return <>{isLogin ? <MapBottomTabsNavigator /> : <AuthStackNavigator />}</>;
}
