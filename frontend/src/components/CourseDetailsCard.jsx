import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import {Button, CardActionArea, CardMedia, Chip, Rating} from '@mui/material';
import { useNavigate } from 'react-router';


const DEFAULT_IMAGE = ""
const RATING_PRECISION = 0.5
const DEFAULT_RATING = 0

const gridElement = {
    margin: '1vh auto',
    width: '100%'
}

export default function CourseDetailsCard({course, role}) {

    const navigate = useNavigate();

    const handleRedirect = (courseid) => {
        navigate(`/course/learn/${courseid}`, {state: { modules:course.modules }})
    }

    return (

        <Card sx={{minWidth:'45vh', maxWidth:'45vh', margin: '2vh auto'}}>
            <CardActionArea courseid={course.courseid} onClick={() => {handleRedirect(course.courseid)}}>
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
                    <Rating name="half-rating" defaultValue={(course.rating===undefined)? DEFAULT_RATING: course.rating} precision={RATING_PRECISION} readOnly/>
                    <Typography 
                        sx={gridElement}
                        color="text.secondary">
                        By: {course.teacherName}
                    </Typography>
                    {role == "student" ? <Button variant="contained" size="large" sx={gridElement} type="submit">Enroll Now</Button> :<></>}
                </CardContent>
            </CardActionArea>
        </Card>
    );
}
