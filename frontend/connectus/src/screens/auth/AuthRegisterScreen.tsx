import {
  KeyboardAvoidingView,
  Platform,
  Pressable,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import React, {useState} from 'react';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import MainText from '@/components/text/MainText';
import AuthInput from '@/components/text/AuthInput';
import MainContainer from '@/components/containers/MainContainer';
import {StackNavigationProp} from '@react-navigation/stack';
import {AuthStackParamList} from '@/navigations/stack/AuthStackNavigator';
import {useNavigation} from '@react-navigation/native';
import {validateInput} from '@/utils/validate';
import Dialog from '@/components/containers/Dialog';
import LightText from '@/components/text/LightText';
import PrimaryButton, {
  PrimaryButtonText,
} from '@/components/buttons/PrimaryButton';
import {RegisterRequest, register} from '@/api/user';
import SecondaryButton, {
  SecondaryButtonText,
} from '@/components/buttons/SecondaryButton';
import {AxiosError, AxiosResponse} from 'axios';

type Navigate = StackNavigationProp<AuthStackParamList>;

export default function AuthRegisterScreen() {
  const navigation = useNavigation<Navigate>();

  const handlePressRegister = () => {
    closeDialog();
    navigation.pop();
    navigation.navigate('AuthLogin');
  };

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordConfirm, setPasswordConfirm] = useState('');
  const [nickname, setNickname] = useState<string>('');

  const [dialogVisibility, setDialogVisibility] = useState<boolean>(false);
  const [dialogMessage, setDialogMessage] = useState<string | undefined>();
  const [registrationComplete, setRegistrationComplete] =
    useState<boolean>(false);

  const openDialog = () => {
    setDialogVisibility(true);
  };
  const closeDialog = () => {
    setRegistrationComplete(false);
    setDialogVisibility(false);
  };

  const validate = (): string | false => {
    let message: string | undefined = '';

    if (
      (message = validateInput('email', email)) ||
      (message = validateInput('nickname', nickname)) ||
      (message = validateInput('password', password)) ||
      (message = validateInput('passwordConfirm', {
        value: password,
        checkValue: passwordConfirm,
      }))
    ) {
      return message;
    }

    return false;
  };

  const onRegistration = () => {
    let message: ReturnType<typeof validate> = '';

    // 입력 확인
    if ((message = validate())) {
      setDialogMessage(message);
      openDialog();

      return;
    }

    // 입력 확인 완료 - 로그인 요청 진행
    const request: RegisterRequest = {
      email: email,
      password: password,
      nickname: nickname,
    };
    register(request)
      .then(() => {
        setDialogMessage('회원가입이 완료되었습니다');
        setRegistrationComplete(true);
      })
      .catch((error: AxiosError) => {
        let data: any;

        // 서버의 응답이 있는 경우 출력
        if ((data = (error?.response as AxiosResponse)?.data)) {
          setDialogMessage(data);
        } else {
          setDialogMessage('회원가입에 실패했습니다');
        }
      })
      .finally(() => {
        openDialog();
      });
  };

  return (
    <MainContainer style={styles.flex}>
      <SafeAreaView>
        <KeyboardAvoidingView
          behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
          style={Platform.OS === 'ios' && styles.kbdView}>
          <ScrollView>
            <View style={styles.mainContainer}>
              <AuthInput
                autoFocus
                inputType="email"
                value={email}
                handleChange={setEmail}
                placeholder="아이디를 입력해주세요"
                keyboardType="email-address"
              />
              <AuthInput
                autoFocus
                inputType="nickname"
                value={nickname}
                handleChange={setNickname}
                placeholder="닉네임을 입력해주세요"
                keyboardType="default"
              />
              <AuthInput
                inputType="password"
                value={password}
                handleChange={setPassword}
                placeholder="비밀번호를 입력해주세요"
                secureTextEntry
              />
              <AuthInput
                inputType="passwordConfirm"
                value={passwordConfirm}
                handleChange={setPasswordConfirm}
                placeholder="비밀번호를 다시 입력해주세요"
                checkValue={password}
                secureTextEntry
              />
              <Pressable onPress={onRegistration} style={styles.loginButton}>
                <MainText>회원가입</MainText>
              </Pressable>
              <Pressable
                style={styles.registerButton}
                onPress={handlePressRegister}>
                <Text style={styles.registerText}>로그인</Text>
              </Pressable>
            </View>
            <Dialog
              style={styles.modal}
              visible={dialogVisibility}
              onRequestClose={closeDialog}>
              <LightText>{dialogMessage}</LightText>
              <View style={styles.buttonRow}>
                {registrationComplete ? (
                  <>
                    <PrimaryButton onPress={handlePressRegister}>
                      <PrimaryButtonText>로그인</PrimaryButtonText>
                    </PrimaryButton>
                    <SecondaryButton onPress={closeDialog}>
                      <SecondaryButtonText>확인</SecondaryButtonText>
                    </SecondaryButton>
                  </>
                ) : (
                  <PrimaryButton onPress={closeDialog}>
                    <PrimaryButtonText>확인</PrimaryButtonText>
                  </PrimaryButton>
                )}
              </View>
            </Dialog>
          </ScrollView>
        </KeyboardAvoidingView>
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
  kbdView: {
    flex: 1,
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
