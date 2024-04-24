import {useEffect, useRef, useState} from 'react';
import {AppState} from 'react-native';

/**
 * 유저가백그라운드에서 돌아왔을 때, 실행할 코드입니다.
 * 상태로 놓고 useEffect안에 넣어서 dependency로 사용하면 됩니다
 */
function useAppState() {
  const appState = useRef(AppState.currentState);
  const [appStateVisible, setAppStateVisible] = useState(appState.current);
  const [isComback, setIsComback] = useState(false);

  useEffect(() => {
    const subscription = AppState.addEventListener('change', nextAppState => {
      console.log('change state');
      if (
        // 앱의 상태가 inactive 혹은 백그라운드에서 돌아오면
        appState.current.match(/inactive|background/) &&
        nextAppState === 'active'
      ) {
        setIsComback(true);
      }
      if (appState.current.match(/active/) && nextAppState === 'background') {
        setIsComback(false);
      }
      appState.current = nextAppState;
      setAppStateVisible(appState.current);
    });

    return () => {
      subscription.remove();
    };
  }, []);

  return {isComback, appStateVisible};
}

export default useAppState;
