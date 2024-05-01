import {StyleSheet, Text, View} from 'react-native';
import React from 'react';

export default function BottomSheetAchievmentsScreen() {
  return (
    <View style={styles.achievementContainer}>
      <Text>BottomSheetAchievmentsScreen</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  achievementContainer: {
    padding: 15,
    flex: 1,
    alignItems:'center',
  },
});
