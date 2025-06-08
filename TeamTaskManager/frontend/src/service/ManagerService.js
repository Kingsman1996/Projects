import createAxios from "./config.js";

export default class ManagerService {
    static async createProject(projectData) {
        try {
            return await createAxios(true).post('/projects', projectData);
        } catch (error) {
            return error;
        }
    }
}