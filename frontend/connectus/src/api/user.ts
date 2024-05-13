import {authAxios} from '@/api/axios';

/**
 * user domain의 URL prefix입니다
 */
const prefix = 'user';

/**
 * 로그인 요청 데이터입니다
 */
export type LoginRequest = {
  /**
   * 사용자 이메일
   */
  email: string;
  /**
   * 사용자의 비밀번호
   */
  password: string;
};

/**
 * 로그인 시 반환되는 데이터입니다
 */
export type LoginResponse = {
  /**
   * Access token
   */
  accessToken: string;
};

/**
 * 회원 가입 요청 데이터입니다
 */
export type RegisterRequest = {
  /**
   * 사용자 이메일
   */
  email: string;
  /**
   * 사용자의 비밀번호
   */
  password: string;
  /**
   * 전화번호
   */
  phoneNumber?: string;
  /**
   * 닉네임
   */
  nickname?: string;
  /**
   * 이름
   */
  name?: string;
  /**
   * 생년월일
   */
  birthday?: string;
};

/**
 * 로그인을 요청합니다
 *
 * @param {LoginRequest} request 요청 데이터
 * @returns {Promise<LoginResponse>} 로그인 후 반환되는 데이터
 */
export async function login(request: LoginRequest) {
  const {data} = await authAxios.post<LoginResponse>(
    `${prefix}/login`,
    request,
  );

  return data;
}

/**
 * 회원 가입을 요청합니다
 *
 * @param {RegisterRequest} request 요청 데이터
 */
export async function register(request: RegisterRequest) {
  await authAxios.post<void>(`${prefix}/register`, request);
}
