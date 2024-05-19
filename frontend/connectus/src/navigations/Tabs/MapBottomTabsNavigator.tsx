import React from 'react';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import EventScreen from '@/screens/event/EventHomeScreen';
import ShareHomeScreen from '@/screens/share/ShareHomeScreen';
import FeedStackNavigator, {
  FeedStackParamList,
} from '../stack/FeedStackNavigator';
import {
  NavigatorScreenParams,
  RouteProp,
  getFocusedRouteNameFromRoute,
} from '@react-navigation/native';
import colors from '@/constants/colors';
import MapStackNavigator, {MapStackParamList} from '../stack/MapStackNavigator';
import {SafeAreaView} from 'react-native';
import MyStackNavigator from '../stack/MyStackNavigator';
import ShareStackNavigator from '../stack/ShareStackNavigator';
import EventStackNavigator from '../stack/EventStackNavigator';

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
    <SafeAreaView style={{flex: 1}}>
      <Tab.Navigator
        initialRouteName="Map"
        screenOptions={({route}) => ({
          tabBarStyle: {
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
          component={ShareStackNavigator}
          options={{
            title: '기록',
            headerTitle: '',
            headerShown: false,
            tabBarIcon: () => (
              <MaterialIcons name="share" size={30} color={colors.white} />
            ),
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
          options={({route}) => ({
            tabBarStyle: (route => {
              const routeName = getFocusedRouteNameFromRoute(route) ?? '';
              if (routeName === 'WalkTest') {
                return {display: 'none'};
              }
              return {
                height: 70,
                shadowColor: colors.background,
                shadowOffset: {
                  width: 2,
                  height: 4,
                },
                elevation: 4,
              };
            })(route),
            title: '메인',
            headerTitle: '',
            headerShown: false,
            tabBarIcon: () => (
              <MaterialIcons
                name="directions-walk"
                size={30}
                color={colors.white}
              />
            ),
          })}
        />
        <Tab.Screen
          name="Event"
          component={EventStackNavigator}
          options={{
            title: '이벤트',
            headerTitle: '',
            headerShown: false,
            tabBarIcon: () => (
              <MaterialIcons
                name="star-border"
                size={30}
                color={colors.white}
              />
            ),
          }}
        />
        <Tab.Screen
          name="My"
          component={MyStackNavigator}
          options={{
            title: '마이',
            headerTitle: '',
            headerShown: false,
            tabBarIcon: () => (
              <MaterialIcons name="person" size={30} color={colors.white} />
            ),
          }}
        />
      </Tab.Navigator>
    </SafeAreaView>
  );
}
