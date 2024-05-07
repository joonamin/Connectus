import React, {useRef} from 'react';
import {
  Dimensions,
  Pressable,
  SafeAreaView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import MapView, {PROVIDER_GOOGLE} from 'react-native-maps';
import useUserLocation from '../../hooks/useUserLocation';
import MainText from '@/components/text/MainText';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';
import colors from '@/constants/colors';

type Navigation = CompositeNavigationProp<
  StackNavigationProp<MapStackParamList>,
  BottomTabNavigationProp<BottomTabParamList>
>;

export default function MapHomeScreen() {
  const mapRef = useRef<MapView | null>(null);
  const navigation = useNavigation<Navigation>();
  const {userLocation} = useUserLocation();

  const handlePressStart = () => {
    navigation.navigate('MapWalk');
  };

  return (
    <SafeAreaView style={{flex: 1}}>
      <MapView
        ref={mapRef}
        style={styles.container}
        provider={PROVIDER_GOOGLE}
        showsUserLocation
        followsUserLocation
        showsMyLocationButton={true}
        region={{
          ...userLocation,
          latitudeDelta: 0.012,
          longitudeDelta: 0.011,
        }}>
        {}
      </MapView>
      <View style={styles.buttonContainer}>
        <Pressable style={styles.startButton} onPress={handlePressStart}>
          <MainText>산책시작</MainText>
        </Pressable>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  buttonContainer: {
    position: 'absolute',
    bottom: 0,
    width: Dimensions.get('screen').width,
    backgroundColor: 'transparent',
  },
  bottomSheetContainer: {
    flex: 1,
    alignItems: 'center',
  },
  startButton: {
    width: '100%',
    backgroundColor: colors.primaryColorBlue,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 15,
    borderRadius: 15,
  },
});
