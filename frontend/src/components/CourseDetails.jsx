import Header from './Header';
import Box from '@mui/material/Box';
import {Stack} from '@mui/material';
import * as React from "react";
import CourseDetailsCard from "./CourseDetailsCard";
import Divider from '@mui/material/Divider';
import DetailsView from "./DetailsView";

export default function CourseDetails() {
    const enrolledCourses = [{
        name: "Data Structures",
        description: "How to structure data",
        image: "https://media.geeksforgeeks.org/wp-content/cdn-uploads/20230706095706/intro-data-structure-%E2%80%93-1.png",
        tags: ['Trees', 'Graphs', 'Arrays'],
        rating: 4,
        courseid: '23',
        teacherName: 'Ahmed Ayman'
    },{
        name: "The Way Of C",
        description: "Become superior, think like a computer ;^)",
        image: "https://hypnotherapycenter.co.za/wp-content/uploads/2021/05/Connect-with-Your-Higher-Self-During-Meditation-e1621063603562.jpg",
        tags: ['Pointers', 'Memory leaks', 'File descriptors'],
        rating: 5,
        courseid: '15',
        teacherName: 'Terry A. Davis'
    },{
        name: "Data Structures",
        description: "How to structure data",
        image: "https://media.geeksforgeeks.org/wp-content/cdn-uploads/20230706095706/intro-data-structure-%E2%80%93-1.png",
        tags: ['Trees', 'Graphs', 'Arrays'],
        rating: 4,
        courseid: '23',
        teacherName: 'Ahmed Ayman'
    },{
        name: "The Way Of C",
        description: "Become superior, think like a computer ;^)",
        image: "https://hypnotherapycenter.co.za/wp-content/uploads/2021/05/Connect-with-Your-Higher-Self-During-Meditation-e1621063603562.jpg",
        tags: ['Pointers', 'Memory leaks', 'File descriptors'],
        rating: 5,
        courseid: '15',
        teacherName: 'Terry A. Davis'
    }]

    const recommendedCourses = [{
        name: "Algorithms",
        description: "How to algorithm data and structures",
        image: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
        tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
        rating: 2.5,
        courseid: '12',
        teacherName: 'Michael Elsayed'
    },{
        name: "Java Programming",
        description: "In case you would nothing but bloatware and unnecessary overhead",
        image: "https://appmaster.io/api/_files/hRaLG2N4DVjRZJQzCpN2zJ/download/",
        tags: ['Classes', 'Interfaces', 'More classes', 'More layers'],
        rating: 0.5,
        courseid: '19',
        teacherName: 'Joseph Magdy'
    },{
        name: "Algorithms",
        description: "How to algorithm data and structures",
        image: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
        tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
        rating: 2.5,
        courseid: '12',
        teacherName: 'Michael Elsayed'
    },{
        name: "Java Programming",
        description: "In case you would nothing but bloatware and unnecessary overhead",
        image: "https://appmaster.io/api/_files/hRaLG2N4DVjRZJQzCpN2zJ/download/",
        tags: ['Classes', 'Interfaces', 'More classes', 'More layers'],
        rating: 0.5,
        courseid: '19',
        teacherName: 'Joseph Magdy'
    }]


    const gridStyle = {
        margin: '2vh',
        padding: '2vh',
        justifyContent: 'center',
        alignItems: 'center',
        minWidth: '25vw'
    }

    const paperStyle = {
        height: 'fit',
        display: 'flex',
        maxWidth: '45vh',
        flexDirection: 'column',
        margin: '2vh auto',
        padding: '2vh',
        minWidth: '25vw'
    }

    const innerGridStyle = {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center'
    }

    const gridElement = {
        background: 'red',
        margin: '8px',
        width: '100%'
    }


    let course = enrolledCourses[0];
    return (
        // overflow:'auto', maxWidth: '100%', padding: '2vh'
        <>
            <Stack direction={'row'} spacing={5}  alignItems={'left'}
                   sx={{width: '100%', height: '20 vw', margin: '2vh', maxWidth: '100%', padding: '2vh'}}>
                <Stack direction={'column'} spacing={4}  alignItems={'left'}
                       sx={{width: '70%', height: '45vw', margin: '2vh',overflow:'auto', maxWidth: '100%', padding: '2vh'}}
                       divider={<Divider orientation="horizontal" flexItem />}
                >
                    {/*example data to view only*/}
                    <DetailsView
                        descriptionName={"Details"}
                        content={"We sent you an email with a 6 digit\n" +
                    "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                    "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                    "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                    "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"} //course.details
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"About US"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"Meet Teacher assistance"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"Details"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"Details"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"Details"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"Details"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"Details"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"Details"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>
                    <DetailsView
                        descriptionName={"Details"}
                        content={"We sent you an email with a 6 digit\n" +
                            "asfdlgsdkfgkjhgasdfjksakfgdsfsadfsadf\n" +
                            "dsfsadfsadfsadfsadfsdfsadfsadfsdfsdfs\n" +
                            "fdsfsdfsadfsdfsadfsadfsdfsdfsdfsdfsdf\n" +
                            "sdacodeasdlsadfgkgsakjdfgkjsahgfdkjhd"}
                    >
                    </DetailsView>



                </Stack>
                <Box sx={{ minWidth: '30%', maxWidth: '90%', margin: '2vh auto'}}>
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
                </Box>
            </Stack>
        </>
    );
}