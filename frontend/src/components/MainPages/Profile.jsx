import { IconButton, Typography, Paper, Box, Stack, Avatar, Modal, Input, Button, Backdrop, Fade, SpeedDial, SpeedDialIcon } from "@mui/material";
import { useState, useEffect } from "react";
import InfoField from "../Reusable/InfoField";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import globals from '../../utils/globals';
import styles from "../../utils/styles";
import DateField from "../Reusable/DateField";
import axios from "axios";
import ErrorModal from "../Modals/ErrorModal";

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
    const [education, setEducation] = useState((globals.user.education));
    const [phone, setPhone] = useState((globals.user.phone));
    const [dateOfBirth, setDob] = useState((globals.user.dateOfBirth));
    const [imageUrl, setImageUrl] = useState((globals.user.photoLink));

    //To be changed upon handling in the backend
    const [enrolledCourses, setEnrolledCourses] = useState(11);
    const [completedCourses, setCompletedCourses] = useState(19);
    
    const [modal, setModal] = useState(false)
    const [errorModal, setErrorModal] = useState(false);
    const [tempImageUrl, setTempImageUrl] = useState('');
    const [imageFile, setImageFile] = useState(null)

    useEffect(() => {
        globals.user.firstName = firstName
        globals.user.lastName = lastName
        globals.user.education = education
        globals.user.dateOfBirth = dateOfBirth
        globals.user.photoLink = imageUrl
        globals.user.phone = phone
        localStorage.setItem("user", JSON.stringify(globals.user));
        //axios.post(`${globals.baseURL}/person/test`, globals.user)       modify when routine is added in backend
    })

    const chooseImage = (e) => {
        setTempImageUrl(URL.createObjectURL(e.target.files[0]));
        setImageFile(e.target.files[0]);
    }

    const closeErrorModal = () => {
        setErrorModal(false);
    }

    const uploadImage = async (event) => {
        if(imageFile == null)
            return
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
            closeHandler()
        } catch (error) {
            console.log(error)
        }
    }
    
    const requestPromotion = async () => {
        try {
            await axios.post(`${globals.baseURL}/${globals.user.role.toLowerCase()}/requestPromotion`, null, {withCredentials: true});
        }
        catch(error) {
            setErrorModal(true);
            setTimeout(() => closeErrorModal(), 1000)
        }
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
            <ErrorModal open={errorModal} handleClose={closeErrorModal} message={'You already applied for a promotion'} />
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
                        <InfoField field={'First Name'} value={firstName} setValue={setFirstName} onChange={() => {globals.user.firstName = firstName}}></InfoField>
                        <InfoField field={'Last Name'} value={lastName} setValue={setLastName}></InfoField>
                    </Stack>
                    <InfoField field={'Education'} value={education} setValue={setEducation}></InfoField>
                    <InfoField field={'Phone Number'} value={phone} setValue={setPhone}></InfoField>
                    <DateField field={'Date Of Birth'} value={dateOfBirth} setValue={setDob}></DateField>
                </Stack>
                {globals.user.role !== "ADMIN" && 
                    <Button
                        variant="contained"
                        sx={{
                            display: "block",
                            position: "fixed",
                            bottom: '20px',
                            right: "20px",
                            borderRadius: "20px"
                        }}
                        onClick={requestPromotion}
                    >
                        {globals.user.role === "STUDENT" ? "Request Promotion" : globals.user.role === "TEACHER" ? "Become an Admin" : ""}
                    </Button>
                }
            </Stack>
        </>
    );
}