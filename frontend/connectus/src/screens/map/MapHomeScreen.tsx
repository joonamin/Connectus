import React, {useRef} from 'react';
import {Dimensions, StyleSheet, View} from 'react-native';
import MapView, {PROVIDER_GOOGLE} from 'react-native-maps';
import useUserLocation from '../../hooks/useUserLocation';
import CustomButton from '@/components/buttons/CustomButton';
import MainText from '@/components/text/MainText';

export default function MapHomeScreen() {
  const mapRef = useRef<MapView | null>(null);
  const {userLocation} = useUserLocation();
  return (
    <>
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
        <CustomButton onPress={() => console.log('hello')}>
          <MainText>산책시작</MainText>
        </CustomButton>
      </View>
    </>
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
});
