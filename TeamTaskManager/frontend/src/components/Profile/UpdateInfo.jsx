import React, {useState} from "react";
import {Box, Button, Container, Paper, TextField, Typography} from "@mui/material";
import {useFormik} from "formik";
import * as Yup from "yup";
import axiosPrivate from "../../service/axiosPrivate.js";

const validationSchema = Yup.object({
    name: Yup.string().required("Họ và tên không được để trống"),
    email: Yup.string().email("Email không hợp lệ").required("Email không được để trống"),
    phone: Yup.string()
        .matches(/^[0-9]{10,11}$/, "Số điện thoại không hợp lệ")
        .required("Số điện thoại không được để trống"),
});

const UpdateInfo = ({initialValues = {name: "", email: "", phone: ""}}) => {
    const [message, setMessage] = useState({
        content: "",
        color: "",
    });
    const formik = useFormik({
        initialValues,
        validationSchema,
        onSubmit: async (values, {setSubmitting}) => {
            try {
                const response = await axiosPrivate.put("/user-info", values);
                setMessage({
                    content: response.data,
                    color: "green",
                });
            } catch (error) {
                setMessage({
                    content: error.response.data.error,
                    color: "red",
                });
            } finally {
                setSubmitting(false);
            }
        },
    });

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} sx={{p: 4, mt: 5, borderRadius: 2}}>
                <Typography variant="h5" gutterBottom>
                    Cập nhật thông tin cá nhân
                </Typography>
                <Box component="form" onSubmit={formik.handleSubmit}>
                    <TextField
                        fullWidth
                        label="Họ và tên"
                        name="name"
                        value={formik.values.name}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        margin="normal"
                        error={formik.touched.name && Boolean(formik.errors.name)}
                        helperText={formik.touched.name && formik.errors.name}
                    />
                    <TextField
                        fullWidth
                        label="Email"
                        name="email"
                        value={formik.values.email}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        margin="normal"
                        error={formik.touched.email && Boolean(formik.errors.email)}
                        helperText={formik.touched.email && formik.errors.email}
                    />
                    <TextField
                        fullWidth
                        label="Số điện thoại"
                        name="phone"
                        value={formik.values.phone}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                        margin="normal"
                        error={formik.touched.phone && Boolean(formik.errors.phone)}
                        helperText={formik.touched.phone && formik.errors.phone}
                    />
                    <Button
                        fullWidth
                        type="submit"
                        variant="contained"
                        color="primary"
                        sx={{mt: 2}}
                        disabled={formik.isSubmitting}
                    >
                        Cập nhật
                    </Button>
                </Box>
                {message.content && (
                    <Typography sx={{mt: 2, color: message.color}}>
                        {message.content}
                    </Typography>
                )}
            </Paper>
        </Container>
    );
};

export default UpdateInfo;