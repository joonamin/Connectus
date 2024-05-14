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
import Dialog from '@/components/containers/Dialog';
import LightText from '@/components/text/LightText';
import PrimaryButton, {
  PrimaryButtonText,
} from '@/components/buttons/PrimaryButton';
import {LoginRequest, LoginResponse, login} from '@/api/user';
import {validateInput} from '@/utils/validate';
import {AxiosError, AxiosResponse} from 'axios';

type Navigate = StackNavigationProp<AuthStackParamList>;

export default function AuthLoginScreen() {
  const navigation = useNavigation<Navigate>();
  const handlePressRegister = () => {
    navigation.pop();
    navigation.navigate('AuthRegister');
  };

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const [dialogVisibility, setDialogVisibility] = useState<boolean>(false);
  const [dialogMessage, setDialogMessage] = useState<string | undefined>();

  const openDialog = () => {
    setDialogVisibility(true);
  };
  const closeDialog = () => {
    setDialogVisibility(false);
  };

  const validate = (): string | false => {
    let message: string | undefined = '';

    if (
      (message = validateInput('email', email)) ||
      (message = validateInput('password', password))
    ) {
      return message;
    }

    return false;
  };

  const onLogin = () => {
    let message: ReturnType<typeof validate> = '';

    // 입력 확인
    if ((message = validate())) {
      setDialogMessage(message);
      openDialog();

      return;
    }

    // 입력 확인 완료 - 로그인 요청 진행
    const request: LoginRequest = {
      email: email,
      password: password,
    };
    login(request)
      .then((response: LoginResponse) => {
        // 로그인 완료
        setDialogMessage(response.accessToken);
        openDialog();
      })
      .catch((error: AxiosError) => {
        let data: any;

        // 서버의 응답이 있는 경우 출력
        if ((data = (error?.response as AxiosResponse)?.data)) {
          setDialogMessage(data);
        } else {
          setDialogMessage('회원가입에 실패했습니다');
        }
        openDialog();
      });
  };

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
        <Pressable style={styles.loginButton} onPress={onLogin}>
          <MainText>로그인</MainText>
        </Pressable>
        <Pressable style={styles.registerButton} onPress={handlePressRegister}>
          <Text style={styles.registerText}>회원가입</Text>
        </Pressable>
        <Dialog
          style={styles.modal}
          visible={dialogVisibility}
          onRequestClose={closeDialog}>
          <LightText>{dialogMessage}</LightText>
          <View style={styles.buttonRow}>
            <PrimaryButton onPress={closeDialog}>
              <PrimaryButtonText>확인</PrimaryButtonText>
            </PrimaryButton>
          </View>
        </Dialog>
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
  modal: {
    gap: 30,
    alignItems: 'center',
  },
  buttonRow: {
    gap: 15,
    flexDirection: 'row',
  },
});
