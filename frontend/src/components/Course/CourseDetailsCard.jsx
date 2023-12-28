import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import {Button, CardMedia, Rating} from '@mui/material';
import { useNavigate } from 'react-router';
import axios from 'axios';
import globals from '../../utils/globals';
import CourseService from '../../service/CourseService';
import { useState } from 'react';


const DEFAULT_IMAGE = ""
const RATING_PRECISION = 0.5
const DEFAULT_RATING = 0

const gridElement = {
    margin: '1vh auto',
    width: '100%'
}

export default function CourseDetailsCard({course, role}) {

    const [courseRating, setCourseRating] = useState();

    const navigate = useNavigate();

    const enrollCourse = async () => {
        await axios.post(`${globals.baseURL}/student/enrollCourse`, null, {params: {courseId: Number(course.courseId)}, withCredentials: true})
        course.enrolled =true;
        navigate("/home");
    }

    const handleNavigate = () => {
        if (role === "STUDENT") navigate(`/course/learn/${course.courseId}/`, {state: { course:course }})
        if (role === "TEACHER") navigate(`/course/${course.courseId}/manageModules`, {state: { course:course }})
    }

    const removeCourse = async () => {
        await CourseService.deleteCourse(course.courseId);
        navigate('/home');
    }

    const rateCourse = async (newValue) => {
        console.log(newValue);
        await CourseService.rateCourse(course.courseId, newValue);
    }

    return (

        <Card sx={{minWidth:'45vh', maxWidth:'45vh', margin: '2vh auto'}}>
            <CardMedia
                component="img"
                image={(course.photoLink===undefined)? DEFAULT_IMAGE: course.photoLink}
                alt="personal image"
                sx={{maxWidth:'45vh', maxHeight:'25vh', minHeight:'25Vh'}}
            />
            <CardContent>
                <Typography gutterBottom variant="h3" component="div">
                    {course.name}
                </Typography>
                {role === "TEACHER" ?
                    <Rating name="rating" defaultValue={(course.rating===undefined)? DEFAULT_RATING: course.rating} precision={RATING_PRECISION} readOnly/>
                :
                    <Rating
                        name="rating"
                        defaultValue={(course.rating===undefined)? DEFAULT_RATING: course.rating}
                        precision={RATING_PRECISION}
                        value={courseRating}
                        onChange={(event, newValue) => {rateCourse(newValue)}}
                    />
                }
                <Typography 
                    sx={gridElement}
                    color="text.secondary">
                    By: {course.teacherName}
                </Typography>
                {role === "STUDENT" && course.enrolled ? <Button variant="contained" size="large" sx={gridElement} onClick={handleNavigate} >Continue Learning</Button> :
                role === "STUDENT" ? <Button variant="contained" size="large" sx={gridElement} onClick={enrollCourse} >Enroll Now</Button> : <></>}
                {role === "TEACHER" &&
                    <>
                        <Button variant="contained" size="large" sx={gridElement} onClick={handleNavigate} >Manage Course</Button>
                        <Button variant="contained" size="large" style={{backgroundColor: 'red'}} sx={gridElement} onClick={removeCourse} >Delete this course</Button>
                    </>
                }
            </CardContent>
        </Card>
    );
}
