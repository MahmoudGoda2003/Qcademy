import Header from './Header';
import CoursesList from './CoursesList';
import { Typography } from '@mui/material';


export default function Home(props) {
    const enrolledCourses = []
    const recommendedCourses = []



    const TextStyle = {fontFamily: 'Segoe Ui', fontWeight: 'lighter'};
    const TextStyle2 = { color:'gray'};
    return (
        <>
            <Header onThemeChange={props.onThemeChange} theme={props.theme} userInfo={{img:"as"}} searchOptions={['1', '2', '3', '4']} />
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