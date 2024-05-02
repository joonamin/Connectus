import {useEffect} from 'react';
import {Alert, Linking, Platform} from 'react-native';
import {
  PERMISSIONS,
  check,
  RESULTS,
  request,
  Permission,
} from 'react-native-permissions';

type PermissionType = 'LOCATION' | 'PHOTO';

// 키가 PermissionType 이면서
type PermissionOS = {
  [key in PermissionType]: Permission;
};

const androidPermissions: PermissionOS = {
  LOCATION: PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION,
  PHOTO: PERMISSIONS.ANDROID.READ_MEDIA_IMAGES,
};

const iosPermissions: PermissionOS = {
  LOCATION: PERMISSIONS.IOS.LOCATION_WHEN_IN_USE,
  PHOTO: PERMISSIONS.IOS.PHOTO_LIBRARY,
};

const alerts = {
  LOCATION_PERMISSION: {
    TITLE: '위치 권한 허용이 필요합니다',
    DESCRIPTION: '설정 화면에서 위치 권한을 허용해주세요',
  },
  PHOTO_PERMISSION: {
    TITLE: '사진 접근 권한이 필요합니다',
    DESCRIPTION: '설정 화면에서 사진 권한을 허용해주세요',
  },
} as const;

function usePermission(type: PermissionType) {
  useEffect(() => {
    (async () => {
      const isAndroid = Platform.OS === 'android';
      const permissionOS = isAndroid ? androidPermissions : iosPermissions;
      const checked = await check(permissionOS[type]);
      console.log('실행확인좀');
      const showPermissionAlert = () => {
        // Alert.alert('제목', '설명', [옵션]);
        Alert.alert(
          alerts[`${type}_PERMISSION`].TITLE,
          alerts[`${type}_PERMISSION`].DESCRIPTION,
          [
            {
              // 직접 유저에게 설정창을 열어주는 과정
              text: '설정하기',
              onPress: () => Linking.openSettings(),
            },
            {
              text: '취소',
              style: 'cancel',
            },
          ],
        );
      };

      switch (checked) {
        case RESULTS.DENIED:
          if (isAndroid) {
            showPermissionAlert();
            return;
          }
          // DENIED상태일때는 request를 사용해 요청을 다시 보내도록 함
          await request(permissionOS[type]);
          break;
        case RESULTS.BLOCKED:
        case RESULTS.LIMITED:
          showPermissionAlert();
          break;
        default:
          break;
      }
    })();
  }, []);
}

export default usePermission;
