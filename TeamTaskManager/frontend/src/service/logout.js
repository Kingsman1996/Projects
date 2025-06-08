export default function logout() {
    localStorage.removeItem('token');
    alert(". Vui lòng đăng nhập lại.");
    window.location.href = '/login';
}