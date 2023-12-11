import { Card, CardActionArea, Collapse, Divider, Input, Typography } from "@mui/material";
import { Box, Stack, margin } from "@mui/system";
import { useState } from "react";
import PlayCircleOutlineOutlinedIcon from '@mui/icons-material/PlayCircleOutlineOutlined';
import QuizOutlinedIcon from '@mui/icons-material/QuizOutlined';
import LibraryBooksOutlinedIcon from '@mui/icons-material/LibraryBooksOutlined';

export default function ModuleList() {

    return(
        <Stack margin={'1.5vh 1.5vw'} padding={'1vh 1vw'} border={1} borderColor={'lightgray'} borderRadius={3}> 
            <Module />
            <Divider sx={{margin: '1vw'}} />
            <Module />
            <Divider sx={{margin: '1vw'}} />
            <Module />
            <Divider sx={{margin: '1vw'}} />
            <Module />
            <Divider sx={{margin: '1vw'}} />            
            <Module />
        </Stack>
    );
}

function Module() {

    const [open, setOpen] = useState(false);

    return (
        <Card elevation={0}>
            <CardActionArea onClick={()=> {setOpen(!open)}} sx={{margin:'1vh', padding:'1vh'}}>
                <Typography variant="h6" fontSize={20}>
                    Arrays
                </Typography>
                <Typography variant="body2" color={'gray'}>
                    Module 1
                </Typography>
                </CardActionArea>
                <Collapse orientation="vertical" in={open}>
                    <Stack margin={'0 1vh 1vh'} padding={'1vh'}>
                        <Typography marginBottom={'3vh'}>
                            In this module you will learn about arrays and how to use them in various problems.
                        </Typography>
                        <Typography marginBottom={'2vh'} fontWeight={'bold'}>
                            What's Included?
                        </Typography>
                        <Stack direction={'row'}>
                            <PlayCircleOutlineOutlinedIcon fontSize="small" />
                            <Typography margin={'0 5vh 0 1vh'}> 5 Videos </Typography>
                            <LibraryBooksOutlinedIcon fontSize="small"/>
                            <Typography margin={'0 5vh 0 1vh'}> 2 Slide Sets </Typography>
                            <QuizOutlinedIcon fontSize="small" />
                            <Typography margin={'0 5vh 0 1vh'}> 1 Quiz </Typography>
                        </Stack>
                    </Stack>
                </Collapse>
        </Card>
    );
}