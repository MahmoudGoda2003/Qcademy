import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import {Button, CardMedia, Chip, Rating} from '@mui/material';


const DEFAULT_IMAGE = ""
const RATING_PRECISION = 0.5
const DEFAULT_RATING = 0

const gridElement = {
    margin: '1vh auto',
    width: '100%'
}

export default function CourseDetailsCard({name, image, rating, teacherName}) {

    return (

        <Card sx={{minWidth:'45vh', maxWidth:'45vh', margin: '2vh auto'}}>

                <CardMedia
                    component="img"
                    image={(image===undefined)? DEFAULT_IMAGE: image}
                    alt="personal image"
                    sx={{maxWidth:'45vh', maxHeight:'25vh', minHeight:'25Vh'}}
                />
                <CardContent>
                    <Typography gutterBottom variant="h3" component="div">
                        {name}
                    </Typography>
                    <Rating name="half-rating" defaultValue={(rating===undefined)? DEFAULT_RATING: rating} precision={RATING_PRECISION} readOnly/>
                    <Typography 
                        sx={gridElement}
                        color="text.secondary">
                        By: {teacherName}
                    </Typography>
                    <Button variant="contained" size="large" sx={gridElement} type="submit">Enroll Now</Button>
                </CardContent>
        </Card>
    );
}