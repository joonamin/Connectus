import {authType} from '@/components/text/AuthInput';

function validateEmail(email: string) {
  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    return '아이디는 이메일 형식으로 입력해주세요';
  }
  return '';
}

function validatePassword(password: string) {
  if (!(password.length >= 8 && password.length < 20)) {
    return '비밀번호는 8에서 20자 사이로 입력해 주세요';
  }
  return '';
}

function validatePasswordConfirm(password: string, passwordConfirm: string) {
  if (password !== passwordConfirm) {
    return '비밀번호가 일치하지 않습니다.';
  }
}

function validateInput(type: authType, value: any) {
  const error = {message: ''};
  if (type === 'email') {
    error.message = validateEmail(value);
  } else if (type === 'password') {
    error.message = validatePassword(value);
  } else if (type === 'passwordConfirm') {
    error.message = validatePasswordConfirm(value.value, value.checkValue);
  }
  return error.message;
}

export {validateInput};
