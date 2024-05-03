import {Pressable, StyleSheet, View, Image} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import MainText from '@/components/text/MainText';
import colors from '@/constants/colors';

const DUMMY_USER = ['한방 옻닭', '갓영욱'];

export default function ChatListScreen() {
  return (
    <MainContainer style={styles.chatListContainer}>
      {DUMMY_USER.map(user => {
        return (
          <Pressable style={styles.chatButton} key={user}>
            <View style={styles.profileImageContainer}>
              <Image
                style={styles.profileImage}
                source={require('@/assets/default-profile.png')}
              />
            </View>
            <MainText>{user}</MainText>
          </Pressable>
        );
      })}
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  chatListContainer: {
    gap: 15,
  },
  profileImageContainer: {
    width: 44,
    height: 44,
    borderRadius: 50,
  },
  profileImage: {
    width: '100%',
    height: '100%',
  },
  chatButton: {
    backgroundColor: colors.buttonBackground,
    flexDirection: 'row',
    alignItems: 'center',
    padding: 15,
    gap: 15,
    borderRadius: 15,
  },
});
