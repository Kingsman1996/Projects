import React from "react";
import { Navigate, useLocation } from "react-router-dom";
import TokenDecoder from "../../service/decodeToken.js";

const RouterGuard = ({ allowedRoles, children }) => {
    const location = useLocation();

    if (!TokenDecoder.isLoggedIn()) {
        return <Navigate to="/login" state={{ from: location }} replace />;
    }

    const userRoles = TokenDecoder.getRole();

    const hasAccess = userRoles.some(role => allowedRoles.includes(role));

    if (!hasAccess) {
        return <Navigate to="/unauthorized" replace />;
    }
    return children;
};

export default RouterGuard;
