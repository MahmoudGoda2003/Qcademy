import { Card, CardActionArea, Collapse, Divider, Input, Typography } from "@mui/material";
import { Box, Stack } from "@mui/system";
import { useState } from "react";
import AssignmentOutlinedIcon from '@mui/icons-material/AssignmentOutlined';
import PlayCircleOutlineOutlinedIcon from '@mui/icons-material/PlayCircleOutlineOutlined';
import QuizOutlinedIcon from '@mui/icons-material/QuizOutlined';
import LibraryBooksOutlinedIcon from '@mui/icons-material/LibraryBooksOutlined';

export default function ModuleList({modules}) {
    console.log(modules);

    return(
        <Stack margin={'1.5vh 1.5vw'} padding={'1vh 1vw'} border={1} borderColor={'lightgray'} borderRadius={3}>
            {modules.map((module, index) => (
                <>
                <Module module={module} index={index} />
                <Divider sx={{margin: '1vw'}} />
                </>
          ))}
        </Stack>
    );
}

function Module({module, index}) {

    const [open, setOpen] = useState(false);

    return (
        <Card elevation={0}>
            <CardActionArea onClick={()=> {setOpen(!open)}} sx={{margin:'1vh', padding:'1vh'}}>
                <Typography variant="h6" fontSize={20}>
                    {module.name}
                </Typography>
                <Typography variant="body2" color={'gray'}>
                    Module {index}
                </Typography>
            </CardActionArea>
            <Collapse orientation="vertical" in={open}>
                <Stack margin={'0 1vh 1vh'} padding={'1vh'}>
                    <Typography marginBottom={'2vh'} fontWeight={'bold'}>
                        What's Included?
                    </Typography>
                    <Stack direction={'row'}>
                        <PlayCircleOutlineOutlinedIcon fontSize="small" />
                        <Typography margin={'0 5vh 0 1vh'}> {module.lectures.length} Videos </Typography>
                        <LibraryBooksOutlinedIcon fontSize="small"/>
                        <Typography margin={'0 5vh 0 1vh'}> {module.slideSets.length} Slide Sets </Typography>
                        <AssignmentOutlinedIcon fontSize="small"/>
                        <Typography margin={'0 5vh 0 1vh'}> {module.assignments.length} Assignments </Typography>
                        <QuizOutlinedIcon fontSize="small" />
                        <Typography margin={'0 5vh 0 1vh'}> {module.quizzes.length} Quiz </Typography>
                    </Stack>
                </Stack>
            </Collapse>
        </Card>
    );
}