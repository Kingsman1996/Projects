import createAxios from "./config.js";

export default class RoleService {
    static async getRoles(name = "") {
        try {
            const roleArray = await createAxios(false).get(`/roles${name}`);
            return roleArray.map(item => item.name).filter(name => name !== "ADMIN");
        } catch (error) {
            return error
        }
    }
}
