import Header from './Header';
import Box from '@mui/material/Box';
import {Chip, Stack, Typography} from '@mui/material';
import * as React from "react";
import CourseDetailsCard from "./CourseDetailsCard";
import Divider from '@mui/material/Divider';
import DetailsView from "./DetailsView";
import ModuleList from './ModuleList';
import { useLocation } from 'react-router-dom';

export default function CourseDetails() {

    const location = useLocation();

    const titleStyle = {
        margin: '2vh 0',
    }

    const bodyStyle = {
        margin: '0 0.5vw 2vh',
    }

    const course = location.state.course;

    return (
        // overflow:'auto', maxWidth: '100%', padding: '2vh'
        <>
            <Stack direction={'row'} spacing={10}
                   sx={{width: '90%', height: '60%', margin: '2vh 2vw 2vh', padding: '2vh 2vw'}}>
                
                <div style={{position: 'sticky', top: 5}}>
                <Stack sx={{position: 'sticky', top: 5}}>
                    <CourseDetailsCard
                        key={1}
                        name={course.name}
                        description={course.description}
                        image={course.image}
                        tags={course.tags.slice(0, 5)}
                        rating={course.rating}
                        courseid={course.courseid}
                        teacherName={course.teacherName}
                    >
                    </CourseDetailsCard>
                </Stack>
                </div>
                
                <Stack direction={'column'}
                       sx={{width: '70vw', height: '90%', overflow:'auto'}}
                >
                    <Typography sx={titleStyle} variant='h4' fontSize={26}>
                        Description
                    </Typography>
                    <Typography sx={bodyStyle} variant='body'>
                        {course.description}
                    </Typography>
                    <Divider sx={{margin: '3vh'}}/>
                    <Typography sx={titleStyle} variant='h4' fontSize={26}>
                        Skills you will learn
                    </Typography>
                    <Typography sx={bodyStyle} variant='body'>
                    {
                        course.tags.map((tag) => {
                            return <Chip
                                sx={{
                                    margin: '0 0.5vh 0.5vw',
                                }}
                                key={tag} label={tag}></Chip>
                        })
                    }
                    </Typography>
                    <Divider sx={{margin: '3vh'}}/>
                    {/*TODO: Add real module count*/}
                    <Typography sx={titleStyle} variant='h4' fontSize={26}>
                        There are 13 modules in this course
                    </Typography>
                    {/*TODO: Add real module view*/}
                    <ModuleList></ModuleList>
                    <Divider sx={{margin: '3vh'}}/>
                </Stack>
            </Stack>
        </>
    );
}
