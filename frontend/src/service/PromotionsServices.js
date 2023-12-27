import axios from "axios";
import globals from "../utils/globals";

const PromotionServices = {
    getPromotionRequests: async function () {
        const result = await axios.get(`${globals.baseURL}/admin/promotionRequests`, {withCredentials: true})
        const parsedResult = result.data.map((result) => ({
            userId: result.userId,
            personName: result.userName,
            requestedRole: result.requestedRole.toLowerCase(),
            personImage: result.userImage
        }))
        return parsedResult;
    },

    changeRole: async function (userId, status) {
        const response = await axios.post(`${globals.baseURL}/admin/changeRole`, {userId:userId, status:status}, {withCredentials: true});
        return response.data;
    },

    requestPromotion: async function () {
        await axios.post(`${globals.baseURL}/${globals.user.role.toLowerCase()}/requestPromotion`, null, {withCredentials: true});
    }
}

export default PromotionServices;