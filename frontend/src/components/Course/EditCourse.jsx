import {Chip, IconButton, Stack, Typography} from '@mui/material';
import * as React from "react";
import CourseDetailsCard from "./CourseDetailsCard";
import Divider from '@mui/material/Divider';
import ModuleList from '../Module/ModuleList';
import { useLocation } from 'react-router-dom';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import { useState } from 'react';
import CreateModule from '../Module/CreateModule';
import globals from '../../utils/globals';
import { useEffect } from 'react';
import axios from 'axios';

export default function EditCourse() {

    const [open, setOpen] = useState(false);

    const handleOpen = () => {
        setOpen(true);
    }

    const handleClose = () => {
        setOpen(false);
    }

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
        axios.get(`${globals.baseURL}/teacher/courseModules`, {params: {courseId: course.courseId} , withCredentials: true})
        .then((response) => {
            setModules(response.data);
        })
        .catch((error) => {
            console.log(error);
        });
    }, []);


    const createModule = (module) => {
        setModules(modules => [...modules, module]);
    }

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
                    <Stack direction={'row'} alignItems={'center'}>
                        <Typography sx={titleStyle} variant='h4' fontSize={26}>
                            Add Module
                        </Typography>
                        <IconButton sx={{margin: '0 1vh'}} onClick={handleOpen}>
                            <AddCircleIcon color='primary'/>
                        </IconButton>
                    </Stack>
                    {modules ? <ModuleList modules = {modules} /> : <></>}
                    <Divider sx={{margin: '3vh'}}/>
                </Stack>
            </Stack>
            <CreateModule open={open} handleClose={handleClose} onCreateModule={createModule} courseId={course.courseId}></CreateModule>
        </>
    );
}
