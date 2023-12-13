import React, { useRef } from 'react';
import { Dialog, Paper } from '@mui/material';
import Draggable from 'react-draggable';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import Button from '@mui/material/Button';
import Input from '@mui/material/Input';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import Chip from '@mui/material/Chip';
import { useState } from 'react';
import CardActionArea from '@mui/material/CardActionArea';
import CardMedia from '@mui/material/CardMedia';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import styles from "../utils/styles";



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

function PaperComponent(props) {
    return (
        <Draggable
            handle="#draggable-dialog-title"
            cancel={'[class*="MuiDialogContent-root"]'}
        >
            <Paper {...props} />
        </Draggable>
    );
}

export default function CreateCourse({ open, handleClose }) {

    const [tempImageUrl, setTempImageUrl] = React.useState('');
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [tages, setTages] = useState([]);
    const [startDate, setStartDate] = useState(null);

    const fields = {
        title: "title",
        description: "description",
        tages: "tages",
        startDate: "startDate"
    }

    const chooseImage = (e) => {
        setTempImageUrl(URL.createObjectURL(e.target.files[0]));
        console.log(URL.createObjectURL(e.target.files[0]));
    }

    const handleInputImg = () => {
        document.getElementById('fileInput').click();
    };

    const handleChange = event =>{
        if (event.target.name === fields.title) setTitle(event.target.value);
        if (event.target.name === fields.description) setDescription(event.target.value);
        if (event.target.name === fields.tages) setTages(event.target.value);
    }

    const handleCreation = async(event) => {
        event.preventDefault();
        const course = {
            title: title,
            description: description,
            imageURL: tempImageUrl,
            tages: tages,
            startDate: startDate.$D + '-' + startDate.$M + '-' + startDate.$y
        }
        try {
            /*await axios.post(`${globals.baseURL}/course/create`, course)
            globals.user = user;
            alert('The course is created successfully')
            navigate('/teacherHome');*/
        } catch (error) {
            alert('Error creating course, try again later')
            console.error(error);
        }
    }

    return (
        <Dialog
            open={open}
            onClose={handleClose}
            PaperComponent={PaperComponent}
            aria-labelledby="draggable-dialog-title"
            fullWidth
            maxWidth="xs"
        >
            <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
                Create Course
            </DialogTitle>
            <DialogContent>
                <CardActionArea onClick={handleInputImg} >
                    <CardMedia
                        component="img"
                        image={tempImageUrl}
                        alt="Course image"
                        sx={{ maxHeight: '25vh', minHeight: '25Vh' }}
                    />
                </CardActionArea>
                <Input sx={VisuallyHiddenStyle} type="file" onChange={chooseImage} id='fileInput' />

                <TextField
                    autoFocus
                    required
                    margin="dense"
                    label="Title"
                    fullWidth
                    variant="standard"
                    onChange={handleChange}
                />
                <TextField
                    autoFocus
                    margin="dense"
                    label="Description"
                    fullWidth
                    variant="standard"
                    onChange={handleChange}
                />
                <TextField
                    autoFocus
                    required
                    margin="dense"
                    label="Tages"
                    fullWidth
                    variant="standard"
                    onChange={handleChange}
                />
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DatePicker
                                sx={styles.gridElement}
                                required 
                                disablePast
                                format="DD/MM/YYYY"
                                label="Start date"
                                value={startDate}
                                slotProps={{
                                    textField: {
                                    required: true,
                                    },
                                }}
                                onChange={(newValue) => setStartDate(newValue)} />
                        </LocalizationProvider>
            </DialogContent>
            <DialogActions>
                <Button autoFocus onClick={handleClose}>
                    Cancel
                </Button>
                <Button onClick={handleCreation}>Submit</Button>
            </DialogActions>
        </Dialog>

    );
}
