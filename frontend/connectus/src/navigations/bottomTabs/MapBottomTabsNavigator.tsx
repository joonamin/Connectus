import React from 'react';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import MapHomeScreen from '@/screens/map/MapHomeScreen';
import EventScreen from '@/screens/event/EventHomeScreen';
import FeedHomeScreen from '@/screens/feed/FeedHomeScreen';
import MyHomeScreen from '@/screens/my/MyHomeScreen';
import ShareHomeScreen from '@/screens/share/ShareHomeScreen';
import FeedStackNavigator, {
  FeedStackParamList,
} from '../stack/FeedStackNavigator';
import {NavigatorScreenParams, RouteProp} from '@react-navigation/native';

export type BottomTabParamList = {
  Share: undefined;
  Feed: NavigatorScreenParams<FeedStackParamList>;
  Map: undefined;
  Event: undefined;
  My: undefined;
};

const Tab = createBottomTabNavigator<BottomTabParamList>();

export default function MapBottomTabsNavigator(
  route: RouteProp<BottomTabParamList>,
) {
  return (
    <Tab.Navigator
      initialRouteName="Map"
      screenOptions={{
        tabBarLabelStyle: {
          fontSize: 12,
        },
      }}>
      <Tab.Screen
        name="Share"
        component={ShareHomeScreen}
        options={{
          title: '기록',
          headerTitle: '',
          headerShown: false,
          tabBarIcon: () => <MaterialIcons name="share" size={30} />,
        }}
      />
      <Tab.Screen
        name="Feed"
        component={FeedStackNavigator}
        options={{
          title: '피드',
          headerTitle: '',
          headerShown: false,
          tabBarIcon: () => <MaterialIcons name="feed" size={30} />,
        }}
      />
      <Tab.Screen
        name="Map"
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
