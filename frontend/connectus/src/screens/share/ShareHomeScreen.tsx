import {StyleSheet, Text, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import SharePost from '@/components/share/SharePost';

export default function ShareHomeScreen() {
  return (
    <MainContainer>
      <SharePost />
    </MainContainer>
  );
}

const styles = StyleSheet.create({});
