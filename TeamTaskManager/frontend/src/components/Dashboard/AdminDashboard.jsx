import React, {useEffect, useState} from "react";
import {
    Box,
    Paper,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Button,
    CircularProgress,
    Container
} from "@mui/material";
import {useNavigate} from "react-router-dom";
import AdminService from "../../service/AdminService.js";

const AdminDashboard = () => {
    const [accountStatsState, setAccountStatsState] = useState({total: 0});
    const [loadingState, setLoadingState] = useState(true);

    const navigate = useNavigate();

    useEffect(() => {
        const fetchAccounts = async () => {
            try {
                const result = await AdminService.getAccountStats();
                setAccountStatsState(result);
            } catch (error) {
                console.log(error);
            } finally {
                setLoadingState(false);
            }
        };
        fetchAccounts();
    }, []);

    if (loadingState) {
        return (
            <Box sx={{display: "flex", justifyContent: "center", mt: 5}}>
                <CircularProgress/>
            </Box>
        );
    }


    return (
        <Container maxWidth="md" sx={{mt: 5}}>
            <Paper sx={{p: 4}}>
                <Typography variant="h4" gutterBottom>
                    Thống kê tài khoản
                </Typography>
                <Typography variant="h6" sx={{mb: 2}}>
                    Tổng số: {accountStatsState.total}
                </Typography>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Loại tài khoản</TableCell>
                                <TableCell>Số lượng</TableCell>
                                <TableCell></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {Object.entries(accountStatsState)
                                .filter(([key]) => key !== 'total')
                                .map(([key, value]) => (
                                    <TableRow key={key}>
                                        <TableCell>{key.charAt(0).toUpperCase() + key.slice(1)}</TableCell>
                                        <TableCell>{value}</TableCell>
                                        <TableCell align="right">
                                            <Button variant="outlined"
                                                    size="small"
                                                    onClick={() => navigate(`/accounts/${key.toLowerCase()}`)}
                                            >
                                                Xem danh sách
                                            </Button>

                                        </TableCell>
                                    </TableRow>
                                ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Paper>
        </Container>
    );
};

export default AdminDashboard;