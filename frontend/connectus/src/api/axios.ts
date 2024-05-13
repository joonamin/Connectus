import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://3.38.245.137:8000',
  withCredentials: true,
});

export {axiosInstance};
