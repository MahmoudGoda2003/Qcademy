import CoursesList from './CoursesList';
import { Typography } from '@mui/material';


export default function Home(props) {
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




    const TextStyle = {fontFamily: 'Segoe Ui', fontWeight: 'lighter'};
    const TextStyle2 = { color:'gray'};
    return (
        <>
            {(enrolledCourses.length === 0)?
                <>
                    <Typography variant='h3'  marginTop='5vh' marginLeft='10vh' sx={TextStyle}>Pick Up Where You Left Off</Typography>
                    <Typography variant='h6' marginLeft='15vh' sx={TextStyle}>These Are All The Courses You've Enrolled In</Typography>
                    <Typography variant='h6' align='center' margin='8vh' sx={TextStyle2}>You Haven't Enrolled In Any Courses Yet ...</Typography>
                </>:
                <>
                    <Typography variant='h3'  marginTop='5vh' marginLeft='10vh' sx={TextStyle}>Pick Up Where You Left Off</Typography>
                    <Typography variant='h6' marginLeft='15vh' sx={TextStyle}>These Are All The Courses You've Enrolled In</Typography>
                    <CoursesList courses={enrolledCourses}></CoursesList>
                </>
                
            }
            {(recommendedCourses.length === 0)?
                <>
                    <Typography variant='h3'  marginTop='5vh' marginLeft='10vh' sx={TextStyle}>Pick Up Where You Left Off</Typography>
                    <Typography variant='h6' marginLeft='15vh' sx={TextStyle}>These Are All The Courses You've Enrolled In</Typography>
                    <Typography variant='h6' align='center' margin='8vh' sx={TextStyle2}>There Are No Courses Yet ...</Typography>
                </>:
                <>
                    <Typography variant='h3'  marginTop='5vh' marginLeft='10vh' sx={TextStyle}>Recommended Courses</Typography>
                    <Typography variant='h6'  marginLeft='15vh' sx={TextStyle}>We Think You'll Like These Suggestions</Typography>
                    <CoursesList courses={recommendedCourses}></CoursesList>    
                </>
                
            }
        </>
    );
}