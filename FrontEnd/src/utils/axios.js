import axios from 'axios'
import qs from 'qs'

axios.defaults.baseURL = "/apis";

axios.interceptors.request.use((request) => {
    var token = JSON.parse(localStorage.getItem('TOKEN'));
    if (token) {
        // token = "TOKEN " + token;
        request.headers['Authorization'] = token;
    }
    if (request.method === 'GET') {
        // request.data = qs.stringify(request.data);
    }
    return request;
}, (error) => {
    // console.log('error');
    return Promise.reject(error);
});

export default axios