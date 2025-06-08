import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import {
    Container,
    Paper,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    CircularProgress,
    Box
} from '@mui/material';
import AdminService from "../../service/AdminService.js";

function AccountList() {
    const [listState, setlistState] = useState([]);
    const [loading, setLoading] = useState(true);
    const { role } = useParams();

    useEffect(() => {
        const loadAccounts = async () => {
            try {
                const url = role ? `/${role}` : '';
                const response = await AdminService.getAccountList(url);
                setlistState(response);
            } catch (error) {
                console.error("Error fetching accounts:", error);
            } finally {
                setLoading(false);
            }
        };
        loadAccounts();
    }, [role]);

    if (loading) {
        return (
            <Box sx={{ display: "flex", justifyContent: "center", mt: 5 }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Container maxWidth="md" sx={{ mt: 5 }}>
            <Paper sx={{ p: 3 }}>
                <Typography variant="h4" gutterBottom>
                    Danh sách tài khoản: <strong>{role}</strong>
                </Typography>
                {listState.length === 0 ? (
                    <Typography color="textSecondary">Không tìm thấy tài khoản nào.</Typography>
                ) : (
                    <TableContainer component={Paper}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>ID</TableCell>
                                    <TableCell>Username</TableCell>
                                    <TableCell>Hành động</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {listState.map((account) => (
                                    <TableRow key={account.id}>
                                        <TableCell>{account.id}</TableCell>
                                        <TableCell>{account.username}</TableCell>
                                        <TableCell>
                                            <Typography variant="body2" color="textSecondary">
                                                ???
                                            </Typography>
                                        </TableCell>
                                    </TableRow>
                                ))}

                            </TableBody>
                        </Table>
                    </TableContainer>
                )}
            </Paper>
        </Container>
    );
}

export default AccountList;
