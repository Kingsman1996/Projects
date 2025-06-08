import React from 'react';
import {CssBaseline, ThemeProvider} from '@mui/material';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import RegisterForm from '../Authentication/RegisterForm.jsx';
import LoginForm from "../Authentication/LoginForm.jsx";
import AdminDashboard from "../Dashboard/AdminDashboard.jsx";
import MemberDashBoard from "../Dashboard/Member.jsx";
import ManagerDashboard from "../Dashboard/ManagerDashboard.jsx";
import {theme} from "../../css/theme.js";
import RouterGuard from "./RouterGuard.jsx";
import NotFound from "./NotFound.jsx";
import AccountList from "../List/AccountList.jsx";
import UserInfoList from "../List/UserInfoList.jsx";

function App() {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <BrowserRouter>
                <Routes>
                    <Route path="/login" element={<LoginForm/>}/>
                    <Route path="/register" element={<RegisterForm/>}/>

                    <Route path="/admin" element={
                        <RouterGuard allowedRoles={['ADMIN']}>
                            <AdminDashboard/>
                        </RouterGuard>}>
                    </Route>

                    <Route path="/manager" element={
                        <RouterGuard allowedRoles={['MANAGER']}>
                            <ManagerDashboard/>
                        </RouterGuard>}>
                    </Route>

                    <Route path="/member" element={
                        <RouterGuard allowedRoles={['MEMBER']}>
                            <MemberDashBoard/>
                        </RouterGuard>}>
                    </Route>

                    <Route path="/accounts" element={<AccountList/>}/>
                    <Route path="/accounts/:role" element={<AccountList/>}/>

                    <Route path="/users" element={<UserInfoList/>}/>

                    <Route path="*" element={<NotFound/>}/>
                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    );
}

export default App;
