import {StyleSheet, Text, View} from 'react-native';
import React from 'react';

export default function QuickMenuHomeScreen() {
  return (
    <View style={styles.mainContainer}>
      <Text>QuickMenuHomeScreen</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    alignItems: 'center',
    padding: 15,
  },
});
