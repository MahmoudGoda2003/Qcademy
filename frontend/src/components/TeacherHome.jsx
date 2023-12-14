import React, { useState } from 'react';
import {Typography } from '@mui/material';
import { Grid} from '@mui/material';
import CourseCard from './CourseCard';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import CreateCourses from './CreateCourse';
import globals from '../utils/globals';
import { Box } from '@mui/system';


export default function Teacher(props) {
    globals.user.courses = [{
        name: "Data Structures",
        description: "How to structure data",
        photoLink: "https://media.geeksforgeeks.org/wp-content/cdn-uploads/20230706095706/intro-data-structure-%E2%80%93-1.png",
        tags: ['Trees', 'Graphs', 'Arrays'],
        rating: 4,
        courseid: '23',
        teacherName: 'Ahmed Ayman', 
        modules: [{
            courseId: '23',
            name: 'Algorithms',
            lectures: [{lectureName: "Lecture 1", lectureUrl: 'b9eMGE7QtTk'}],
            assignments: [{assignmentName: "Assignment 1", assignmentUrl: 'https://learnenglish.britishcouncil.org/sites/podcasts/files/LearnEnglish-Listening-A2-Instructions-for-an-assignment.pdf'}],
            slideSets: [],
            quizzes: ['https://learnenglishteens.britishcouncil.org/sites/teens/files/friendship_quiz_-_answers.pdf'],
            moduleid: '12',
        },
        {
            courseId: '23',
            name: 'Algorithms',
            lectures: [{lectureName: "Lecture 1", lectureUrl: 'b9eMGE7QtTk'}],
            assignments: [{assignmentName: "Assignment 1", assignmentUrl: 'https://learnenglish.britishcouncil.org/sites/podcasts/files/LearnEnglish-Listening-A2-Instructions-for-an-assignment.pdf'}],
            slideSets: [],
            quizzes: ['https://learnenglishteens.britishcouncil.org/sites/teens/files/friendship_quiz_-_answers.pdf'],
            moduleid: '12',
        },
        {
            courseId: '23',
            name: 'Algorithms',
            lectures: ['b9eMGE7QtTk'],
            assignments: ['https://learnenglish.britishcouncil.org/sites/podcasts/files/LearnEnglish-Listening-A2-Instructions-for-an-assignment.pdf'],
            slideSets: [],
            quizzes: ['https://learnenglishteens.britishcouncil.org/sites/teens/files/friendship_quiz_-_answers.pdf'],
            moduleid: '12',
        },],
    }, {
        name: "The Way Of C",
        description: "Become superior, think like a computer ;^)",
        photoLink: "https://hypnotherapycenter.co.za/wp-content/uploads/2021/05/Connect-with-Your-Higher-Self-During-Meditation-e1621063603562.jpg",
        tags: ['Pointers', 'Memory leaks', 'File descriptors'],
        rating: 5,
        courseid: '15',
        teacherName: 'Terry A. Davis'
    }, {
        name: "Algorithms",
        description: "How to algorithm data and structures",
        photoLink: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
        tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
        rating: 2.5,
        courseid: '12',
        teacherName: 'Michael Elsayed'
    }, {
        name: "Java Programming",
        description: "In case you would nothing but bloatware and unnecessary overhead",
        photoLink: "https://appmaster.io/api/_files/hRaLG2N4DVjRZJQzCpN2zJ/download/",
        tags: ['Classes', 'Interfaces', 'More classes', 'More layers'],
        rating: 0.5,
        courseid: '19',
        teacherName: 'Joseph Magdy'
    }, {
        name: "Algorithms",
        description: "How to algorithm data and structures",
        photoLink: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
        tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
        rating: 2.5,
        courseid: '12',
        teacherName: 'Michael Elsayed',
    }]

    const [open, setOpen] = useState(false);
    const [courses, setCourses] = useState(globals.user.courses);

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