import {Image} from 'react-native-image-crop-picker';
import {LatLng} from 'react-native-maps';
import {create} from 'zustand';

type PostType = {
  content: string;
  image: Image | null;
  postLocation: LatLng;
};

interface PostStore {
  posts: PostType[];
  setAddPost: (post: PostType) => void;
  setDeletePost: (index: number) => void;
}

/**
 * 전역적으로 관리할 현재 작성된 방명록들을 저장할 store를 정의합니다.
 */
const usePostStore = create<PostStore>(set => ({
  posts: [
    {
      content: '오늘은 진주님이 카페라떼를 남기셨어요',
      image: require('@/assets/jinjoo-namgim.jpeg'),
      postLocation: {latitude: 14, longitude: 42},
    },
    {
      content: '오늘은 진주님이 카페라떼를 두번 남기셨어요',
      image: require('@/assets/jinjoo-namgim.jpeg'),
      postLocation: {latitude: 14, longitude: 42},
    },
  ],
  setAddPost: (post: PostType) => {
    set(state => ({...state, post: post}));
  },
  setDeletePost: (index: number) => {
    set(state => {
      console.log('delete test');
      const newPosts = [...state.posts];
      newPosts.splice(index, 1);
      return {...state, posts: newPosts};
    });
  },
}));

export default usePostStore;
