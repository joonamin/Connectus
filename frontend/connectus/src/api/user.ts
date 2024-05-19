import {authAxios, axiosInstance} from '@/api/axios';
import {Achievement, UserInfo} from '@/types';

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

/**
 * 사용자 정보 조회 응답 데이터입니다
 */
export type GetUserInfoResponse = UserInfo;

/**
 * 지정한 ID를 가진 사용자의 정보를 불러옵니다
 *
 * @param userId 사용자 ID
 * @returns 사용자 정보
 */
export async function getUserInfo(userId: number) {
  return await axiosInstance.get<GetUserInfoResponse>(
    `${prefix}/info/${userId}`,
  );
}

/**
 * 달성 업적 조회의 응답 데이터입니다
 */
export type GetUserCompletedAchievementResponse = {
  /**
   * 완료한 업적 목록
   */
  completedAchievement: Achievement[];
  /**
   * 완료하지 못한 업적 목록
   */
  uncompletedAchievement: Achievement[];
};

/**
 * 지정한 ID를 가진 사용자의 완료한 업적 목록을 불러옵니다
 *
 * @param userId 사용자 ID
 * @returns 완료한 업적
 */
export async function getUserCompletedAchievement(userId: number) {
  return await axiosInstance.get<GetUserCompletedAchievementResponse>(
    `${prefix}/completed-achievement/${userId}`,
  );
}

/**
 * 아바타 변경 시 요청 데이터입니다
 */
export type UpdateAvatarRequest = {
  /**
   * 변경할 아바타의 이미지 URL
   */
  imageUrl: string;
};

/**
 * 아바타 변경을 요청합니다
 *
 * @param userId 아바타를 변경할 사용자의 ID
 * @param request 요청 데이터
 */
export async function updateAvatar(
  userId: number,
  request: UpdateAvatarRequest,
) {
  return await axiosInstance.post<string>(
    `${prefix}/${userId}/update-avatar`,
    request,
  );
}
