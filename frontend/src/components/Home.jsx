import Header from './Header';
import CoursesList from './CoursesList';
import { Typography } from '@mui/material';

var courses = []

export default function Home() {
    

    const TextStyle={fontFamily: 'Segoe Ui', fontWeight: 'lighter'};
    return (
        <>
            <Header userInfo={{img:"as"}} searchOptions={['1', '2', '3', '4']} />
            <Typography variant='h3'  marginTop='5vh' marginLeft='10vh' sx={TextStyle}>Pick Up Where You Left Off</Typography>
            <Typography variant='h6' marginLeft='15vh' sx={TextStyle}>These Are All The Courses You've Enrolled In</Typography>
            <CoursesList CoursesList={courses}></CoursesList>
            <Typography variant='h3'  marginTop='5vh' marginLeft='10vh' sx={TextStyle}>Recommended Courses</Typography>
            <Typography variant='h6'  marginLeft='15vh' sx={TextStyle}>We Think You'll Like These Suggestions</Typography>
            <CoursesList CoursesList={courses}></CoursesList>    
        </>
    );
}