import { Box, Grid, Typography } from "@mui/material";
import { useLocation } from "react-router-dom"
import CourseCard from "../Course/CourseCard";

export default function Search() {
    const location = useLocation();
    const results = location.state.results;

    const TextStyle = {fontFamily: 'sans-serif'}
    const TextStyle2 = { color: 'gray' };

    return (
        <Box maxWidth='100%' width={'85%'} margin={'auto'}>
            {(results === 0) ?
                <>
                    <Typography variant='h4' marginTop='7vh' sx={TextStyle}>Your Search Results</Typography>
                    <Typography variant='h7' sx={TextStyle}>These Are All The Courses That match your search</Typography>
                    <Typography variant='h7' align='center' margin='8vh' sx={TextStyle2}>Sorry, No courses match your search</Typography>
                </> :
                <>
                    <Typography variant='h4' marginTop='7vh' sx={TextStyle}>Your Search Results</Typography>
                    <Typography variant='h7' sx={TextStyle}>These Are All The Courses That match your search</Typography>

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
                        {results.map((course) => (
                            <CourseCard key={course.courseId}
                                course={course}>
                            </CourseCard>
                        ))}
                    </Grid>
                </>
            }
        </Box>
    );
}