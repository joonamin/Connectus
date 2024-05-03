import {useState} from 'react';
import ImagePicker, {Image} from 'react-native-image-crop-picker';

function useImagePicker() {
  // return 해줄 선택된 이미지 데이터
  const [imageData, setImageData] = useState<Image | null>(null);
  // 갤러리를 오픈해서 사진을 선택하는 함수
  // modal에서 선택스크린으로 옮겨가면서 선택스크린이 바로 사라지는 이슈때문에 setTimeout을 넣었습니다
  const useGallery = () => {
    setTimeout(() => {
      ImagePicker.openPicker({
        mediaType: 'photo',
        includeBase64: true,
        maxFiles: 1,
        cropperChooseText: '완료',
        cropperCancelText: '취소',
      }).then(async image => {
        const img = await ImagePicker.openCropper({
          mediaType: 'photo',
          path: image.path,
          width: 1000,
          height: 1000,
        });
        setImageData(img);
      });
    }, 700);
  };

  // 카메라를 사용해서 사진을 전달해주는 함수(iphone에서는 에뮬레이터로 작동 불가)
  const useCamera = () => {
    ImagePicker.openCamera({
      mediaType: 'photo',
      includeBase64: true,
      maxFiles: 1,
      cropperChooseText: '완료',
      cropperCancelText: '취소',
    }).then(async image => {
      const img = await ImagePicker.openCropper({
        mediaType: 'photo',
        path: image.path,
        width: 1000,
        height: 1000,
      });
      setImageData(img);
    });
  };

  return {
    imageData,
    useGallery,
    useCamera,
  };
}

export default useImagePicker;
