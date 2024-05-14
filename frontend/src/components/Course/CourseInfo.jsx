import * as React from "react";
import { Collapse, ListItemButton, Stack, Typography, List, ListItemText, Box } from '@mui/material';
import { useLocation } from 'react-router-dom';
import {useState} from "react";
import {ExpandLess, ExpandMore} from "@mui/icons-material";
import YouTube from 'react-youtube';
import { useEffect } from 'react';
import CourseService from "../../service/CourseService";

export default function CourseInfo() {

    const location = useLocation();

    const [selectedElement, setSelectedElement] = useState();
    const [modules, setModules] = useState([]);

    const updateView = (item) => {
        setSelectedElement(item);
    }

    const course = location.state.course;

    useEffect(() => {
        const fetchModules = async () => {
            const res = await CourseService.getCourseModules(course.courseId, "student");
            setModules(res);
        }

        fetchModules();
    }, []);

    return (
        <>
            <Stack direction={'row'} spacing={10}
                   sx={{width: '90%', height: '100vh', margin: '2vh 2vw', padding: '2vh 2vw'}}>

                <Stack direction={'column'}
                       sx={{width: '20vw', height: '90%', overflow:'auto'}}
                >
                    <Typography variant="h5" component="div" sx={{margin: '2vh 0'}}>
                        {course.name}
                    </Typography>
                    {modules ? <CourseMaterials modules={modules} onUpdate={updateView} /> : <></>}

                </Stack>
                <ViewElement selectedElement={selectedElement} />
            </Stack>
        </>
    );
}



function CourseMaterials({modules, onUpdate}) {
    return (
        <List
            sx={{ width: '100%' }}
            aria-labelledby="nested-list"
        >
            <ListItemButton>
                <ListItemText primary="CourseMaterials" />
            </ListItemButton>
            <List>
                {modules.map((module, index) => (
                    <Module
                        key={index}
                        module={module}
                        onUpdate={onUpdate}
                    >
                    </Module>
                ))}
            </List>
        </List>
    );
}

function Module({module, onUpdate}) {

    const [open, setOpen] = useState(false);
    const [selectedIndex, setSelectedIndex] = useState(0);
    const handleListItemClick = (event, index, name, url, type) => {
        onUpdate({
            type: type,
            url: url,
            name: name,
        });
        setSelectedIndex(index);
    };
    return (
        <List
            sx={{ width: '100%' }}
            aria-labelledby="nested-list-subheader"
        >
            <ListItemButton onClick={() => {setOpen(!open);}}>
                <ListItemText primary={module.name} sx={{ marginLeft: '1vw'}}/>
                {open ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                    {(module.lectures.toString() === "")?
                        <></>:
                        <>
                        {module.lectures.map((lecture) =>(
                            <ListItemButton
                            selected={selectedIndex === 1}
                            onClick={(event) => handleListItemClick(event, 1, lecture.name, lecture.videoURL, 'lecture')}>
                            <ListItemText primary="Lecture Video" sx={{ marginLeft: '2vw'}} />
                            </ListItemButton>
                        ))}
                        </>
                    }
                    {(module.quizzes.toString() === "")?
                        <></>:
                        <>
                        {module.quizzes.map((quizUrl) =>(
                            <ListItemButton
                            selected={selectedIndex === 2}
                            onClick={(event) => handleListItemClick(event, 2, 'Quiz', quizUrl, '')}>
                            <ListItemText primary="Lecture Quiz" sx={{ marginLeft: '2vw'}} />
                            </ListItemButton>
                        ))}
                        </>
                    }
                    {(module.assignments.toString() === "")?
                        <></>:
                        <>
                        {module.assignments.map((assignment) =>(
                            <ListItemButton
                            selected={selectedIndex === 3}
                            onClick={(event) => handleListItemClick(event, 3, assignment.name, assignment.assignmentURL, '')}>
                            <ListItemText primary="Lecture Assignment" sx={{ marginLeft: '2vw'}} />
                            </ListItemButton>
                        ))}
                        </>
                    }
                    {(module.slidesSets.toString() === "")?
                        <></>:
                        <>
                        {module.slidesSets.map((slidesUrl) =>(
                            <ListItemButton
                            selected={selectedIndex === 4}
                            onClick={(event) => handleListItemClick(event, 4, "slides", slidesUrl, '')}>
                            <ListItemText primary="Lecture Slides" sx={{ marginLeft: '2vw'}} />
                            </ListItemButton>
                        ))}
                        </>
                    }
                </List>
            </Collapse>
        </List>
    );
}

function ViewElement({selectedElement}) {

    const options = {
        height: '585',
        width: '960',
        playerVars: {
            autoplay: 0,
            controls: 1,
        },
    };

    return (
        <>
        {selectedElement === undefined ? <></> :
        <Stack margin={'0 auto'}
            sx={{width: '70%', height: '90%', overflow:'auto'}}
        >
            <Typography variant='h5' margin={'2vh 0'}>
                {selectedElement.name}
            </Typography>
            {selectedElement.type === 'lecture' ?
                <Box sx={{width: '100%', aspectRatio:'16/9', overflow:'auto'}}>
                    <YouTube videoId={selectedElement.url} opts={options}/>
                </Box>
            :
                <Box sx={{width: '100%', height: '90%', overflow:'auto'}}>
                    <iframe src={selectedElement.url} width="100%" style={{aspectRatio: 9/16}} />
                </Box>
            }
        </Stack>}
        </>
    );
}