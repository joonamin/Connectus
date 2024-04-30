import React from 'react';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import EventScreen from '@/screens/event/EventHomeScreen';
import MyHomeScreen from '@/screens/my/MyHomeScreen';
import ShareHomeScreen from '@/screens/share/ShareHomeScreen';
import FeedStackNavigator, {
  FeedStackParamList,
} from '../stack/FeedStackNavigator';
import {NavigatorScreenParams, RouteProp} from '@react-navigation/native';
import colors from '@/constants/colors';
import MapStackNavigator, {MapStackParamList} from '../stack/MapStackNavigator';

export type BottomTabParamList = {
  Share: undefined;
  Feed: NavigatorScreenParams<FeedStackParamList>;
  Map: NavigatorScreenParams<MapStackParamList>;
  Event: undefined;
  My: undefined;
};

const Tab = createBottomTabNavigator<BottomTabParamList>();

export default function MapBottomTabsNavigator(
  navigation: any,
  route: RouteProp<BottomTabParamList>,
) {
  return (
    <Tab.Navigator
      initialRouteName="Map"
      screenOptions={({route}) => ({
        tabBarStyle: {
          // display: route.name === 'Map' ? 'none' : 'flex',
          height: 70,
          shadowColor: colors.background,
          shadowOffset: {
            width: 2,
            height: 4,
          },
          elevation: 4,
        },
        tabBarLabelStyle: {
          fontFamily: 'GmarketSansTTFMedium',
          fontSize: 16,
        },
        tabBarHideOnKeyboard: true,
      })}>
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
          tabBarIcon: () => (
            <MaterialIcons name="feed" size={30} color={colors.white} />
          ),
        }}
      />
      <Tab.Screen
        name="Map"
        component={MapStackNavigator}
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
