import {getFormDataImages} from '@/utils/images';
import {useState} from 'react';
import ImagePicker, {Image} from 'react-native-image-crop-picker';

interface ImageUri {
  id?: number;
  uri: string;
}

interface UseImagePickerProps {
  initialImages: ImageUri;
}

function useImagePicker() {
  const [imageData, setImageData] = useState<Image | null>(null);
  const handleChange = () => {
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
      const formData = getFormDataImages('image', img);
      setImageData(img);
    });
  };

  return {
    imageData,
    handleChange,
  };
}

export default useImagePicker;
