import {useState} from 'react';
import ImagePicker, {Image} from 'react-native-image-crop-picker';

function useImagePicker() {
  const [imageData, setImageData] = useState<Image | null>(null);

  const useGallery = () => {
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
  };

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
