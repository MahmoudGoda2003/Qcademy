import Header from './Header';
import Box from '@mui/material/Box';
import { Collapse, ListItemButton, Stack, Typography, List, ListItemText, Link} from '@mui/material';
import * as React from "react";
import { useLocation } from 'react-router-dom';
import {useState} from "react";
import {ExpandLess, ExpandMore} from "@mui/icons-material";
import YouTube from 'react-youtube';

export default function CourseInfo() {

    const location = useLocation();

    /*const modules = location.state.modules;*/

    const [selectedElement, setSelectedElement] = useState();

    const updateView = (item) => {
        setSelectedElement(item);
    }

    const modules = location.state.modules;

    const course = {
        name: "Data Structures",
        description: "How to structure data",
        image: "https://media.geeksforgeeks.org/wp-content/cdn-uploads/20230706095706/intro-data-structure-%E2%80%93-1.png",
        tags: ['Trees', 'Graphs', 'Arrays'],
        rating: 4,
        courseid: '23',
        teacherName: 'Ahmed Ayman'
    };

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
    const [open, setOpen] = useState(false);
    const [nestedOpen, setNestedOpen] = useState(false);
    return (
        <List
            sx={{ width: '100%', bgcolor: 'background.paper' }}

            aria-labelledby="nested-list-subheader"
        >
            <ListItemButton onClick={() => setOpen(!open)}>
                <ListItemText primary="CourseMaterials" />
                {open ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                    {modules.map((module, index) => (
                        <Module
                            module={module}
                            onUpdate={onUpdate}
                        >
                        </Module>
                    ))}
                    <ListItemButton onClick={() => setNestedOpen(!nestedOpen)} sx={{ pl: 4 }}>
                        <ListItemText primary="Notes" />
                        {nestedOpen ? <ExpandLess /> : <ExpandMore />}
                    </ListItemButton>
                    <Collapse orientation="vertical" in={nestedOpen}>
                         <Stack margin={'1vh 0 0 2vh'} padding={'1vh'}>
                             {/* Nested Collapse Content Goes Here */}
                             <Typography>
                                 This is the nested collapse content. You can add more details or components here.
                             </Typography>
                         </Stack>
                     </Collapse>
                </List>
            </Collapse>
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
    const index = 0;
    const handleListItemClick = (event, index, type, url) => {
        onUpdate({
            type: type,
            url: url,
            name: "Lecture"
        });
        setSelectedIndex(index);
    };
    return (
        <List
            sx={{ width: '100%', bgcolor: 'background.paper' }}
            component="nav"
            aria-labelledby="nested-list-subheader"
        >
            <ListItemButton onClick={() => {setOpen(!open);}}>
                <ListItemText primary={module.name} sx={{ marginLeft: '1vw'}}/>
                {open ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <List component="div" disablePadding>
                    {(module.lectureLink === "")?
                        <></>:
                        <>
                        {/*navigae to page contain the link of lecture*/}
                            <ListItemButton
                                selected={selectedIndex === 1}
                                onClick={(event) => handleListItemClick(event, 1, "lecture", module.lectureLink)}>
                                <ListItemText primary="Lecture Video" sx={{ marginLeft: '2vw'}} />
                            </ListItemButton>
                        </>
                    }
                    {(module.lectureQuiz === "")?
                        <></>:
                        <>
                            {/*navigae to page contain the link of lecture*/}
                            <ListItemButton
                                selected={selectedIndex === 2}
                                onClick={(event) => handleListItemClick(event, 2, "quiz", module.lectureQuiz)}>
                                <ListItemText primary="Quiz" sx={{ marginLeft: '2vw'}} />
                            </ListItemButton>
                        </>
                    }
                    {(module.lectureAssignment === "")?
                        <></>:
                        <>
                            {/*navigae to page contain the link of lecture*/}
                            <ListItemButton
                                selected={selectedIndex === 3}
                                onClick={(event) => handleListItemClick(event, 3, "assignment", module.lectureAssignment)}>
                                <ListItemText primary="Assignment" sx={{ marginLeft: '2vw'}} />
                            </ListItemButton>
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
                    {selectedElement.name}
            </Typography>
            {selectedElement.type === 'lecture' ?
                <Box sx={{width: '100%', aspectRatio:'16/9', overflow:'auto'}}>
                    <YouTube videoId={selectedElement.url} opts={options}/>
                </Box>

            :
            <Box sx={{width: '100%', aspectRatio:'16/9', overflow:'auto'}}>
                <Typography variant='h6' margin={'2vh 0'}>Follow this link to view your {selectedElement.type} </Typography>
                <Typography variant='h6' margin={'2vh 0'}>
                    <a href={selectedElement.url}>{selectedElement.type}</a>
                </Typography>
            </Box>
            }
        </Stack>}
        </>
    );
}