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

    const chooseImage = (e) => {
        setTempImageUrl(URL.createObjectURL(e.target.files[0]));
        console.log(URL.createObjectURL(e.target.files[0]));
    }

    const handleInputImg = () => {
        document.getElementById('fileInput').click();
    };

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
                    margin="dense"
                    label="Title"
                    fullWidth
                    variant="standard"
                />
                <TextField
                    autoFocus
                    margin="dense"
                    label="Description"
                    fullWidth
                    variant="standard"
                />
                <TextField
                    autoFocus
                    margin="dense"
                    label="tages"
                    fullWidth
                    variant="standard"
                />

            </DialogContent>
            <DialogActions>
                <Button autoFocus onClick={handleClose}>
                    Cancel
                </Button>
                <Button onClick={handleClose}>Submit</Button>
            </DialogActions>
        </Dialog>

    );
}
