import createAxios from "./config.js";

export default class AdminService {
    static async getAccountStats(name = "") {
        try {
            return await createAxios(true).get(`/accounts/stats${name}`)
        } catch (error) {
            return error;
        }
    }

    static async getAccountList(name = "") {
        try {
            return await createAxios(true).get(`/accounts${name}`)
        } catch (error) {
            return error;
        }
    }

    static async getUserInfoList() {
        try {
            return await createAxios(true).get('/users')
        } catch (error) {
            return error;
        }
    }

}
