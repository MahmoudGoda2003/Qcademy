import React, { useEffect, useState } from 'react';
import {Typography } from '@mui/material';
import { Grid} from '@mui/material';
import CourseCard from './CourseCard';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import CreateCourses from './CreateCourse';
import globals from '../utils/globals';
import { Box } from '@mui/system';
import axios from 'axios';


export default function Teacher(props) {

    const [open, setOpen] = useState(false);
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        axios.get(`${globals.baseURL}/teacher/createdCourses`, {withCredentials: true})
        .then((response) => {
            setCourses(response.data);
        })
        .catch((error) => {
            console.log(error);
        });

    }, []);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const createCourse = (course) => {
        setCourses(courses => [...courses, course]);
    }

    const TextStyle = {fontFamily: 'sans-serif'}

    const TextStyle2 = { color: 'gray' };

    return (
        <Box maxWidth='100%' width={'85%'} margin={'auto'}>
            {(courses === 0) ?
                <>
                    <Typography variant='h4' marginTop='7vh' sx={TextStyle}>Manage Your Courses ...</Typography>
                    <Typography variant='h7' sx={TextStyle}>These Are All The Courses You've Created</Typography>
                    <Typography variant='h7' align='center' margin='8vh' sx={TextStyle2}>Let's Learn And Grow Together, Sharing Knowledge To Brighten Our World!</Typography>
                </> :
                <>
                    <Typography variant='h4' marginTop='7vh' sx={TextStyle}>Manage Your Courses ...</Typography>
                    <Typography variant='h7' sx={TextStyle}>These Are All The Courses You've Created</Typography>

                    <Grid
                        container
                        direction="row"
                        rowGap={3}
                        columnGap={2}
                        justifyContent="space-around"
                        alignItems="flex-start"
                        margin={'2vh auto'}
                        padding={'2vh 2vw'}
                    >
                        {courses.map((course, index) => (
                            <CourseCard
                                course={course}>
                            </CourseCard>
                        ))}
                    </Grid>

                    <Fab color="secondary" aria-label="add" sx={{ position: 'fixed', bottom: 50, right: 10 }} onClick={handleClickOpen}>
                        <AddIcon />
                    </Fab>
                    <CreateCourses open={open} handleClose={handleClose} onCreateCourse={createCourse} />

                </>
            }
        </Box>
    );
}