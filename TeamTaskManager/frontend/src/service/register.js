import createAxios from "./config.js";

export default async function register(formData) {
    try {
       const message = await createAxios(false).post('/accounts/register', formData)
        return {
            content: message,
            color: 'green',
        };
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
        if (error.status === 409) {
            return {
                content: 'Tài khoản đã tồn tại.',
                color: 'red',
            };
        }
    }
}