import React, { useState } from 'react';
import {Avatar, IconButton, List, ListItem, ListItemAvatar, ListItemButton, ListItemText, Paper, Typography } from '@mui/material';
import { Grid} from '@mui/material';
import CourseCard from './CourseCard';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import CreateCourses from './CreateCourse';
import globals from '../utils/globals';
import { Box, width } from '@mui/system';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ThumbDownAltIcon from '@mui/icons-material/ThumbDownAlt';


export default function Admin() {

    const [promotionRequests, setPromotionRequests] = useState([{
        userId: 1,
        personName: 'ciara',
        requestRole: 'Teacher',
        personImage: 'https://i.imgur.com/uVhlLC3.jpeg'
    },{
        userId: 3,
        personName: 'eliza',
        requestRole: 'Admin',
        personImage: 'https://i.imgur.com/uVhlLC3.jpeg'
    }]);

    const getPromotionRequests = async () => {
        //send request to back
    }


    const handleReq = (userId, status) => {
        console.log('helloo from handler ' + userId + " " + status);
    }
    

    return (
        <>
            <Typography variant="h4" sx={{margin: '2vh 30vw'}}>
                Promotion Requests
            </Typography>
            <Paper elevation={5} sx={{width: '40vw', margin: 'auto'}}> 
            <List dense sx={{ width: '100%', bgcolor: 'background.paper', alignItems:'auto', margin:'auto' }}>
            {
                promotionRequests.map((req) => 
                    <ListItem sx={{ width: '100%'}}
                        key={req.userId}
                    >
                        <ListItemButton>
                        <ListItemAvatar>
                            <Avatar
                                alt={req.personName}
                                src={req.personImage}
                            />
                        </ListItemAvatar>
                        <ListItemText id={req.userId} primary={`${req.personName} wants to be promoted to a ${req.requestRole}`} />
                        </ListItemButton>

                        <IconButton onClick={() => handleReq(req.userId, true)}>
                            <ThumbUpAltIcon color='success'/>
                        </IconButton>
                        <IconButton onClick={() => handleReq(req.userId, false)}>
                            <ThumbDownAltIcon color='error'/>
                        </IconButton>
                    </ListItem>
                )
            }
            </List>
            </Paper>
        </>
    );
}