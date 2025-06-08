import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {
    Box,
    Button,
    Paper,
    TextField,
    Typography,
    RadioGroup,
    FormControlLabel,
    Radio,
    FormLabel,
    FormControl,
    Container,
    CircularProgress
} from "@mui/material";
import * as Yup from 'yup';
import {useFormik} from 'formik';
import register from "../../service/register.js";
import RoleService from "../../service/RoleService.js";

const validationSchema = Yup.object({
    username: Yup.string()
        .min(4, 'Tài khoản phải từ 4 đến 20 ký tự')
        .max(20, 'Tài khoản phải từ 4 đến 20 ký tự'),
    password: Yup.string()
        .min(4, 'Mật khẩu phải từ 4 ký tự'),
    role: Yup.string()
        .required('Vui lòng chọn vai trò'),

});

const RegisterForm = () => {
    const navigate = useNavigate();
    const [rolesState, setRolesState] = useState([]);
    const [loadingState, setLoadingState] = useState(true);
    const [messageState, setMessageState] = useState({
        content: '',
        color: '',
    });


    const formik = useFormik({
        initialValues: {
            username: '',
            password: '',
            role: 'MEMBER',
        },
        validationSchema,
        validateOnChange: true,
        onSubmit: async (values) => {
            try {
                const result = await register(values);
                setMessageState({
                    content: result.content,
                    color: result.color
                });
            } catch (error) {
                setMessageState({
                    content: error.content,
                    color: error.color
                });
            }
        },
    });

    useEffect(() => {
        const fetchRoles = async () => {
            try {
                const roleArray = await RoleService.getRoles("");
                setRolesState(roleArray);
            } catch (error) {
                console.error("Lỗi khi lấy roles:", error);
            } finally {
                setLoadingState(false);
            }
        };
        fetchRoles();
    }, []);

    if (loadingState) {
        return (
            <CircularProgress/>
        )
    }

    return (
        <Container maxWidth="xs">
            <Paper elevation={3} sx={{p: 4, textAlign: 'center', mt: 5, boxShadow: 3, borderRadius: 2}}>
                <Typography variant="h4" gutterBottom>
                    Đăng Ký
                </Typography>
                <Box component="form" onSubmit={formik.handleSubmit}>
                    <TextField
                        fullWidth
                        label="Tài khoản"
                        name="username"
                        value={formik.values.username}
                        onChange={(e) => {
                            formik.handleChange(e);
                            formik.setFieldTouched('username', true, false);
                        }}
                        margin="normal"
                        error={formik.touched.username && Boolean(formik.errors.username)}
                        helperText={formik.touched.username && formik.errors.username}
                    />
                    <TextField
                        fullWidth
                        type="password"
                        label="Mật khẩu"
                        name="password"
                        value={formik.values.password}
                        onChange={(e) => {
                            formik.handleChange(e);
                            formik.setFieldTouched('password', true, false);
                        }}
                        margin="normal"
                        error={formik.touched.password && Boolean(formik.errors.password)}
                        helperText={formik.touched.password && formik.errors.password}
                    />
                    <FormControl fullWidth margin="normal">
                        <FormLabel>Vai trò</FormLabel>
                        <RadioGroup
                            name="role"
                            value={formik.values.role}
                            onChange={formik.handleChange}
                            row
                        >
                            {rolesState.map(role => (
                                <FormControlLabel
                                    key={role}
                                    value={role}
                                    control={<Radio/>}
                                    label={role.charAt(0) + role.slice(1).toLowerCase()}
                                />
                            ))}
                        </RadioGroup>
                    </FormControl>

                    <Button fullWidth type="submit" variant="contained" color="primary" sx={{mt: 2}}>
                        Đăng ký
                    </Button>
                </Box>
                {Array.isArray(messageState.content) ? (
                    messageState.content.map((msg, index) => (
                        <Typography key={index} sx={{mt: 2, color: messageState.color}}>
                            {msg}
                        </Typography>
                    ))
                ) : (
                    <Typography sx={{mt: 2, color: messageState.color}}>
                        {messageState.content}
                    </Typography>
                )}
                <Typography sx={{mt: 3}}>
                    Đã có tài khoản?{' '}
                    <Button color="secondary" onClick={() => navigate('/login')}>
                        Đăng nhập
                    </Button>
                </Typography>
            </Paper>
        </Container>
    );
};

export default RegisterForm;