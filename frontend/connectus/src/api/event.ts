import {axiosInstance} from '@/api/axios';
import {Event, EventDetail} from '@/types';

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
    `${prefix}/detail/${eventId}`
  );
}
