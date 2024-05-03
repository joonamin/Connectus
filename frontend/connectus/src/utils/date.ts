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

export {getSubmitDate};
