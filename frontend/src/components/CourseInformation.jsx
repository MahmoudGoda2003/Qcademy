import { IconButton, Typography, Paper, Box, Stack, Avatar, Modal, Input, Button, Backdrop, Fade } from "@mui/material";
import { useState, useEffect } from "react";
import InfoField from "./InfoField";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import globals from '../utils/globals';
import styles from "../utils/styles";
import CourseDateField from "./CourseDateField";
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import axios from "axios";
import { useNavigate } from "react-router-dom";


export default function CourseInformation () {

    console.log(globals.course)
    const [title, setTitle] = useState(globals.course.title);
    const [description, setDescription] = useState(globals.course.description);
    const [duration, setDuration] = useState(globals.course.duration);
    const [tags, setTags] = useState(globals.course.tags);
    const [imageUrl, setImageUrl] = useState(globals.course.imageUrl);

    let startDate = globals.course.startDate;
    const changeDate = String(startDate).localeCompare(new Date().toJSON().slice(0, 10))>0;

    const [modal, setModal] = useState(false)
    const [tempImageUrl, setTempImageUrl] = useState('');
    const [imageFile, setImageFile] = useState(null)
    const navigate = useNavigate();

    const setDate = (newDate) => {
        startDate = newDate.$y + '-' + newDate.$M + '-' + newDate.$D;
    }

    useEffect(() => {
        globals.course.title = title
        globals.course.description = description
        globals.course.startDate = startDate
        globals.course.duration = duration
        globals.course.tags = tags
        globals.course.imageUrl = imageUrl
        localStorage.setItem("course", JSON.stringify(globals.course));
        //axios.post(`${globals.baseURL}/person/test`, globals.user)       modify when routine is added in backend
    })

    const chooseImage = (e) => {
        setTempImageUrl(URL.createObjectURL(e.target.files[0]));
        setImageFile(e.target.files[0]);
    }

    const uploadImage = async (event) => {
        if(imageFile == null)
            return
        /// send imagefile to backend
        try {
            const result = await axios.post(`https://freeimage.host/api/1/upload?key=6d207e02198a847aa98d0a2a901485a5&source=`+tempImageUrl, {})
            setImageUrl(result.image.url)
            setImageFile(null)
            closeHandler()
        } catch (error) {
            alert("internal server error, try again later")
        }
    }
    
    const openHandler = () => {
        setModal(true)
        setTempImageUrl(imageUrl)
    }

    const updateModules = () => {
        //navigate('/course/modules')
    }

    const closeHandler = () => {
        setModal(false)
    }

    const handleClickOpen = () => {
        
    };

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
                                Upload Your Course Image
                            </Typography>
                            <Avatar
                                alt={title.toUpperCase()}
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
                <Stack>
                    <Paper elevation={2} align='center'
                        sx={{
                            padding:'4vh',
                            margin: '3vh',
                            maxWidth:'50vh'
                        }}
                    >
                        <IconButton onClick={openHandler} >
                            <Avatar
                                alt={title.toUpperCase()}
                                src={imageUrl}
                                referrerPolicy="no-referrer"
                                sx={{ width: '20vw', height: '20vw' }}
                            />
                        </IconButton>

                        <Typography fontSize={35} marginBottom={'1vh'}> {title} </Typography>
                    </Paper>
                    <Paper elevation={2} align='center'
                        sx={{
                            padding:'4vh',
                            margin: '3vh',
                            maxWidth:'50vh'
                        }}
                    >
                        <IconButton onClick={updateModules}>
                            <Typography fontSize={30} marginBottom={'1vh'}> {"Update Modules"} </Typography>
                        </IconButton>
                    </Paper>
                </Stack>
                <Stack padding={'1vh'} margin={'1vh'}>
                    <InfoField field={'Title'} value={title} setValue={setTitle}></InfoField>
                    <InfoField field={'Description'} value={description} setValue={setDescription}></InfoField>
                    <InfoField field={'Tags'} value={tags} setValue={setTags}></InfoField>
                    <Stack direction={"row"}>
                        <InfoField field={'Duration'} value={duration} setValue={setDuration}></InfoField>
                        <CourseDateField field={'Start Date'} value={startDate} setValue={setDate} canChange={changeDate}></CourseDateField>
                    </Stack>
                </Stack>
            </Stack>
        </>
    );
}