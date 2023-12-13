import React, { useEffect, useState } from 'react';
import {Typography } from '@mui/material';
import { Grid} from '@mui/material';
import CourseCard from './CourseCard';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import CreateCourses from './CreateCourse';
import globals from '../utils/globals';


export default function Teacher(props) {
    globals.user.courses = [{
        name: "Data Structures",
        description: "How to structure data",
        image: "https://media.geeksforgeeks.org/wp-content/cdn-uploads/20230706095706/intro-data-structure-%E2%80%93-1.png",
        tags: ['Trees', 'Graphs', 'Arrays'],
        rating: 4,
        duration: 14,
        startDate: '2024-03-02',
        courseid: '23',
        teacherName: 'Ahmed Ayman'
    }, {
        name: "The Way Of C",
        description: "Become superior, think like a computer ;^)",
        image: "https://hypnotherapycenter.co.za/wp-content/uploads/2021/05/Connect-with-Your-Higher-Self-During-Meditation-e1621063603562.jpg",
        tags: ['Pointers', 'Memory leaks', 'File descriptors'],
        rating: 5,
        duration: 12,
        startDate: '2024-01-12',
        courseid: '15',
        teacherName: 'Terry A. Davis'
    }, {
        name: "Data Structures",
        description: "How to structure data",
        image: "https://media.geeksforgeeks.org/wp-content/cdn-uploads/20230706095706/intro-data-structure-%E2%80%93-1.png",
        tags: ['Trees', 'Graphs', 'Arrays'],
        rating: 4,
        duration: 14,
        startDate: '2024-03-02',
        courseid: '23',
        teacherName: 'Ahmed Ayman'
    }, {
        name: "The Way Of C",
        description: "Become superior, think like a computer ;^)",
        image: "https://hypnotherapycenter.co.za/wp-content/uploads/2021/05/Connect-with-Your-Higher-Self-During-Meditation-e1621063603562.jpg",
        tags: ['Pointers', 'Memory leaks', 'File descriptors'],
        rating: 5,
        duration: 12,
        startDate: '2024-01-12',
        courseid: '15',
        teacherName: 'Terry A. Davis'
    }, {
        name: "Algorithms",
        description: "How to algorithm data and structures",
        image: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
        tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
        rating: 2.5,
        duration: 10,
        startDate: '2023-12-10',
        courseid: '12',
        teacherName: 'Michael Elsayed'
    }, {
        name: "Java Programming",
        description: "In case you would nothing but bloatware and unnecessary overhead",
        image: "https://appmaster.io/api/_files/hRaLG2N4DVjRZJQzCpN2zJ/download/",
        tags: ['Classes', 'Interfaces', 'More classes', 'More layers'],
        rating: 0.5,
        duration: 11,
        startDate: '2024-02-09',
        courseid: '19',
        teacherName: 'Joseph Magdy'
    }, {
        name: "Algorithms",
        description: "How to algorithm data and structures",
        image: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
        tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
        rating: 2.5,
        duration: 10,
        startDate: '2023-12-10',
        courseid: '12',
        teacherName: 'Michael Elsayed'
    }]

    const [open, setOpen] = useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const TextStyle = { fontFamily: 'Segoe Ui', fontWeight: 'lighter' };
    
    const TextStyle2 = { color: 'gray' };

    return (
        <>
            {(globals.user.courses === 0) ?
                <>
                    <Typography variant='h3' marginTop='5vh' marginLeft='10vh' sx={TextStyle}>Manage Your Courses ...</Typography>
                    <Typography variant='h6' marginLeft='15vh' sx={TextStyle}>These Are All The Courses You've Created</Typography>
                    <Typography variant='h6' align='center' margin='8vh' sx={TextStyle2}>Let's Learn And Grow Together, Sharing Knowledge To Brighten Our World!</Typography>
                </> :
                <>
                    <Typography variant='h3' marginTop='5vh' marginLeft='10vh' sx={TextStyle}>Manage Your Courses ...</Typography>
                    <Typography variant='h6' marginLeft='15vh' sx={TextStyle}>These Are All The Courses You've Created</Typography>

                    <Grid
                        container
                        direction="row"
                        rowGap={3}
                        columnGap={2}
                        justifyContent="space-around"
                        alignItems="flex-start"
                        padding={3}
                    >
                        {globals.user.courses.map((course, index) => (
                            <CourseCard
                                key={index}
                                name={course.name}
                                description={course.description}
                                image={course.image}
                                tags={course.tags.slice(0, 5)}
                                rating={course.rating}
                                courseid={course.courseid}
                                teacherName={course.teacherName}
                                duration={course.duration}
                                startDate={course.startDate}>
                            </CourseCard>
                        ))}
                    </Grid>

                    <Fab color="secondary" aria-label="add" sx={{ position: 'fixed', bottom: 50, right: 10 }} onClick={handleClickOpen}>
                        <AddIcon />
                    </Fab>
                    <CreateCourses open={open} handleClose={handleClose} />

                </>
            }
        </>
    );
}