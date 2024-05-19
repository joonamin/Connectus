import {Image, Pressable, StyleSheet, Text, View} from 'react-native';
import React, {useEffect} from 'react';
import MainText from '../text/MainText';
import colors from '@/constants/colors';
import {fonts} from '@/constants';
import usePostStore from '@/store/usePostStore';
import Ionicons from 'react-native-vector-icons/Ionicons';
export default function RecordedPost() {
  const {posts, setDeletePost} = usePostStore();

  useEffect(() => {
    console.log(posts);
  }, [posts]);
  return (
    <View style={styles.mainContainer}>
      <Text style={styles.headerText}>작성된 방명록</Text>
      <View style={styles.postListContainer}>
        {posts.length === 0 && (
          <View style={styles.postContainer}>
            <Text style={styles.headerText}>
              작성된 방명록이 존재하지 않습니다.
            </Text>
          </View>
        )}
        {posts.map((post, index) => {
          return (
            <View style={styles.postContainer} key={index}>
              <Pressable
                style={styles.colseButton}
                onPress={() => {
                  setDeletePost(index);
                }}>
                <Ionicons name="close" size={32} color={colors.white} />
              </Pressable>
              {/* <MainText>2024년 5월 6일 16:17</MainText> */}
              {/* <Text style={styles.locationText}>여기에 주소가 나옵니다</Text> */}
              <View style={styles.postImageContainer}>
                {post.image === null && <MainText>이미지가 없습니다</MainText>}
                {post.image !== null && (
                  <Image
                    style={styles.postImage}
                    source={{uri: post.image.path}}
                  />
                )}
              </View>
              <MainText>{post.content}</MainText>
            </View>
          );
        })}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  mainContainer: {
    width: '100%',
    gap: 10,
  },
  headerText: {
    fontFamily: fonts.medium,
    fontSize: 20,
    color: colors.white,
  },
  postListContainer: {
    gap: 5,
  },
  locationText: {
    color: colors.white,
    fontFamily: fonts.light,
    fontSize: 18,
  },
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
  colseButton: {
    zIndex: 1,
    position: 'absolute',
    top: 10,
    right: 10,
  },
});
