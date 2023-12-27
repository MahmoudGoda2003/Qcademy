import React, { useEffect, useState } from 'react';
import {Avatar, IconButton, List, ListItem, ListItemAvatar, ListItemButton, ListItemText, Paper, Typography } from '@mui/material';
import globals from '../../utils/globals';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ThumbDownAltIcon from '@mui/icons-material/ThumbDownAlt';
import axios from 'axios';
import SuccessModal from '../Modals/SuccessModal';


export default function Admin() {

    const [promotionRequests, setPromotionRequests] = useState([]);
    const [successModal, setSuccessModal] = useState();
    const [message, setMessage] = useState("");
    
    // setPromotionRequests(result)    
    useEffect(() => {
        const fetchData = async () => {
          try {
            const result = await axios.get(`${globals.baseURL}/admin/promotionRequests`, {withCredentials: true})
            const parsedResult = result.data.map((result) => ({
                userId: result.userId,
                personName: result.userName,
                requestedRole: result.requestedRole.toLowerCase(),
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
        try {
            const response = await axios.post(`${globals.baseURL}/admin/changeRole`, {userId:userId, status:status}, {withCredentials: true});
            if (status) setMessage("accepted");
            else setMessage("rejected");
            setSuccessModal(true);
            const newRequests = promotionRequests.filter((item) => item.userId !== userId)
            setPromotionRequests(newRequests);
            setTimeout(() => closeSuccessModal(), 1000)
        } catch (error) {
            console.log(error);
        }
    }
    
    const closeSuccessModal = () => {
        setSuccessModal(false)
    }

    return (
        <>
            <SuccessModal open={successModal} handleClose={closeSuccessModal} message={`Successully ${message} request`}></SuccessModal>
            <Typography variant="h4" sx={{margin: '5vh 25vw'}}>
                Promotion Requests
            </Typography>
            <Paper elevation={5} sx={{width: '50vw', margin: 'auto'}}> 
            <List dense sx={{ width: '100%', bgcolor: 'background.paper', alignItems:'auto', margin:'auto' }}>
            {(promotionRequests.toString() === "") ?
                <ListItem>
                    <ListItemText>You have no requests to review</ListItemText>
                </ListItem>
                :
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
                        <ListItemText id={req.userId} primary={`${req.personName} wants to be promoted to a ${req.requestedRole}`} />
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