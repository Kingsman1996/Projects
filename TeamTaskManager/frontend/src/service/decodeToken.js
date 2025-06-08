import { jwtDecode } from "jwt-decode";

class TokenDecoder {
    static getToken() {
        return localStorage.getItem('token');
    }

    static decodeToken() {
        const token = this.getToken();
        if (!token) return null;
        try {
            return jwtDecode(token);
        } catch (e) {
            console.error("Invalid token:", e);
            return null;
        }
    }

    static getUsername() {
        return this.decodeToken().sub ;
    }

    static getRole() {
        return this.decodeToken().roleList
    }

    static isLoggedIn() {
        return !!this.getToken();
    }
}

export default TokenDecoder;
