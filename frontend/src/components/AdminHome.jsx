import React, { useEffect, useState } from 'react';
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
import axios from 'axios';


export default function Admin() {

    const [promotionRequests, setPromotionRequests] = useState([]);
    
    

    // setPromotionRequests(result)    
    useEffect(() => {
        const fetchData = async () => {
          try {
            // Fetch data from your API or endpoint
            const result = await axios.get(`${globals.baseURL}/admin/promotionRequests`, {withCredentials: true})
            console.log(result);
            const parsedResult = result.data.map((result) => ({
                userId: result.userId,
                personName: result.userName,
                requestedRole: result.requestedRole,
                personImage: result.userImage
            }))

            setPromotionRequests(parsedResult);
          } catch (error) {
            console.log(error);
          }
        };
    
        fetchData();
      }, []);


    const handleReq = async (userId, status) => {
        console.log('helloo from handler ' + userId + " " + status);
        try {
            // Fetch data from your API or endpoint
            const response = await axios.get(`${globals.baseURL}/admin/promotionRequests`, {
                withCredentials: true
            })
            console.log(response);
            const newRequests = promotionRequests.filter((item) => item.userId != userId)
            setPromotionRequests(newRequests);
        } catch (error) {
            console.log(error);
        }
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
                                referrerPolicy="no-referrer"
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