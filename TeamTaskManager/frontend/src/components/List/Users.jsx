import React, {useState, useEffect} from "react";
import {DataGrid} from "@mui/x-data-grid";
import {Box, Typography} from "@mui/material";
import axiosPrivate from "../../js/axiosPrivate.js";

const UserList = () => {
    const [userListState, setUserListState] = useState([]);
    const [loading, setLoading] = useState(true);
    const [forbidden, setForbidden] = useState(false);

    useEffect(() => {
        const loadUserData = async () => {
            try {
                const response = await axiosPrivate.get('/users');
                console.log(response.data);
                setUserListState(response.data);
            } catch (error) {
                if (error && error.forbidden) {
                    setForbidden(true);
                } else {
                    console.log(error);
                }
            } finally {
                setLoading(false);
            }
        };
        loadUserData();
    }, []);

    if (forbidden) {
        return (
            <Box sx={{height: 300, width: "100%", display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
                <Typography variant="h5" color="error">
                    Bạn không có quyền truy cập tài nguyên này.
                </Typography>
            </Box>
        );
    }

    const columns = [
        {field: "id", headerName: "ID", width: 100},
        {field: "name", headerName: "Họ và tên", width: 200},
        {field: "role", headerName: "Vai trò", width: 200},
        {field: "project", headerName: "Dự án", width: 300},
        {field: "email", headerName: "Email", width: 250},
        {field: "phone", headerName: "Số điện thoại", width: 150},
    ];

    return (
        <Box sx={{height: 500, width: "100%", padding: 2}}>
            <Typography variant="h4" gutterBottom>
                Danh sách thành viên
            </Typography>
            <DataGrid
                rows={userListState}
                columns={columns}
                loading={loading}
                pageSize={5}
                rowsPerPageOptions={[5, 10, 20]}
                disableSelectionOnClick
            />
        </Box>
    );
};

export default UserList;

