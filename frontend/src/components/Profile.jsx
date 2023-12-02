import { IconButton, Typography, Paper, Box, Stack, Avatar, Modal, Input, Button, Backdrop, Fade } from "@mui/material";
import { useState } from "react";
import InfoField from "./InfoField";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import globals from '../utils/globals';
import styles from "../utils/styles";

const getRank = (coursesCompleted) => {
    switch(parseInt(coursesCompleted/5)){
        case 0:
            return "👶  newbie 👶"
        case 1:
            return "🔥  Seasoned 🔥" 
        default:
            return "Unranked"
    }
}


export default function Profile () {

    const [firstName, setFirstName] = useState(globals.user.firstName);
    const [lastName, setLastName] = useState(globals.user.lastName);
    const [education, setEducation] = useState('school');
    const [phone, setPhone] = useState('0222021908');
    const [imageUrl, setImageUrl] = useState((globals.user.photoLink)? globals.user.photoLink: '');
    const [enrolledCourses, setEnrolledCourses] = useState(11);
    const [completedCourses, setCompletedCourses] = useState(19);

    const [dob, setDob] = useState((globals.user.dateOfBirth)? globals.user.dateOfBirth: 'You didn\'t tell me <:^(');
    const [modal, setModal] = useState(false)
    const [tempImageUrl, setTempImageUrl] = useState('');
    const [imageFile, setImageFile] = useState(null)

    const chooseImage = (e) => {
        setTempImageUrl(URL.createObjectURL(e.target.files[0]));
        setImageFile(e.target.files[0]);
    }

    const uploadImage = () => {
        if(imageFile == null)
            return
        /// send imagefile to backend
        setImageUrl(tempImageUrl)
        setImageFile(null)
        closeHandler()
    }
    
    const openHandler = () => {
        setModal(true)
        setTempImageUrl(imageUrl)
    }

    const closeHandler = () => {
        setModal(false)
    }

    return (
        <>
            <Modal
                open={modal}
                onClose={closeHandler}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
                closeAfterTransition
                slots={{ backdrop: Backdrop }}
                slotProps={{
                    backdrop: {
                    timeout: 250,
                    },
                }}
            >
                <Fade in={modal}>
                    <Box sx={styles.modalStyle}>
                        <Stack alignItems={'center'}>
                            <Typography id="modal-modal-title" variant="h5" component="h2">
                                Upload Your Profile Image
                            </Typography>
                            <Avatar
                                alt={firstName.toUpperCase()}
                                src={tempImageUrl}
                                sx={{ width: '15vw', height: '15vw', margin: '2vh' }}
                            />
                        </Stack>
                        <Stack>
                            <Button component="label" sx={{
                                margin:'1vh'
                            }} variant="contained" startIcon={<CloudUploadIcon />}>
                                Upload Image
                                <Input sx={styles.VisuallyHiddenStyle} type="file" onChange={chooseImage}/>
                            </Button>
                            <Button variant="outlined" sx={{
                                margin:'1vh'
                            }} 
                            onClick={uploadImage}>Confirm</Button>
                        </Stack>
                    </Box>
                </Fade>
            </Modal>
            <Stack direction={'row'} sx={{marginLeft:'auto', justifyContent:'center', alignItems: 'center'}}>

                <Paper elevation={2} align='center'
                    sx={{
                        padding:'5vh',
                        margin: '4vh',
                        maxWidth:'50vh'
                    }}
                >
                    <IconButton onClick={openHandler} >
                        <Avatar
                            alt={firstName.toUpperCase()}
                            src={imageUrl}
                            referrerPolicy="no-referrer"
                            sx={{ width: '20vw', height: '20vw' }}
                        />
                    </IconButton>

                    <Typography fontSize={35} marginBottom={'1vh'}> {firstName + " " + lastName} </Typography>
                    <Typography fontSize={20} color='gray' marginBottom={'5vh'}> {getRank(6)} </Typography>

                    <Stack direction={'row'}>
                        <Stack width={'50%'}>
                            <Typography color={'gray'} fontSize={16}>Courses Enrolled</Typography>
                            <Typography fontSize={24}>{enrolledCourses}</Typography>
                        </Stack>
                        <Stack width={'50%'}>
                            <Typography color={'gray'} fontSize={16}>Courses Completed</Typography>
                            <Typography fontSize={24}>{completedCourses}</Typography>
                        </Stack>
                    </Stack>
                </Paper>
                <Stack padding={'1vh'} margin={'1vh'}>
                    <Stack direction={'row'}>
                        <InfoField field={'First Name'} value={firstName} setValue={setFirstName}></InfoField>
                        <InfoField field={'Last Name'} value={lastName} setValue={setLastName}></InfoField>
                    </Stack>
                    <InfoField field={'Education'} value={education} setValue={setEducation}></InfoField>
                    <InfoField field={'Phone Number'} value={phone} setValue={setPhone}></InfoField>
                    <InfoField field={'Date Of Birth'} value={dob} setValue={setDob}></InfoField>
                </Stack>
            </Stack>
        </>
    );
}