import Box from '@mui/material/Box';
import { Collapse, ListItemButton, Stack, Typography, List, ListItemText } from '@mui/material';
import * as React from "react";
import { useLocation } from 'react-router-dom';
import {useState} from "react";
import {ExpandLess, ExpandMore} from "@mui/icons-material";
import YouTube from 'react-youtube';
import { useEffect } from 'react';
import axios from 'axios';
import globals from '../../utils/globals';

export default function CourseInfo() {

    const location = useLocation();

    const [selectedElement, setSelectedElement] = useState();
    const [modules, setModules] = useState([]);

    const updateView = (item) => {
        setSelectedElement(item);
    }

    const course = location.state.course;

    useEffect(() => {
        axios.get(`${globals.baseURL}/student/courseModules`, {params: {courseId: course.courseId} , withCredentials: true})
        .then((response) => {
            setModules(response.data);
        })
        .catch((error) => {
            console.log(error);
        });
    }, []);

    return (
        // overflow:'auto', maxWidth: '100%', padding: '2vh'
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
    const [open, setOpen] = useState(-1);

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
        // <Card elevation={0} sx={{overflow:'auto'}}>
        //     <CardActionArea onClick={() => setOpen(!open)} sx={{ margin: '1vh', padding: '1vh' }}>
        //         <Typography variant="h8" sx={{ marginLeft: '1vw' }}>
        //             CourseMaterials
        //         </Typography>
        //         {open ? <ExpandLess /> : <ExpandMore />}
        //     </CardActionArea>
        //     <Collapse orientation="vertical" in={open}>
        //             <Module module={module1}></Module>
        //             <CardActionArea onClick={() => setNestedOpen(!nestedOpen)} sx={{ margin: '1vh', padding: '1vh' }}>
        //                 <Typography variant="h8" sx={{ marginLeft: '2vw'}}>
        //                     Notes
        //                 </Typography>
        //                     {open ? <ExpandLess sx={{ pl: 4 }}/> : <ExpandMore sx={{ pl: 4 }}/>}
        //             </CardActionArea>
        //             {/* Nested Collapse */}
        //             <Collapse orientation="vertical" in={nestedOpen}>
        //                 <Stack margin={'1vh 0 0 2vh'} padding={'1vh'}>
        //                     {/* Nested Collapse Content Goes Here */}
        //                     <Typography>
        //                         This is the nested collapse content. You can add more details or components here.
        //                     </Typography>
        //                 </Stack>
        //             </Collapse>
        //     </Collapse>
        //
        // </Card>
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
                <ListItemText primary={module.publishDate} sx={{ marginLeft: '1vw'}}/>
                {open ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                    {(module.lectures.toString() === [].toString())?
                        <></>:
                        <>
                        {/*navigae to page contain the link of lecture*/}
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
                            {/*navigae to page contain the link of lecture*/}
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
                            {/*navigae to page contain the link of lecture*/}
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
                            {/*navigae to page contain the link of lecture*/}
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
        // <Card elevation={0}>
        //     <CardActionArea onClick={() => setOpen(!open)} sx={{ margin: '1vh', padding: '1vh' }}>
        //         <Typography variant="h8" sx={{ marginLeft: '2vw' }}>
        //             {module.name}
        //         </Typography>
        //         {open ? <ExpandLess /> : <ExpandMore />}
        //     </CardActionArea>
        //     <Collapse orientation="vertical" in={open}>
        //         {(module.lectureLink === "")?
        //             <></>:
        //             <>
        //                 {/*navigae to page contain the link of lecture*/}
        //                 <CardActionArea sx={{ margin: '1vh', padding: '1vh' }}>
        //                     <Typography variant="h8" sx={{ marginLeft: '3vw' }}>
        //                         Lecture Video
        //                     </Typography>
        //                 </CardActionArea>
        //             </>
        //
        //         }
        //
        //         {(module.lectureQuiz === "")?
        //             <></>:
        //             <>
        //                 {/*navigae to page contain the link of lecture*/}
        //                 <CardActionArea sx={{ margin: '1vh', padding: '1vh' }}>
        //                     <Typography variant="h8" sx={{ marginLeft: '3vw' }}>
        //                         Quiz
        //                     </Typography>
        //                 </CardActionArea>
        //             </>
        //
        //         }
        //
        //         {(module.lectureAssignment === "")?
        //             <></>:
        //             <>
        //                 {/*navigae to page contain the link of Assignment*/}
        //                 <CardActionArea sx={{ margin: '1vh', padding: '1vh' }} >
        //                     <Typography variant="h8" sx={{ marginLeft: '3vw' }}>
        //                         Assignment
        //                     </Typography>
        //                 </CardActionArea>
        //             </>
        //
        //         }
        //     </Collapse>
        // </Card>
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
                    This week's {selectedElement.name}
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