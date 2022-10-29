import axios from 'axios';

const axiosClient = axios.create({
    baseURL: 'http://localhost:8080',
    'Content-Type': 'application/json',
    'Accept': 'application/json, text/plain',
})

export default axiosClient

