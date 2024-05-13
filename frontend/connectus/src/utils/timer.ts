/**
 * 하단 인디케이터에서 사용할 몇초동안 걸었는지를 string으로 표시해줄 함수
 * @param seconds
 * @returns
 */
function convertSecondsToTime(seconds: number): {
  hours: number;
  minutes: number;
  seconds: number;
} {
  const hours = Math.floor(seconds / 3600);
  const minutes = Math.floor((seconds % 3600) / 60);
  const remainingSeconds = seconds % 60;
  return {hours, minutes, seconds: remainingSeconds};
}

function padSingleDigit(num: number): string {
  return num < 10 ? `0${num}` : `${num}`;
}

/**
 * 모여라 타이머에 넣어줄 date값을위해 두 string형태의 date를 가져와 빼기연산을 진행합니다.
 * @param time1
 * @param time2
 * @returns 시간차이를 초로 계산해서 넘겨줍니다
 */
function getMinuteDifference(time1: string, time2: string): number {
  // 시간 문자열을 Date 객체로 변환
  const date1 = new Date(time1);
  const date2 = new Date(time2);
  console.log('data1 : ', date1);
  console.log('data2 : ', date2);
  const differenceInMs = date2.getTime() - date1.getTime();

  const differenceInSeconds = Math.round(Math.abs(differenceInMs) / 1000);

  return differenceInSeconds;
}

function formatTime(hours: number, minutes: number, seconds: number): string {
  return `${padSingleDigit(hours)}:${padSingleDigit(minutes)}:${padSingleDigit(
    seconds,
  )}`;
}

export {convertSecondsToTime, formatTime, getMinuteDifference};
