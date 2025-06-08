import React, {useState, useEffect} from "react";
import {DataGrid} from "@mui/x-data-grid";
import {Box, CircularProgress, Typography} from "@mui/material";
import AdminService from "../../service/AdminService.js";

const UserInfoList = () => {
    const [usersState, setUsersState] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadData = async () => {
            try {
                const response = await AdminService.getUserInfoList();
                setUsersState(response);
            } catch (error) {
                console.error("Error fetching user info:", error);
            } finally {
                setLoading(false);
            }
        };
        loadData();
    }, []);

    if (loading) {
        return (
            <Box sx={{ display: "flex", justifyContent: "center", mt: 5 }}>
                <CircularProgress />
            </Box>
        );
    }

    const columns = [
        {field: "id", headerName: "ID", width: 100},
        {field: "username", headerName: "Tài khoản", width: 100},
        {field: "name", headerName: "Họ và tên", width: 200},
        {field: "email", headerName: "Email", width: 250},
        {field: "phone", headerName: "Số điện thoại", width: 150},
    ];

    return (
        <Box sx={{height: 500, width: "100%", padding: 2}}>
            <Typography variant="h4" gutterBottom>
                Danh sách thành viên
            </Typography>
            <DataGrid
                rows={usersState}
                columns={columns}
                loading={loading}
                pageSize={5}
                rowsPerPageOptions={[5, 10, 20]}
                disableSelectionOnClick
            />
        </Box>
    );
};

export default UserInfoList;

