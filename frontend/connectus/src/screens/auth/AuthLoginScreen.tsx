import {Pressable, SafeAreaView, StyleSheet, Text, View} from 'react-native';
import React, {useState} from 'react';
import MainContainer from '@/components/containers/MainContainer';
import MainText from '@/components/text/MainText';
import {fonts} from '@/constants';
import colors from '@/constants/colors';
import AuthInput from '@/components/text/AuthInput';
import {useNavigation} from '@react-navigation/native';
import {AuthStackParamList} from '@/navigations/stack/AuthStackNavigator';
import {StackNavigationProp} from '@react-navigation/stack';

type Navigate = StackNavigationProp<AuthStackParamList>;

export default function AuthLoginScreen() {
  const navigation = useNavigation<Navigate>();

  const handlePressRegister = () => {
    navigation.pop();
    navigation.navigate('AuthRegister');
  };

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  return (
    <MainContainer style={styles.flex}>
      <SafeAreaView style={styles.mainContainer}>
        <AuthInput
          autoFocus
          inputType="email"
          value={email}
          handleChange={setEmail}
          placeholder="아이디를 입력해주세요"
          keyboardType="email-address"
        />
        <AuthInput
          inputType="password"
          value={password}
          handleChange={setPassword}
          placeholder="비밀번호를 입력해주세요"
          secureTextEntry
        />
        <Pressable style={styles.loginButton}>
          <MainText>로그인</MainText>
        </Pressable>
        <Pressable style={styles.registerButton} onPress={handlePressRegister}>
          <Text style={styles.registerText}>회원가입</Text>
        </Pressable>
      </SafeAreaView>
    </MainContainer>
  );
}

const styles = StyleSheet.create({
  flex: {
    flex: 1,
    width: '100%',
    alignItems: 'center',
  },
  mainContainer: {
    flex: 1,
    width: '100%',
    gap: 15,
    alignItems: 'center',
  },
  Text: {
    fontFamily: fonts.medium,
    color: colors.white,
    fontSize: 24,
  },
  registerButton: {
    marginTop: 15,
    borderBottomWidth: 2,
    borderBottomColor: colors.white,
    paddingBottom: 3,
  },
  registerText: {
    color: colors.white,
    fontSize: 18,
  },
  loginButton: {
    marginTop: 45,
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.primaryColorBlue,
    padding: 15,
    borderRadius: 15,
  },
});
