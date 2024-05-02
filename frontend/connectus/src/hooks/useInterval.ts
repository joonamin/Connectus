import {useEffect, useRef} from 'react';

/**
 * setInterval의 단점을 없애기 위한 Dan형님의 슈퍼파월 훅입니다
 * @param callback delay마다 실행할 함수
 * @param delay 이거모르면 영어공부 다시해야함
 */
export default function useInterval(
  callback: () => void,
  delay: number | null,
) {
  const savedCallback = useRef(callback);

  // Remember the latest callback.
  useEffect(() => {
    savedCallback.current = callback;
  }, [callback]);

  // Set up the interval.
  useEffect(() => {
    function tick() {
      savedCallback.current();
    }
    if (delay !== null) {
      let id = setInterval(tick, delay);
      return () => clearInterval(id);
    }
  }, [delay]);
}
