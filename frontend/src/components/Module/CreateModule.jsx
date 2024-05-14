import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import { Backdrop, Button, Divider, Fade, InputLabel, Modal, Typography } from '@mui/material';
import { Stack } from '@mui/system';
import styles from '../../utils/styles';
import globals from '../../utils/globals';
import axios from 'axios';
import AssignmentOutlinedIcon from '@mui/icons-material/AssignmentOutlined';
import SchoolOutlinedIcon from '@mui/icons-material/SchoolOutlined';
import InsertDriveFileOutlinedIcon from '@mui/icons-material/InsertDriveFileOutlined';
import QuizOutlinedIcon from '@mui/icons-material/QuizOutlined';

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
        let lectures = [];
        let assignments = [];
        let slidesSets = [];
        let quizzes = [];

        if (lectureName !== '' && lectureUrl !== '') {
            lectures = [{
                name: lectureName,
                videoURL: lectureUrl.split("v=")[1].split("&")[0]
            }];
        }
        
        if (assignmentName !== '' && assignmentUrl !== '') {
            assignments = [{
                name: assignmentName,
                assignmentURL: assignmentUrl
            }];
        }

        if (slidesUrl !== '') slidesSets = [slidesUrl];

        if (quizUrl !== '') quizzes = [quizUrl];

        const currentDate = new Date();

        const module = {
            courseId: courseId,
            lectures: lectures,
            assignments: assignments,
            slidesSets: slidesSets,
            quizzes: quizzes,
            name: moduleName,
        }

        try{
            const response = await axios.post(`${globals.baseURL}/teacher/createModule`, module, {withCredentials: true})
            onCreateModule(module);
            console.log(response);
        }
        catch (error) {
            console.log(error);
        }

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
                        placeholder='Please add a youtube link to the lecture'
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
                        placeholder='Please add a link to th PDF file containing the slides'
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
                        placeholder='Please add a link to th PDF file containing the assignment'
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
                        placeholder='Please add a link to th PDF file containing the Quiz'
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
