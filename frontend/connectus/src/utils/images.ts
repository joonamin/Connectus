import type {Image} from 'react-native-image-crop-picker';

function getFormDataImages(key: string = 'image', image: Image) {
  const formData = new FormData();
  const file = {
    uri: image.path,
    type: 'multipart/form-data',
    name: image.path.split('/').pop(),
  };
  console.log(file);
  formData.append(key, file);

  return formData;
}

export {getFormDataImages};
