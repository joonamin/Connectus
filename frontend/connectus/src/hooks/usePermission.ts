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

/**
 * 유저에게 LOCATION 혹은 PHOTO의 접근 권한을 요청하는 customHook입니다.
 * 페이지에 접근했을때, 권한을 확인하고, 권한이 없다면 바로 요청하게 해줄 수 있습니다
 * @param type
 */
function usePermission(type: PermissionType) {
  useEffect(() => {
    (async () => {
      const isAndroid = Platform.OS === 'android';
      const permissionOS = isAndroid ? androidPermissions : iosPermissions;
      const checked = await check(permissionOS[type]);
      const showPermissionAlert = () => {
        Alert.alert(
          alerts[`${type}_PERMISSION`].TITLE,
          alerts[`${type}_PERMISSION`].DESCRIPTION,
          [
            {
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
