import {Chip, IconButton, Stack, Typography} from '@mui/material';
import * as React from "react";
import CourseDetailsCard from "./CourseDetailsCard";
import Divider from '@mui/material/Divider';
import ModuleList from './ModuleList';
import { useLocation } from 'react-router-dom';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import { useState } from 'react';
import CreateModule from './CreateModule';

export default function EditCourse() {

    const fakeModule = {
        courseId: 1,
        name: "moduleName",
        lectures: ['lectures', 'lectures', 'lectures'],
        assignments: ['assignments'],
        slideSets: ['slideSets'],
        quizzes: ['quizzes']
    }

    const [open, setOpen] = useState(false);
    const [modules, setModules] = useState([fakeModule]);

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
                        key={1}
                        name={course.name}
                        description={course.description}
                        image={course.photoLink}
                        tags={course.tags.slice(0, 5)}
                        rating={course.rating}
                        courseid={course.courseid}
                        teacherName={course.teacherName}
                        role={"teacher"}
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
                    <Stack direction={'row'} alignItems={'center'}>
                        <Typography sx={titleStyle} variant='h4' fontSize={26}>
                            Add Module
                        </Typography>
                        <IconButton sx={{margin: '0 1vh'}} onClick={handleOpen}>
                            <AddCircleIcon color='primary'/>
                        </IconButton>
                    </Stack>
                    {/*TODO: Add real module view*/}
                    <ModuleList
                        modules = {modules}
                    >
                    </ModuleList>
                    <Divider sx={{margin: '3vh'}}/>
                </Stack>
            </Stack>
            <CreateModule open={open} handleClose={handleClose} onCreateModule={createModule} courseId={course.courseid}></CreateModule>
        </>
    );
}
