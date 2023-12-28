import axios from "axios";
import globals from "../utils/globals";

const CourseService = {
    getCreatedCourses: async function() {
        let result;
        await axios.get(`${globals.baseURL}/teacher/createdCourses`, {withCredentials: true})
        .then((response) => {
            result = response.data;
        })
        .catch((error) => {
            result = [];
            console.log(error);
        });
        return result;
    },

    getEnrolledCourses: async function() {
        let result;
        await axios.get(`${globals.baseURL}/student/enrolledCourses`, {withCredentials: true})
        .then((response) => {
            let courses = response.data;
            courses.forEach(element => {
                element.enrolled = true;
            });
            result = courses;
        })
        .catch((error) => {
            result = [];
            console.log(error);
        });

        return result;
    },

    getRecommendedCourses: async function() {
        let result;
        await axios.get(`${globals.baseURL}/student/recommendedCourses`, {withCredentials: true})
        .then((response) => {
            let courses = response.data;
            courses.forEach(element => {
                element.enrolled = false;
            });
            result = courses;
        })
        .catch((error) => {
            result = [];
            console.log(error);
        });

        return result;
    }

};

export default CourseService;