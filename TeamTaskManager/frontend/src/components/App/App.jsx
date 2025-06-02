import React from 'react';
import { CssBaseline, ThemeProvider, createTheme } from '@mui/material';
import RegisterForm from '../Authentication/RegisterForm.jsx';
import LoginForm from "../Authentication/LoginForm.jsx";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import UserList from "../List/Users.jsx";

const theme = createTheme();

function App() {
    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <BrowserRouter>
                <Routes>
                    <Route path="/register" element={<RegisterForm/>}/>
                    <Route path="/login" element={<LoginForm/>}/>
                    <Route path="/users" element={<UserList/>}/>
                </Routes>
            </BrowserRouter>
        </ThemeProvider>
    );
}

export default App;
