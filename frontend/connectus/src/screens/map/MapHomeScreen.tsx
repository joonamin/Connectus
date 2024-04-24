import React, {useRef} from 'react';
import {StyleSheet} from 'react-native';
import MapView, {PROVIDER_GOOGLE} from 'react-native-maps';
import useUserLocation from '../../hooks/useUserLocation';

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
        }}></MapView>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});
