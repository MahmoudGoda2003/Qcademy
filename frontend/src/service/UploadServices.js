import axios from "axios";

const UploadServices = {
    uploadImage: async function (imageFile) {
        const formData = new FormData();
        formData.append("file", imageFile);
        formData.append("upload_preset", "xdmym8xv");
        formData.append("api_key", "593319395186373");

        const response = await axios.post(
            "https://api.cloudinary.com/v1_1/dlcy5giof/image/upload",
            formData
        )
        return response.data.secure_url;
    }
}

export default UploadServices;