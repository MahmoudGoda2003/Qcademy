import Header from './Header';
import Box from '@mui/material/Box';
import { Collapse, ListItemButton, Stack, Typography, List, ListItemText} from '@mui/material';
import * as React from "react";
import { useLocation } from 'react-router-dom';
import {useState} from "react";
import {ExpandLess, ExpandMore} from "@mui/icons-material";


export default function CourseInfo() {

    const location = useLocation();

    const titleStyle = {
        margin: '2vh 0',
    }

    const bodyStyle = {
        margin: '0 0.5vw 2vh',
    }
    const modules = [{
            name: "Algorithms Module name",
            description: "How to structure data",
            image: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
            tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
            rating: 2.5,
            moduleid: '12',
            teacherName: 'Michael Elsayed',
            lectureLink: "a",
            lectureQuiz: "a",
            lectureAssignment: "a"
        },
        {
            name: "Binary search Module name",
            description: "How to structure data",
            image: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
            tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
            rating: 2.5,
            moduleid: '11',
            teacherName: 'Michael Elsayed',
            lectureLink: "a",
            lectureQuiz: "a",
            lectureAssignment: "a"
        },
        {
            name: "Complexity Module name",
            description: "How to structure data",
            image: "https://miro.medium.com/v2/resize:fit:900/0*TDgnPm06sS0np--2.jpg",
            tags: ['Binary search', 'Complexity', 'Greedy', 'DP'],
            rating: 2.5,
            moduleid: '13',
            teacherName: 'Michael Elsayed',
            lectureLink: "a",
            lectureQuiz: "",
            lectureAssignment: ""
        }]

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
                    <Typography gutterBottom variant="h5" component="div" sx={{margin: '2vh 2vw 2vh'}}>
                        {course.name}
                    </Typography>
                    <CourseMaterials modules={modules}>
                    </CourseMaterials>

                </Stack>

                <Stack direction={'column'}
                       sx={{width: '90%', height: '90%', overflow:'auto'}}
                >
                    <Typography>
                        Lecture Video
                    </Typography>
                    <Box sx={{width: '100%', aspectRatio:'16/9', overflow:'auto'}}>
                        <iframe width="100%" height="100%"
                                src="https://www.youtube.com/embed/7P8yRpu_MoE?si=0h9U4Yw7r07jvW4F"
                                title="YouTube video player" frameBorder="0"
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                allowFullScreen></iframe>
                    </Box>

                    {/*<Typography sx={titleStyle} variant='h4' fontSize={26}>*/}
                    {/*    Description*/}
                    {/*</Typography>*/}
                    {/*<Typography sx={bodyStyle} variant='body'>*/}
                    {/*    {course.description}*/}
                    {/*</Typography>*/}
                    {/*<Divider sx={{margin: '3vh'}}/>*/}
                    {/*<Typography sx={titleStyle} variant='h4' fontSize={26}>*/}
                    {/*    Skills you will learn*/}
                    {/*</Typography>*/}
                    {/*<Typography sx={bodyStyle} variant='body'>*/}
                    {/*    {*/}
                    {/*        course.tags.map((tag) => {*/}
                    {/*            return <Chip*/}
                    {/*                sx={{*/}
                    {/*                    margin: '0 0.5vh 0.5vw',*/}
                    {/*                }}*/}
                    {/*                key={tag} label={tag}></Chip>*/}
                    {/*        })*/}
                    {/*    }*/}
                    {/*</Typography>*/}
                    {/*<Divider sx={{margin: '3vh'}}/>*/}
                    {/*/!*TODO: Add real module count*!/*/}
                    {/*<Typography sx={titleStyle} variant='h4' fontSize={26}>*/}
                    {/*    There are 13 modules in this course*/}
                    {/*</Typography>*/}
                    {/*/!*TODO: Add real module view*!/*/}
                    {/*<ModuleList></ModuleList>*/}
                    {/*<Divider sx={{margin: '3vh'}}/>*/}
                </Stack>
            </Stack>
        </>
    );
}



function CourseMaterials({modules}) {
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

function Module({module}) {
    const [open, setOpen] = useState(false);
    const [selectedIndex, setSelectedIndex] = React.useState(1);
    const index = 0;
    const handleListItemClick = (event, index) => {
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
                                onClick={(event) => handleListItemClick(event, 1)}>
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
                                onClick={(event) => handleListItemClick(event, 2)}>
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
                                onClick={(event) => handleListItemClick(event, 3)}>
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

