import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { CardActionArea, CardMedia, Chip, Rating } from '@mui/material';
import { useNavigate } from "react-router-dom";
import globals from "../utils/globals";

const DEFAULT_IMAGE = ""
const RATING_PRECISION = 0.5
const DEFAULT_RATING = 0

export default function CourseCard({course}) {
    const navigate = useNavigate();

    const navTo = (courseid) => {
        console.log(course);
        // Used for updating course information
        /*globals.course = {
            id: courseid,
            title: name,
            description: description,
            imageUrl: image,
            tags: tags,
            duration: duration,
            startDate: startDate
        }
        localStorage.setItem("course", JSON.stringify(globals.course));*/
        navigate(`/course/${courseid}`, {state: { course:course }})
    }
    
    return (
        <Card sx={{minWidth:'45vh', maxWidth:'45vh'}}>
            <CardActionArea courseid={course.courseid} onClick={() => {navTo(course.courseid)}}>
                <CardMedia
                    component="img"
                    image={(course.image===undefined)? DEFAULT_IMAGE: course.image}
                    alt="personal image"
                    sx={{maxWidth:'45vh', maxHeight:'25vh', minHeight:'25Vh'}}
                />
                <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {course.name}
                </Typography>
                <Rating name="half-rating" defaultValue={(course.rating===undefined)? DEFAULT_RATING: course.rating} precision={RATING_PRECISION} readOnly/>
                <Typography variant="body2" color="text.secondary">
                    {course.description}
                </Typography>
                {
                    course.tags.map((tag) => {
                        return <Chip 
                        sx={{
                            marginRight: '1%',
                            marginTop: '2%'
                        }} 
                        key={tag} label={tag}></Chip>
                    })
                }

                
                
                <Typography sx={{
                    marginTop: '2vh'
                }} 
                color="text.secondary">
                    Created By: {course.teacherName}
                </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    );
}
