import {ScrollView, StyleSheet, View} from 'react-native';
import React from 'react';
import MainContainer from '@/components/containers/MainContainer';
import CustomButton from '@/components/buttons/CustomButton';
import IconItemButton from '@/components/containers/IconItemButton';
import ProfileOverview from '@/components/my/ProfileOverview';
import LightText from '@/components/text/LightText';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {StackScreenProps} from '@react-navigation/stack';
import {MyStackParamList} from '@/navigations/stack/MyStackNavigator';
import useAuthStore from '@/store/useAuthStore';
import {defaultColors} from '@/constants/colors';
import {useQuery} from '@tanstack/react-query';
import {queryKeys} from '@/constants';
import {getUserInfo} from '@/api/user';

export default function MyHomeScreen({
  navigation,
}: StackScreenProps<MyStackParamList>) {
  const gotoAchievements = () => {
    navigation.navigate('MyAchievements');
  };
  const gotoHistory = () => {
    navigation.navigate('MyWalkHistory');
  };

  // 로그아웃 기능 수행
  const {user, invalidate} = useAuthStore();

  // 프로필 정보 가져오기
  const {isPending, isError, data} = useQuery({
    queryKey: [queryKeys.GET_USER_INFO, user?.userId],
    queryFn: async ({queryKey}) => {
      const id = queryKey[1];

      if (typeof id !== 'number') {
        throw new Error('유효한 사용자 ID가 지정되어 있지 않습니다');
      }
      return (await getUserInfo(id)).data;
    },
  });

  return (
    <ScrollView>
      <MainContainer style={styles.maincontainer}>
        {isPending ? (
          <LightText style={styles.statusText}>사용자 정보를 불러오는 중입니다</LightText>
        ) : undefined}
        {isError ? (
          <LightText style={styles.statusText}>사용자 정보를 불러오는 데 실패했습니다</LightText>
        ) : undefined}
        {!isPending && !isError ? (
          <>
            <ProfileOverview userInfo={data} />
            <MainContainer style={styles.listGroup}>
              <IconItemButton
                iconType="MaterialIcons"
                iconName="person"
                text="아바타 변경"
              />
              <IconItemButton
                iconType="Ionicons"
                iconName="trophy"
                text="달성한 업적"
                onPress={gotoAchievements}
              />
              <IconItemButton
                iconType="MaterialIcons"
                iconName="list"
                text="산책 기록"
                onPress={gotoHistory}
              />
              <IconItemButton
                iconType="Ionicons"
                iconName="heart"
                text="좋아요 기록"
              />
            </MainContainer>
          </>
        ) : undefined}
        <MainContainer style={styles.listGroup}>
          <IconItemButton
            onPress={invalidate}
            backgroundColor={defaultColors.errorContainer}
            iconType="MaterialIcons"
            iconName="logout"
            text="로그아웃"
          />
        </MainContainer>
      </MainContainer>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  maincontainer: {
    gap: 30,
  },
  statusText: {
    alignSelf: 'center',
  },
  detailButton: {
    padding: 10,
    alignSelf: 'baseline',
    flexDirection: 'row',
    alignItems: 'center',
    gap: 5,
  },
  listGroup: {
    padding: 0,
    gap: 5,
    alignContent: 'stretch',
  },
  logoutText: {
    color: defaultColors.onErrorContainer,
  },
});
