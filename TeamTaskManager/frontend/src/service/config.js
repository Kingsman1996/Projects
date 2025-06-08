import axios from 'axios';
import TokenDecoder from "./decodeToken.js";

export default function createAxios(requiredAuth) {
    const instance = axios.create({
        baseURL: 'http://localhost:8080',
    });

    if (requiredAuth) {
        instance.interceptors.request.use(
            config => {
                const token = TokenDecoder.getToken();
                if (token) {
                    config.headers['Authorization'] = `Bearer ${token}`;
                }
                return config;
            },
            error => Promise.reject(error)
        );
    }

    instance.interceptors.response.use(
        response => response.data,
        error => {
            if (!error.response) {
                return Promise.reject({
                    connected: false,
                    message: 'Lỗi mạng hoặc máy chủ không phản hồi.'
                });
            }
            if (error.response.status === 401) {
                return Promise.reject({
                    connected: true,
                    tokenInvalid: true,
                    message: "Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại.",
                })
            }
            if (error.response.status === 403) {
                return Promise.reject({
                    connected: true,
                    forbidden: true,
                    message: "Bạn không có quyền truy cập vào tài nguyên này.",
                })
            }
            return Promise.reject({
                connected: true,
                status: error.response.status,
                message: error.response.data,
            });
        }
    );
    return instance;
}