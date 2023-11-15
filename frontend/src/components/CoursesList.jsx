import * as React from 'react';
import Box from '@mui/material/Box';
import CourseCard from "./CourseCard"
import { Stack } from '@mui/material';


export default function CoursesList(courses) {
  return (
    <Box sx={{ minWidth: '10vw', maxWidth: '90%', margin: '2vh auto' }}>
      <Stack direction={'row'} spacing={7} sx={{overflow:'auto', maxWidth: '100%', padding: '2vh'}}>
          {courses.CoursesList.map((course, index) => (
                <CourseCard key={index} name={course.name} description={course.description} tags={course.tags.slice(0, 5)} rating={course.rating} courseid={course.courseid} teacherName={course.teacherName}></CourseCard>
          ))}
      </Stack>
    </Box>
  );
}
