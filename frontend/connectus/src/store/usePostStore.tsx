import {Image} from 'react-native-image-crop-picker';
import {LatLng} from 'react-native-maps';
import {create} from 'zustand';

type PostType = {
  authorId?: number;
  content: string;
  image: Image | null;
  formImage?: any;
  postLocation: LatLng;
};

interface PostStore {
  posts: PostType[];
  setAddPost: (post: PostType) => void;
  setDeletePost: (index: number) => void;
  clearPost: () => void;
}

/**
 * 전역적으로 관리할 현재 작성된 방명록들을 저장할 store를 정의합니다.
 */
const usePostStore = create<PostStore>(set => ({
  posts: [],
  setAddPost: (post: PostType) => {
    set(state => {
      console.log('feed create test', post);
      return {...state, posts: [...state.posts, post]};
    });
  },
  setDeletePost: (index: number) => {
    set(state => {
      console.log('delete test');
      const newPosts = [...state.posts];
      newPosts.splice(index, 1);
      return {...state, posts: newPosts};
    });
  },
  clearPost: () => {
    return {posts: []};
  },
}));

export default usePostStore;
