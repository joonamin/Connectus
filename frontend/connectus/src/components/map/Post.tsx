import {fonts, queryKeys} from '@/constants';
import colors from '@/constants/colors';
import React from 'react';
import {Image, StyleSheet, Text, View} from 'react-native';
import MainText from '@/components/text/MainText';
import {useQuery} from '@tanstack/react-query';
import {getPostDetail} from '@/api/post';
import useAuthStore from '@/store/useAuthStore';
import LightText from '../text/LightText';

export interface PostProps {
  postId: number;
}

export default function Post({postId}: PostProps) {
  const {user} = useAuthStore();

  const {isPending, isError, data} = useQuery({
    queryKey: [queryKeys.GET_POST_DETAIL, postId],
    queryFn: async () => {
      if (typeof user?.userId === 'undefined') {
        throw new Error('사용자 ID가 지정되지 않았습니다');
      }
      return await getPostDetail(postId, user.userId);
    },
  });

  // 방명록 업데이트 시각
  let timestamp = new Date();

  try {
    if (isPending) {
      return (
        <View style={styles.postContainer}>
          <LightText>방명록을 불러오는 중입니다</LightText>
        </View>
      );
    } else if (isError) {
      throw new Error('불러오기 실패');
    }

    timestamp = new Date(data.updatedAt);
  } catch (_error) {
    return (
      <View style={styles.postContainer}>
        <LightText>방명록을 불러오는 데 실패했습니다</LightText>
      </View>
    );
  }

  return (
    <View style={styles.postContainer}>
      <MainText>
        {Intl.DateTimeFormat('ko-KR', {
          dateStyle: 'long',
          timeStyle: 'short',
        }).format(timestamp)}
      </MainText>
      <View style={styles.postImageContainer}>
        {data.imageUrl ? (
          <Image style={styles.postImage} src={data.imageUrl} />
        ) : (
          <MainText>이미지가 없습니다</MainText>
        )}
      </View>
      <MainText>{data.content}</MainText>
    </View>
  );
}

const styles = StyleSheet.create({
  postContainer: {
    padding: 15,
    backgroundColor: colors.buttonBackground,
    borderRadius: 15,
  },
  postImageContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%',
    height: 300,
    borderRadius: 15,
    marginTop: 10,
    marginBottom: 10,
  },
  postImage: {
    width: '100%',
    height: '100%',
    borderRadius: 15,
  },
});
