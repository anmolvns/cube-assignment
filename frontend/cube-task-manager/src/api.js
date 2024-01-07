// api.js
import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080/api/v1/tasks', // Replace with your backend URL
  //   headers: { 'Access-Control-Allow-Origin': '*' },
});

export default instance;
