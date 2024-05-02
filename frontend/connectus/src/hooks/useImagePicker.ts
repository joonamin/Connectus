import ImagePickr from 'react-native-image-crop-picker';

function useImagePicker() {
  const handleChange = () => {
    ImagePickr.openPicker({
      mediaType: 'photo',
      multiple: true,
      includeBase64: true,
      maxFiles: 1,
      cropperChooseText: '완료',
      cropperCancelText: '취소',
    }).then(images => {
      console.log(images);
    });
  };

  return {
    handleChange,
  };
}

export default useImagePicker;
