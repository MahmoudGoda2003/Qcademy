import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import { Backdrop, Button, Divider, Fade, InputLabel, Modal, Typography } from '@mui/material';
import { Stack } from '@mui/system';
import styles from '../utils/styles';
import globals from '../utils/globals';
import axios from 'axios';
import AssignmentOutlinedIcon from '@mui/icons-material/AssignmentOutlined';
import SchoolOutlinedIcon from '@mui/icons-material/SchoolOutlined';
import InsertDriveFileOutlinedIcon from '@mui/icons-material/InsertDriveFileOutlined';
import QuizOutlinedIcon from '@mui/icons-material/QuizOutlined';

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

export default function CreateModule({ open, handleClose, onCreateModule, courseId }) {

    const [moduleName, setModuleName] = useState('');
    const [lectureName, setLectureName] = useState('');
    const [lectureUrl, setLectureUrl] = useState('');
    const [assignmentName, setAssignmentName] = useState('');
    const [assignmentUrl, setAssignmentUrl] = useState('');
    const [slidesUrl, setSlidesUrl] = useState('');
    const [quizUrl, setQuizUrl] = useState('');

    const handleChange = event => {
        if (event.target.name === "moduleName") setModuleName(event.target.value);
        if (event.target.name === "lectureName") setLectureName(event.target.value);
        if (event.target.name === "lectureUrl") setLectureUrl(event.target.value);
        if (event.target.name === "assignmentName") setAssignmentName(event.target.value);
        if (event.target.name === "assignmentUrl") setAssignmentUrl(event.target.value);
        if (event.target.name === "slidesUrl") setSlidesUrl(event.target.value);
        if (event.target.name === "quizUrl") setQuizUrl(event.target.value);
    }

    const addModule = async (event) => {
        const lectures = [{
            lectureName: lectureName,
            lectureUrl: lectureUrl
        }];

        const assignments = [{
            assignmentName: assignmentName,
            assignmentUrl: assignmentUrl
        }];

        const slideSets = [slidesUrl];

        const quizzes = [quizUrl];

        const module = {
            courseId: courseId,
            name: moduleName,
            lectures: lectures,
            assignments: assignments,
            slideSets: slideSets,
            quizzes: quizzes
        }

        onCreateModule(module);
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
                    <Stack direction={'row'} alignItems={'center'} marginBottom={'1vh'}>
                        <Typography variant='h6'>Module Name</Typography>
                    </Stack>
                    <TextField
                        size='small'
                        name="moduleName"
                        variant="outlined"
                        onChange={handleChange}
                    />
                    <Divider sx={{margin: '1vh 0 2vh 0'}}/>

                    <Stack direction={'row'} alignItems={'center'} marginBottom={'1vh'}>
                        <SchoolOutlinedIcon fontSize='small' sx={{margin: '0 0.5vw 0 0'}} />
                        <Typography variant='h6'>Module Lecture</Typography>
                    </Stack>
                    <InputLabel>Letcure Name</InputLabel>
                    <TextField
                        size='small'
                        name="lectureName"
                        variant="outlined"
                        onChange={handleChange}
                        sx={{marginBottom: '1vh'}}
                    />
                    <InputLabel>Letcure URL</InputLabel>
                    <TextField
                        size='small'
                        name="lectureUrl"
                        variant="outlined"
                        onChange={handleChange}
                    />
                    <Divider sx={{margin: '1vh 0 2vh 0'}}/>

                    <Stack direction={'row'} alignItems={'center'} marginBottom={'1vh'}>
                        <InsertDriveFileOutlinedIcon fontSize='small' sx={{margin: '0 0.5vw   0 0'}} />
                        <Typography variant='h6'>Module Slides</Typography>
                    </Stack>
                    <InputLabel>Slides URL</InputLabel>
                    <TextField
                        size='small'
                        name="slidesUrl"
                        variant="outlined"
                        onChange={handleChange}
                    />
                    <Divider sx={{margin: '1vh 0 2vh 0'}}/>

                    <Stack direction={'row'} alignItems={'center'} marginBottom={'1vh'}>
                        <AssignmentOutlinedIcon fontSize='small' sx={{margin: '0 0.5vw   0 0'}} />
                        <Typography variant='h6'>Module Assignment</Typography>
                    </Stack>
                    <InputLabel>Assignment Name</InputLabel>
                    <TextField
                        size='small'
                        name="assignmentName"
                        variant="outlined"
                        onChange={handleChange}
                        sx={{marginBottom: '1vh'}}
                    />
                    <InputLabel>Assignment URL</InputLabel>
                    <TextField
                        size='small'
                        name="assignmentUrl"
                        variant="outlined"
                        onChange={handleChange}
                    />
                    <Divider sx={{margin: '1vh 0 2vh 0'}}/>

                    <Stack direction={'row'} alignItems={'center'} marginBottom={'1vh'}>
                        <QuizOutlinedIcon fontSize='small' sx={{margin: '0 0.5vw   0 0'}} />
                        <Typography variant='h6'>Module Quiz</Typography>
                    </Stack>
                    <InputLabel>Quiz URL</InputLabel>
                    <TextField
                        size='small'
                        name="quizUrl"
                        variant="outlined"
                        onChange={handleChange}
                    />
                    <Button
                        variant='contained'
                        sx={{
                            margin: '2vh 0 0',
                        }}
                        onClick={addModule}
                    >
                        Create New Module
                    </Button>
                </Stack>
            </Fade>
        </Modal>
    );
}
