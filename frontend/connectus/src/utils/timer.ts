function convertSecondsToTime(seconds: number): { hours: number; minutes: number; seconds: number } {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const remainingSeconds = seconds % 60;
    return { hours, minutes, seconds: remainingSeconds };
}

function padSingleDigit(num: number): string {
    return num < 10 ? `0${num}` : `${num}`;
}

function formatTime(hours: number, minutes: number, seconds: number): string {
    return `${padSingleDigit(hours)}:${padSingleDigit(minutes)}:${padSingleDigit(seconds)}`;
}

export {convertSecondsToTime, formatTime}