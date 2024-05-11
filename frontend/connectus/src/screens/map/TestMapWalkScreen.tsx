import {SafeAreaView, StyleSheet} from 'react-native';
import React, {useEffect, useRef, useState} from 'react';
import MapView from 'react-native-map-clustering';
import {PROVIDER_GOOGLE, Region} from 'react-native-maps';
import Geolocation from '@react-native-community/geolocation';

type deltaType = {
  latitudeDelta: number;
  longitudeDelta: number;
};

export default function TestMapWalkScreen() {
  const mapRef = useRef<MapView | null>(null);
  const [currentPos, setCurrentPos] = useState<Region | undefined>(undefined);

  const setUserLocation = () => {
    Geolocation.getCurrentPosition(index => {
      setCurrentPos({
        latitude: index.coords.latitude,
        longitude: index.coords.longitude,
        longitudeDelta: 0.001,
        latitudeDelta: 0.001,
      });
    });
  };

  useEffect(() => {
    Geolocation.getCurrentPosition(index => {
      setCurrentPos({
        latitude: index.coords.latitude,
        longitude: index.coords.longitude,
        longitudeDelta: 0.001,
        latitudeDelta: 0.001,
      });
    });
  }, []);

  return (
    <SafeAreaView style={styles.flex}>
      <MapView
        ref={mapRef}
        provider={PROVIDER_GOOGLE}
        style={styles.flex}
        showsUserLocation
        followsUserLocation
        initialRegion={currentPos}
        onMapReady={setUserLocation}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  flex: {flex: 1},
});
