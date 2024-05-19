import {axiosInstance} from '@/api/axios';
import {Event, EventDetail} from '@/types';
import {dateTimeToString} from '@/utils';
import {LatLng} from 'react-native-maps';

/**
 * user domain의 URL prefix입니다
 */
const prefix = 'event';

/**
 * {@link getOngoingEventList}의 응답 데이터 형식입니다
 */
export type GetOngoingEventListResponse = {
  eventList: Event[];
};

/**
 * 현재 진행중인 이벤트 목록을 요청합니다
 *
 * @returns 진행중인 이벤트 목록
 */
export async function getOngoingEventList() {
  return await axiosInstance.get<GetOngoingEventListResponse>(
    `${prefix}/list/ongoing`,
  );
}

/**
 * {@link getEventDetails}의 응답 데이터 형식입니다
 */
export type GetEventDetailsResponse = EventDetail;

/**
 * 지정한 ID의 이벤트 상세 정보를 조회합니다
 *
 * @param eventId 이벤트 ID
 * @returns 이벤트 정보
 */
export async function getEventDetails(eventId: number) {
  return await axiosInstance.get<GetEventDetailsResponse>(
    `${prefix}/detail/${eventId}`,
  );
}

/**
 * {@link makeEvent}의 요청 데이터 형식입니다
 */
export type MakeEventRequest = {
  /**
   * 이벤트 생성자 ID
   */
  userId: number;
  /**
   * 이벤트 제목
   */
  title: string;
  /**
   * 이벤트 사진 경로
   */
  image: string;
  /**
   * 이벤트 상세 정보
   */
  description: string;
  /**
   * 이벤트 위치 목록
   */
  positions: LatLng[];

  /**
   * 이벤트 시작 시간
   */
  startDatetime: Date;
  /**
   * 이벤트 종료 시간
   */
  endDatetime: Date;

  /**
   * 이벤트 완료 시 보상 포인트
   */
  reward: number;
};

/**
 * 이벤트를 생성합니다
 *
 * @param request 요청 데이터
 */
export async function makeEvent(request: MakeEventRequest) {
  const form = new FormData();

  form.append('userId', request.userId);
  form.append('title', request.title);
  form.append('image', {
    uri: request.image,
    type: 'image/jpeg',
    name: `event-${new Date().getTime()}.jpg`,
  });
  form.append('description', request.description);
  request.positions.forEach((position, index) => {
    form.append(`positions[${index}].latitude`, position.latitude);
    form.append(`positions[${index}].longitude`, position.longitude);
  });
  form.append(
    'startDatetime',
    dateTimeToString(request.startDatetime.toISOString()),
  );
  form.append(
    'endDatetime',
    dateTimeToString(request.endDatetime.toISOString()),
  );
  form.append('reward', request.reward);

  return await axiosInstance.post<void>(prefix, form, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
}
