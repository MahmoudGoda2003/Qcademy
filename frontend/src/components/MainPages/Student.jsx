import { useEffect, useState } from 'react';
import CoursesList from '../Course/CoursesList';
import { Typography, Box } from '@mui/material';
import axios from 'axios';
import globals from '../../utils/globals';


export default function Student(props) {

    const [enrolledCourses, setEnrolledCourses] = useState([]);
    const [recommendedCourses, setRecommendedCourses] = useState([]);
    
    useEffect(() => {
        axios.get(`${globals.baseURL}/student/enrolledCourses`, {withCredentials: true})
        .then((response) => {
            let courses = response.data;
            courses.forEach(element => {
                element.enrolled = true;
            });
            setEnrolledCourses(courses);
        })
        .catch((error) => {
            console.log(error);
        });

        axios.get(`${globals.baseURL}/student/recommendedCourses`, {withCredentials: true})
        .then((response) => {
            let courses = response.data;
            courses.forEach(element => {
                element.enrolled = false;
            });
            setRecommendedCourses(courses);
        })
        .catch((error) => {
            console.log(error);
        });
        

    }, []);

    const TextStyle = {fontFamily: 'sans-serif'}
    const TextStyle2 = { color:'gray'};
    return (
        <Box maxWidth='100%' width={'85%'} margin={'auto'}>
            {(enrolledCourses.length === 0)?
                <>
                    <Typography variant='h4' marginTop='7vh' sx={TextStyle}>Pick Up Where You Left Off</Typography>
                    <Typography variant='h7' sx={TextStyle}>These Are All The Courses You've Enrolled In</Typography>
                    <Typography variant='h7' justifyContent={'center'} display={'flex'} margin='8vh' sx={TextStyle2}>You Haven't Enrolled In Any Courses Yet ...</Typography>
                </>:
                <>
                    <Typography variant='h4'  marginTop='7vh' sx={TextStyle}>Pick Up Where You Left Off</Typography>
                    <Typography variant='h7' sx={TextStyle}>These Are All The Courses You've Enrolled In</Typography>
                    <CoursesList courses={enrolledCourses}></CoursesList>
                </>
                
            }
            {(recommendedCourses.length === 0)?
                <>
                    <Typography variant='h4' marginTop='7vh' sx={TextStyle}>Recommended Courses</Typography>
                    <Typography variant='h7' sx={TextStyle}>We Think You'll Like These Suggestions</Typography>
                    <Typography variant='h7' justifyContent={'center'} display={'flex'} margin='8vh' sx={TextStyle2}>There Are No Courses Yet ...</Typography>
                </>:
                <>
                    <Typography variant='h4' marginTop='7vh' sx={TextStyle}>Recommended Courses</Typography>
                    <Typography variant='h7' sx={TextStyle}>We Think You'll Like These Suggestions</Typography>
                    <CoursesList courses={recommendedCourses}></CoursesList>    
                </>
                
            }
        </Box>
    );
}