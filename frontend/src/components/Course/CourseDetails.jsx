import {Chip, Stack, Typography} from '@mui/material';
import * as React from "react";
import CourseDetailsCard from "./CourseDetailsCard";
import Divider from '@mui/material/Divider';
import ModuleList from '../Module/ModuleList';
import { useLocation } from 'react-router-dom';
import { useState } from 'react';
import globals from '../../utils/globals';
import axios from 'axios';
import { useEffect } from 'react';

export default function CourseDetails() {

    const location = useLocation();

    const titleStyle = {
        margin: '2vh 0',
    }

    const bodyStyle = {
        margin: '0 0.5vw 2vh',
    }

    const course = location.state.course;

    const [modules, setModules] = useState([]);

    useEffect(() => {
        axios.get(`${globals.baseURL}/student/courseModules`, {params: {courseId: course.courseId} , withCredentials: true})
        .then((response) => {
            setModules(response.data);
        })
        .catch((error) => {
            console.log(error);
        });
    }, []);

    return (
        // overflow:'auto', maxWidth: '100%', padding: '2vh'
        <>
            <Stack direction={'row'} spacing={10}
                   sx={{width: '90%', height: '60%', margin: '2vh 2vw', padding: '2vh 2vw'}}>
                <div style={{position: 'sticky', top: 5}}>
                <Stack sx={{position: 'sticky', top: 5}}>
                    <CourseDetailsCard
                        course={course}
                        role={globals.user.role}
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
                    <Typography sx={titleStyle} variant='h4' fontSize={26}>
                        This course includes these modules
                    </Typography>
                    {modules ? <ModuleList modules = {modules} /> : <></>}
                    <Divider sx={{margin: '3vh'}}/>
                </Stack>
            </Stack>
        </>
    );
}
