'use client'

import React, {useState} from 'react';
import {Container, TextField, Button, Typography, Box, CircularProgress} from '@mui/material';
import {useFormik} from "formik";

const LoginForm = () => {
    const [loadingState, setLoadingState] = useState(false);
    const [messageState, setMessageState] = useState({
        content: '',
        color: '',
    });

    const formik = useFormik({
        initialValues: {
            username: '',
            password: '',
        },
        onSubmit: async (values) => {
            setLoadingState(true);
            try {
                // const roleList = await login(values);
            } catch (error) {
                setMessageState({
                    content: error.content,
                    color: error.color,
                });
            } finally {
                setLoadingState(false);
            }
        },
    });

    if (loadingState) {
        return (
            <CircularProgress/>
        )
    }

    return (
        <Container maxWidth="xs">
            <Box sx={{textAlign: 'center', mt: 5, p: 3, boxShadow: 3, borderRadius: 2}}>
                <Typography variant="h4" gutterBottom>Đăng Nhập</Typography>
                <form onSubmit={formik.handleSubmit}>
                    <TextField fullWidth label="Tên đăng nhập" name="username" value={formik.values.username}
                               onChange={formik.handleChange} margin="normal" variant="outlined"/>
                    <TextField fullWidth label="Mật khẩu" type="password" name="password" value={formik.values.password}
                               onChange={formik.handleChange} margin="normal" variant="outlined"/>
                    <Button fullWidth type="submit" variant="contained" color="primary" sx={{mt: 2}}>
                        Đăng nhập
                    </Button>
                </form>

                {messageState.content && (
                    <Typography sx={{mt: 2, color: messageState.color}}>
                        {messageState.content}
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
