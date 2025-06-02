import {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axiosPublic from "../../js/axiosPublic.js";
import {Container, TextField, Button, Typography, Box} from '@mui/material';
import {jwtDecode} from "jwt-decode";

const LoginForm = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });
    const [serverMessage, setServerMessage] = useState({
        content: '',
        color: ''
    });
    const [loading, setLoading] = useState(false);

    const handleChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            const response = await axiosPublic.post('/login', formData);
            const token = response.data;
            localStorage.setItem('token', token);
            const role = jwtDecode(token).role;
            switch (role) {
                case 'ADMIN':
                    navigate('/admin');
                    break;
                case 'MANAGER':
                    navigate('/manager');
                    break;
                case 'MEMBER':
                    navigate('/member');
                    break;
                default:
                    navigate('/login');
            }
        } catch (error) {
            setServerMessage({
                content: error.response?.data?.message,
                color: 'red',
            });
        }
        setLoading(false);
    };

    return (
        <Container maxWidth="xs">
            <Box sx={{textAlign: 'center', mt: 5, p: 3, boxShadow: 3, borderRadius: 2}}>
                <Typography variant="h4" gutterBottom>Đăng Nhập</Typography>
                <form onSubmit={handleSubmit}>
                    <TextField fullWidth label="Tên đăng nhập" name="username" value={formData.username}
                               onChange={handleChange} margin="normal" variant="outlined"/>
                    <TextField fullWidth label="Mật khẩu" type="password" name="password" value={formData.password}
                               onChange={handleChange} margin="normal" variant="outlined"/>
                    <Button fullWidth type="submit" variant="contained" color="primary" sx={{mt: 2}} disabled={loading}>
                        {loading ? "Đang xử lý..." : "Đăng nhập"}
                    </Button>
                </form>

                {serverMessage.content && (
                    <Typography sx={{mt: 2, color: serverMessage.color}}>
                        {serverMessage.content}
                    </Typography>
                )}

                <Typography sx={{mt: 3}}>
                    Chưa có tài khoản? <Button color="secondary" onClick={() => navigate('/register')}>Đăng ký</Button>
                </Typography>
            </Box>
        </Container>
    );
};

export default LoginForm;
