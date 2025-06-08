import React from "react";
import {
    Box,
    Button,
    TextField,
    Typography,
    Container,
    Paper
} from "@mui/material";
import { useFormik } from "formik";
import * as Yup from "yup";
import dayjs from "dayjs";
import ManagerService from "../../service/ManagerService.js";

const ProjectSchema = Yup.object().shape({
    name: Yup.string().required("Tên dự án không được để trống"),
    description: Yup.string(),
    startDate: Yup.date().required("Ngày bắt đầu là bắt buộc"),
    endDate: Yup.date()
        .nullable()
        .transform((curr, orig) => (orig === "" ? null : curr))
        .min(Yup.ref("startDate"), "Ngày kết thúc phải sau ngày bắt đầu")
});

const ManagerDashboard = () => {
    const today = dayjs().format("YYYY-MM-DD");
    const [loadingState, setLoadingState] = React.useState(false);
    const [messageState, setMessageState] = React.useState({
        content: '',
        color: '',
    });

    const formik = useFormik({
        initialValues: {
            name: '',
            description: '',
            startDate: today,
            endDate: ''
        },
        validationSchema: ProjectSchema,
        onSubmit: async (values, { resetForm }) => {
            setLoadingState(true);
            setMessageState({ content: '', color: '' });
            try {
                const result = await ManagerService.createProject(values);
                console.log(result);
                setMessageState({
                    content: "Tạo dự án thành công!",
                    color: "green",
                });
                resetForm();
            } catch (error) {
                console.log(error);
                setMessageState({
                    content: error?.response?.data?.message || "Lỗi khi tạo dự án",
                    color: "red",
                });
            } finally {
                setLoadingState(false);
            }
        },
    });

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} sx={{ p: 3, mt: 4 }}>
                <Typography variant="h5" gutterBottom>
                    Tạo dự án mới
                </Typography>
                <form onSubmit={formik.handleSubmit}>
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Tên dự án"
                        name="name"
                        value={formik.values.name}
                        onChange={formik.handleChange}
                        error={formik.touched.name && Boolean(formik.errors.name)}
                        helperText={formik.touched.name && formik.errors.name}
                    />
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Mô tả"
                        name="description"
                        value={formik.values.description}
                        onChange={formik.handleChange}
                        multiline
                        rows={4}
                    />
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Ngày bắt đầu"
                        type="date"
                        name="startDate"
                        value={formik.values.startDate}
                        onChange={formik.handleChange}
                        error={formik.touched.startDate && Boolean(formik.errors.startDate)}
                        helperText={formik.touched.startDate && formik.errors.startDate}
                    />
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Ngày kết thúc"
                        type="date"
                        name="endDate"
                        value={formik.values.endDate}
                        onChange={formik.handleChange}
                        error={formik.touched.endDate && Boolean(formik.errors.endDate)}
                        helperText={formik.touched.endDate && formik.errors.endDate}
                    />

                    {messageState.content && (
                        <Typography sx={{ mt: 2 }} color={messageState.color}>
                            {messageState.content}
                        </Typography>
                    )}

                    <Box sx={{ mt: 2 }}>
                        <Button
                            type="submit"
                            variant="contained"
                            color="primary"
                            disabled={loadingState}
                        >
                            {loadingState ? "Đang xử lý..." : "Tạo dự án"}
                        </Button>
                    </Box>
                </form>
            </Paper>
        </Container>
    );
};

export default ManagerDashboard;
