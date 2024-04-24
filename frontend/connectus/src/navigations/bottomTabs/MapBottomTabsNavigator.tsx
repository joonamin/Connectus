import React from 'react';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import MapHomeScreen from '../../screens/map/MapHomeScreen';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import EventScreen from '../../screens/event/EventHomeScreen';
import FeedHomeScreen from '../../screens/feed/FeedHomeScreen';
import MyHomeScreen from '../../screens/my/MyHomeScreen';
import ShareHomeScreen from '../../screens/share/ShareHomeScreen';
const Tab = createBottomTabNavigator();

export default function MapBottomTabsNavigator() {
  return (
    <Tab.Navigator
      initialRouteName="MapHome"
      screenOptions={{
        tabBarLabelStyle: {
          fontSize: 12,
        },
      }}>
      <Tab.Screen
        name="Share"
        component={FeedHomeScreen}
        options={{
          title: '기록',
          headerTitle: '',
          headerShown: false,
          tabBarIcon: () => <MaterialIcons name="share" size={30} />,
        }}
      />
      <Tab.Screen
        name="Feed"
        component={ShareHomeScreen}
        options={{
          title: '피드',
          headerTitle: '',
          headerShown: false,
          tabBarIcon: () => <MaterialIcons name="feed" size={30} />,
        }}
      />
      <Tab.Screen
        name="MapHome"
        component={MapHomeScreen}
        options={{
          title: '메인',
          headerTitle: '',
          headerShown: false,
          tabBarIcon: () => <MaterialIcons name="directions-walk" size={30} />,
        }}
      />
      <Tab.Screen
        name="Event"
        component={EventScreen}
        options={{
          title: '이벤트',
          headerTitle: '',
          headerShown: false,
          tabBarIcon: () => <MaterialIcons name="star-border" size={30} />,
        }}
      />
      <Tab.Screen
        name="My"
        component={MyHomeScreen}
        options={{
          title: '마이',
          headerTitle: '',
          headerShown: false,
          tabBarIcon: () => <MaterialIcons name="person" size={30} />,
        }}
      />
    </Tab.Navigator>
  );
}
