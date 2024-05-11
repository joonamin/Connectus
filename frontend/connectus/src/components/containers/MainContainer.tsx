import React from 'react';
import {StyleSheet, View, ViewProps} from 'react-native';

export default function MainContainer({children, style, ...props}: ViewProps) {
  return (
    <View style={[styles.container, style]} {...props}>
      {children}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 15,
  },
});
