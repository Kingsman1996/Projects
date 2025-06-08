import createAxios from "./config.js";
import TokenDecoder from "./decodeToken.js";

export async function login(formData) {
    try {
        const token = await createAxios(false).post('/accounts/login', formData);
        localStorage.setItem('token', token);
        return TokenDecoder.getRole();
    } catch (error) {
        if (!error.connected) {
            return {
                content: 'Lỗi mạng hoặc máy chủ không phản hồi.',
                color: 'red',
            };
        }
        if (error.status === 400) {
            return {
                content: error.message,
                color: 'red',
            };
        }
    }
}
