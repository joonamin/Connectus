function getDateDetail(minOption: number) {
  const now = new Date();
  const utc = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const addMin = 1000 * 60 * minOption;
  const date = new Date(utc + koreaTimeDiff + addMin);

  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const hour = date.getHours();
  const min = date.getMinutes();

  return {year, month, day, hour, min};
}

function padSingleDigit(num: number): string {
  return num < 10 ? `0${num}` : `${num}`;
}

function getSubmitDate(minOption: number) {
  const {year, month, day, hour, min} = getDateDetail(minOption);
  return `${year}-${padSingleDigit(month)}-${padSingleDigit(
    day,
  )} ${padSingleDigit(hour)}:${padSingleDigit(min)}`;
}

/**
 * Date형식을 YYYY-MM-DD 형식으로 반환합니다
 * @param date
 */
function dateToString(date: Date) {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
}

export {getSubmitDate, dateToString};
