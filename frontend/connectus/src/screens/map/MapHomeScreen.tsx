import React, {useRef} from 'react';
import {Dimensions, SafeAreaView, StyleSheet, Text, View} from 'react-native';
import MapView, {PROVIDER_GOOGLE} from 'react-native-maps';
import useUserLocation from '../../hooks/useUserLocation';
import CustomButton from '@/components/buttons/CustomButton';
import MainText from '@/components/text/MainText';
import {CompositeNavigationProp, useNavigation} from '@react-navigation/native';
import {StackNavigationProp} from '@react-navigation/stack';
import {BottomTabNavigationProp} from '@react-navigation/bottom-tabs';
import {MapStackParamList} from '@/navigations/stack/MapStackNavigator';
import {BottomTabParamList} from '@/navigations/Tabs/MapBottomTabsNavigator';

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
        <CustomButton onPress={handlePressStart}>
          <MainText>산책시작</MainText>
        </CustomButton>
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
});
