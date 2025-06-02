import axios from 'axios';

const axiosPrivate = axios.create({
    baseURL: 'http://localhost:8080',
});

axiosPrivate.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

axiosPrivate.interceptors.response.use(
    response => response,
    error => {
        if (error.response) {
            if (error.response.status === 401) {
                localStorage.removeItem('token');
                window.alert('Token không hợp lệ hoặc đã hết hạn. Vui lòng đăng nhập lại.');
                window.location.href = '/login';
            } else if (error.response.status === 403) {
                return Promise.reject({ forbidden: true });
            }
        }
        return Promise.reject(error);
    }
);

export default axiosPrivate;
