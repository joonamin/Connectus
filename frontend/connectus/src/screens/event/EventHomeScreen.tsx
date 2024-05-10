import {StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import EventBanner from '@/components/event/EventBanner';

export default function EventHomeScreen() {
  return (
    <MainContainer style={styles.mainContainer}>
      <EventBanner />
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    alignContent: 'center',
  },
});
