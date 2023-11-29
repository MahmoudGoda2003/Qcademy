import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { CardActionArea, CardMedia, Chip, Rating } from '@mui/material';
import { useNavigate } from "react-router-dom";

const DEFAULT_IMAGE = ""
const RATING_PRECISION = 0.5
const DEFAULT_RATING = 0


export default function CourseCard({name, description, image, tags, rating, courseid, teacherName}) {
    const navigate = useNavigate();

    const navTo = (courseid) => {
        console.log(courseid);
        navigate(`/course/${courseid}`)
    }

    
    return (
        <Card sx={{minWidth:'45vh', maxWidth:'45vh'}}>
            <CardActionArea courseid={courseid} onClick={() => {navTo(courseid)}}>
                <CardMedia
                    component="img"
                    image={(image===undefined)? DEFAULT_IMAGE: image}
                    alt="personal image"
                    sx={{maxWidth:'45vh', maxHeight:'25vh', minHeight:'25Vh'}}
                />
                <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {name}
                </Typography>
                <Rating name="half-rating" defaultValue={(rating===undefined)? DEFAULT_RATING: rating} precision={RATING_PRECISION} readOnly/>
                <Typography variant="body2" color="text.secondary">
                    {description}
                </Typography>
                {
                    tags.map((tag) => {
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
                    Created By: {teacherName}
                </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    );
}
