import queryClient from '@/api/queryClient';
import {updateAvatar} from '@/api/user';
import CustomButton from '@/components/buttons/CustomButton';
import CustomTextButton from '@/components/buttons/CustomTextButton';
import MainContainer from '@/components/containers/MainContainer';
import {queryKeys} from '@/constants';
import colors, {defaultColors} from '@/constants/colors';
import {MyStackParamList} from '@/navigations/stack/MyStackNavigator';
import useAuthStore from '@/store/useAuthStore';
import {StackScreenProps} from '@react-navigation/stack';
import {useMutation} from '@tanstack/react-query';
import React, {useState} from 'react';
import {Image, SafeAreaView, StyleSheet, View} from 'react-native';

/**
 * 아바타 변경 화면을 생성합니다
 *
 * @returns MyChangeAvatarScreen
 */
export default function MyChangeAvatarScreen({
  navigation,
  route,
}: StackScreenProps<MyStackParamList, 'MyChangeAvatar'>) {
  // 현재 사용자 정보 및 아바타 불러오기
  const {user} = useAuthStore();
  const currentImage = route.params.currentAvatar;

  // 작업 진행 중일 시 버튼 비활성화
  const [selected, setSelected] = useState<string>(currentImage);
  const [progress, setProgress] = useState<boolean>(false);

  // 선택 가능한 이미지
  // TODO: URL 삭제 및 조금 더 좋은 방법으로 아바타 불러오기
  const prefix =
    'https://e106-connectus.s3.ap-northeast-2.amazonaws.com/avatar';
  const list: string[] = [
    `${prefix}/character_1.png`,
    `${prefix}/character_2.png`,
    `${prefix}/character_3.png`,
    `${prefix}/character_4.png`,
    `${prefix}/character_5.png`,
    `${prefix}/character_6.png`,
  ];

  const mutation = useMutation({
    mutationFn: async () => {
      setProgress(true);

      if (typeof user?.userId === 'undefined') {
        throw new Error('사용자 ID가 정의되지 않았습니다');
      }

      await updateAvatar(user.userId, {imageUrl: selected});
      queryClient.invalidateQueries({
        queryKey: [queryKeys.GET_USER_INFO, user.userId],
      });
    },
    onSuccess: () => {
      navigation.goBack();
    },
    onSettled: () => {
      setProgress(false);
    },
  });

  return (
    <SafeAreaView>
      <View style={styles.pageContainer}>
        <View style={styles.page}>
          <MainContainer style={styles.currentAvatarContainer}>
            <Image
              style={styles.currentAvatar}
              src={currentImage}
              defaultSource={defaultImage}
            />
          </MainContainer>
          <MainContainer style={styles.avatarList}>
            {list.map(url => (
              <Avatar
                key={url}
                url={url}
                selected={selected === url}
                setSelected={setSelected}
              />
            ))}
          </MainContainer>
        </View>
        <MainContainer style={styles.actionContainer}>
          <CustomTextButton
            backgroundColor={
              !selected || progress || currentImage === selected
                ? colors.buttonBackground
                : defaultColors.primaryContainer
            }
            label="아바타 변경"
            disabled={!selected || progress || currentImage === selected}
            onPress={() => {
              mutation.mutate();
            }}
          />
        </MainContainer>
      </View>
    </SafeAreaView>
  );
}

/**
 * 내부 아바타 요소를 생성할 시 사용되는 prop 형식입니다
 */
interface AvatarProps {
  /**
   * 아바타의 이미지 URL
   */
  url: string;
  /**
   * 해당 아바타의 선택 여부
   */
  selected: boolean;
  /**
   * 선택될 시 호출되는 callback
   *
   * @param url 선택된 URL
   */
  setSelected: (url: string) => void;
}

/**
 * 내부 아바타를 표현하기 위한 요소입니다
 *
 * @returns Avatar
 */
const Avatar = ({url, selected, setSelected}: AvatarProps) => (
  <CustomButton
    containerStyle={styles.avatarContainerContainer}
    style={styles.avatarContainer}
    backgroundColor={
      selected ? defaultColors.primaryContainer : colors.buttonBackground
    }
    onPress={() => {
      setSelected(url);
    }}>
    <Image style={styles.avatar} src={url} defaultSource={defaultImage} />
  </CustomButton>
);

// 프로필 이미지 불러오기에 실패할 시 기본 이미지 사용
const defaultImage = require('@/assets/default-profile.png');

const styles = StyleSheet.create({
  pageContainer: {
    width: '100%',
    height: '100%',
  },
  page: {
    flexGrow: 1,
  },
  actionContainer: {
    flexShrink: 1,
    flexGrow: 0,
  },
  currentAvatarContainer: {
    paddingVertical: 30,
    alignItems: 'center',
  },
  currentAvatar: {
    aspectRatio: '1 / 1',
    height: 96,
    borderRadius: 180,
    backgroundColor: colors.buttonBackground,
    alignSelf: 'center',
    borderColor: colors.white,
    borderWidth: 2,
    zIndex: 1,
    resizeMode: 'contain',
  },
  avatarList: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    flexWrap: 'wrap',
    rowGap: 15,
  },
  avatarContainerContainer: {
    borderRadius: 15,
    aspectRatio: '1 / 1',
    overflow: 'hidden',
    flex: 1,
    flexBasis: '30%',
    flexGrow: 0,
    flexShrink: 0,
  },
  avatarContainer: {
    padding: 15,
    justifyContent: 'center',
  },
  avatar: {
    aspectRatio: '1 / 1',
    resizeMode: 'contain',
  },
});
