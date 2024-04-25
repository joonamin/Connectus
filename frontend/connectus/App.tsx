import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import RootNavigator from './src/navigations/root/RootNavigator';
import {defaultTheme} from '@/constants/themes';

function App(): React.JSX.Element {
  return (
    <NavigationContainer theme={defaultTheme}>
      <RootNavigator />
    </NavigationContainer>
  );
}

export default App;
