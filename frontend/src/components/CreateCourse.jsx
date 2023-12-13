import React, { useState } from 'react';
import Input from '@mui/material/Input';
import TextField from '@mui/material/TextField';
import CardActionArea from '@mui/material/CardActionArea';
import CardMedia from '@mui/material/CardMedia';
import { Backdrop, Button, Chip, Fade, InputAdornment, Modal } from '@mui/material';
import { Stack } from '@mui/system';
import styles from '../utils/styles';
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import InputTags from './InputTags';
import globals from '../utils/globals';
import { DatePicker, LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import axios from 'axios';

const VisuallyHiddenStyle = {
    clip: 'rect(0 0 0 0)',
    clipPath: 'inset(50%)',
    height: 1,
    overflow: 'hidden',
    position: 'absolute',
    bottom: 0,
    left: 0,
    whiteSpace: 'nowrap',
};

export default function CreateCourse({ open, handleClose, onCreateCourse }) {

    const [imageUrl, setImageUrl] = useState();
    const [imageFile, setImageFile] = useState();
    const [title, setTitle] = useState();
    const [description, setDescription] = useState();
    const [startDate, setStartDate] = useState();
    const [duration, setDuration] = useState();
    const [tags, setTags] = useState([]);

    const chooseImage = (e) => {
        setImageUrl(URL.createObjectURL(e.target.files[0]));
        setImageFile(e.target.files[0]);
    }

    const handleInputImg = () => {
        document.getElementById('fileInput').click();
    };

    const handleChange = event => {
        if (event.target.name === "Title") setTitle(event.target.value);
        if (event.target.name === "Description") setDescription(event.target.value);
        if (event.target.name === "Duration") setDuration(event.target.value);
    }

    const addCourse = async (event) => {
        let uploadedImage = {};
        try {
            const formData = new FormData ();
            formData.append("file", imageFile);
            formData.append("upload_preset", "xdmym8xv");
            formData.append("api_key", "593319395186373");

            const response = await axios.post(
                "https://api.cloudinary.com/v1_1/dlcy5giof/image/upload",
                formData
            )            
            uploadedImage = response.data.secure_url;
        } 
        catch (error) {
            console.log(error)
        }
        const course =  {
            name: title,
            description: description,
            photoLink: uploadedImage,
            tags: tags,
            startDate: startDate.$D + '-' + startDate.$M + "-" + startDate.$y,
            estimatedTime: duration,
            teacherName: globals.user.firstName + " " + globals.user.lastName,
        }
        try {
            const response = await axios.post(`${globals.baseURL}/teacher/createCourse`, course, {withCredentials: true})
            onCreateCourse(course);
            console.log(response);
        }
        catch (error) {
            console.log(error);
        }
        setImageUrl(null);
        setImageFile(null);
        handleClose();
    }


    return (
        <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-title"
        aria-describedby="modal-description"
        closeAfterTransition
        slots={{ backdrop: Backdrop }}
        slotProps={{
            backdrop: {
            timeout: 250,
            },
        }}
        >
            <Fade in={open}>
                <Stack sx={styles.modalStyle2} alignItems={'left'}>
                    <CardActionArea onClick={handleInputImg} >
                        { imageUrl ?
                            <CardMedia
                                component="img"
                                image={imageUrl}
                                alt="Course image"
                                sx={{ maxHeight: '25vh', minHeight: '25Vh' }}
                            /> 
                            :
                            <CardMedia
                                alt="Course image"
                                sx={{
                                    maxHeight: '25vh',
                                    minHeight: '25Vh',
                                    display:'flex',
                                    alignItems: 'center',
                                    justifyContent: 'center'
                                }}
                            >
                                <AddPhotoAlternateIcon sx={{ fontSize: 100 }}/>
                            </CardMedia>
                        }
                    </CardActionArea>
                    <Input sx={VisuallyHiddenStyle} type="file" onChange={chooseImage} id='fileInput' />
                    <TextField
                        margin="normal"
                        label="Title"
                        name="Title"
                        variant="outlined"
                        onChange={handleChange}
                    />
                    <TextField
                        margin="normal"
                        label="Description"
                        name="Description"
                        variant="outlined"
                        multiline
                        rows={5}
                        onChange={handleChange}
                    />
                    <InputTags tags={tags} setTags={setTags} />
                    <Stack direction={'row'}>
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DatePicker
                                sx={{margin: '2vh 0.5vh'}}
                                required
                                disablePast
                                label="Start Date"
                                value={startDate}
                                slotProps={{
                                    textField: {
                                    },
                                }}
                                onChange={(newValue) => setStartDate(newValue)} />
                        </LocalizationProvider>
                        <TextField
                            sx={{margin: '2vh 0.5vh'}}
                            label="Estimated Duration"
                            name="Duration"
                            variant="outlined"
                            onChange={handleChange}
                            InputProps={{
                                pattern: '[0-9]*',
                                endAdornment: <InputAdornment position="end">Weeks</InputAdornment>,
                            }}
                        />
                    </Stack>
                    <Button
                        variant='contained'
                        sx={{
                            margin: '2vh 0 0',
                        }}
                        onClick={addCourse}
                    >
                        Create New Course
                    </Button>
                </Stack>
            </Fade>
        </Modal>
    );
}
