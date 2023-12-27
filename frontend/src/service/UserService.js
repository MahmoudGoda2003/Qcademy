import axios from "axios";
import globals from "../utils/globals";

const UserService = {
    google: async function(access_token) {
        const result = await axios.post(`${globals.baseURL}/person/google`, access_token, {headers: {"Content-Type": "text/plain"}, withCredentials: true})
        globals.user = {
            firstName: result.data.firstName,
            lastName: result.data.lastName,
            photoLink: result.data.photoLink,
            phone: result.data.phone,
            education: result.data.education,
            dateOfBirth: result.data.dateOfBirth? result.data.dateOfBirth : null,
            role: result.data.role,
        }
        localStorage.setItem("user", JSON.stringify(globals.user));
    },

    login: async function(user) {
        const response = await axios.post(`${globals.baseURL}/person/login`, user, {withCredentials: true});
        globals.user = {
            firstName: response.data.firstName,
            lastName: response.data.lastName,
            photoLink: response.data.photoLink,
            email: response.data.email,
            dateOfBirth: response.data.dateOfBirth? response.data.dateOfBirth : '1-1-1960',
            phone: response.data.phone,
            education: response.data.dateOfBirth,
            role: response.data.role
        }
        localStorage.setItem("user", JSON.stringify(globals.user));
    },

    signUp: async function(email) {
        await axios.post(`${globals.baseURL}/person/signup`, email, {withCredentials: true, headers: {"Content-Type": "text/plain"}});
    },

    confirmCode: async function(user) {
        await axios.post(`${globals.baseURL}/person/signup/validate`, user, {withCredentials: true});
    },

    updateInfo: async function(user) {
        const res = await axios.post(`${globals.baseURL}/person/update`, user, {withCredentials: true});
    },

    getInfo: async function() {
        const response = await axios.get(`${globals.baseURL}/person/getInfo`, {withCredentials: true});
        globals.user.firstName= response.data.firstName;
        globals.user.lastName= response.data.lastName;
        globals.user.photoLink= response.data.photoLink;
        globals.user.email= response.data.email;
        globals.user.dateOfBirth= response.data.dateOfBirth;
        localStorage.setItem("user", JSON.stringify(globals.user));
        return response.data;
    },
};

export default UserService;