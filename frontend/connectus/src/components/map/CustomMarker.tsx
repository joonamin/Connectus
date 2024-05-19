import {axiosInstance} from '@/api/axios';
import {getUserInfo} from '@/api/user';
import {queryKeys} from '@/constants';
import useLookUpPost from '@/store/useLookUpPost';
import {domainType} from '@/types';
import {useQuery} from '@tanstack/react-query';
import React, {useEffect, useState} from 'react';
import {Image, StyleSheet, View} from 'react-native';
import {LatLng, MyMapMarkerProps, Marker} from 'react-native-maps';
import {setGestureState} from 'react-native-reanimated';

interface CustomMarkerProps extends MyMapMarkerProps {
  coordinate?: LatLng;
  type: domainType;
  markerId: number;
  lookUpFeed: number | null;
}

/**
 * 맵에서 마커들을 표시할 커스텀 마커 컴포넌트 입니다.
 * 아직 백엔드에서 어떤식으로 데이터들을 확실하게 보내줄지 모르지만
 * 타입의 경우에는 number의 형태로 보낸다고 들었습니다.
 * @param {number}type  1 : 이벤트 좌표, 2: 피드 이미지 좌표, 3: 모여라 좌표
 * @param {LatLng|undefined}coordinate 해당 마커의 좌표
 */

export default function CustomMarker({
  coordinate,
  type,
  markerId,
  lookUpFeed,
  ...props
}: CustomMarkerProps) {
  const [imageLoaded, setImageLoaded] = useState<boolean>(false);

  const onImageLoad = () => {
    setImageLoaded(true);
  };
  const [imageUrl, setImageUrl] = useState<string>();

  useEffect(() => {
    const getUserImg = async () => {
      const {data} = await axiosInstance.get(`/user/info/${markerId}`);
      setImageUrl(data.imageUrl);
    };
    if (type === 'USER') {
      getUserImg();
    }
  }, []);

  const markerView = (
    <View style={styles.container}>
      {/* 타입별로 이미지를 다르게합니다 */}
      {type === 'EVENT' && (
        <Image
          style={styles.image}
          source={require('@/assets/markers/eventMarker.png')}
          resizeMode="cover"
          onLoad={onImageLoad}
        />
      )}
      {type === 'POST' && (
        <Image
          style={styles.image}
          source={
            markerId === lookUpFeed
              ? require('@/assets/markers/goldfeedmarker.png')
              : require('@/assets/markers/feedMarker.png')
          }
          resizeMode="cover"
          onLoad={onImageLoad}
        />
      )}
      {type === 'GATHER' && (
        <Image
          style={styles.image}
          source={require('@/assets/markers/gatherMarker.png')}
          resizeMode="cover"
          onLoad={onImageLoad}
        />
      )}
      {type === 'USER' && (
        <Image
          style={styles.image}
          source={{uri: imageUrl}}
          resizeMode="cover"
          onLoad={onImageLoad}
        />
      )}
    </View>
  );

  return coordinate ? (
    <Marker coordinate={coordinate} tracksViewChanges={!imageLoaded} {...props}>
      {markerView}
    </Marker>
  ) : (
    markerView
  );
}

const styles = StyleSheet.create({
  container: {
    width: 50,
    aspectRatio: '414/500',
    borderRadius: 50,
    justifyContent: 'center',
    alignItems: 'center',
  },
  image: {
    width: '100%',
    height: '100%',
  },
});
