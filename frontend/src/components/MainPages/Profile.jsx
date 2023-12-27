import { IconButton, Typography, Paper, Box, Stack, Avatar, Modal, Input, Button, Backdrop, Fade, TextField } from "@mui/material";
import { useState, useEffect } from "react";
import InfoField from "../Reusable/InfoField";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import globals from '../../utils/globals';
import styles from "../../utils/styles";
import DateField from "../Reusable/DateField";
import ErrorModal from "../Modals/ErrorModal";
import SuccessModal from "../Modals/SuccessModal";
import PromotionServices from "../../service/PromotionsServices";
import UploadServices from "../../service/UploadServices";
import SaveIcon from '@mui/icons-material/Save';
import UserService from "../../service/UserService";
import LoadingModal from "../Modals/LoadingModal";

export default function Profile () {

    const [user, setUser] = useState();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [dateOfBirth, setDob] = useState('');
    const [imageUrl, setImageUrl] = useState('');
    const [bio, setBio] = useState('');

    useEffect(() => {
        const getInfo = async () => {
            try {
                const res = await UserService.getInfo();
                setUser(res);
                setFirstName(res.firstName);
                setLastName(res.lastName);
                setEmail(res.email);
                setDob(res.dateOfBirth);
                setImageUrl(res.photoLink);
                setBio(res.bio);
            }
            catch (error) {
                console.log(error)
            }
        }
        getInfo();
    }, [])
    
    const [modal, setModal] = useState(false)
    const [errorModal, setErrorModal] = useState(false);
    const [successModal, setSuccessModal] = useState(false);
    const [loadingModal, setLoadingModal] = useState(false);
    const [message, setMessage] = useState('');

    const [tempImageUrl, setTempImageUrl] = useState('');
    const [imageFile, setImageFile] = useState(null);

    const [editBio, setEditBio] = useState(false);

    const chooseImage = (e) => {
        setTempImageUrl(URL.createObjectURL(e.target.files[0]));
        setImageFile(e.target.files[0]);
    }

    const closeSuccessModal = () => {
        setSuccessModal(false);
    }

    const closeErrorModal = () => {
        setErrorModal(false);
    }

    const closeLoadingModal = () => {
        setLoadingModal(false);
    }

    const updateInfo = async () => {
        const user = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            photoLink: imageUrl,
            bio: bio,
            dateOfBirth: dateOfBirth.$D ?dateOfBirth.$D + "-" + dateOfBirth.$M + "-" + dateOfBirth.$y : dateOfBirth,
        }
        await UserService.updateInfo(user);
        setMessage('Successfully updated your info');
        setSuccessModal(true);
        setTimeout(() => closeSuccessModal(), 1000);
    }

    const uploadImage = async (event) => {
        if(imageFile === null)
            return
        try {
            setLoadingModal(true);
            const uploadedImage = await UploadServices.uploadImage(imageFile);
            setImageUrl(uploadedImage);
            closeLoadingModal();
            closeHandler();
        } catch (error) {
            console.log(error)
        }
        setMessage('Successfully uploaded your image');
        setSuccessModal(true);
        setTimeout(() => closeSuccessModal(), 1000);
    }
    
    const requestPromotion = async () => {
        try {
            await PromotionServices.requestPromotion();
            setMessage('Successfully applied for a promotion')
            setSuccessModal(true)
            setTimeout(() => closeSuccessModal(), 1000)
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

    const saveBio = (e) => {
        if (e.key == "Enter") {
            setBio(e.target.value);
            setEditBio(false);
            console.log(bio);
        }
    }

    return (
        <>
            <ErrorModal open={errorModal} handleClose={closeErrorModal} message={'You already applied for a promotion'} />
            <SuccessModal open={successModal} handleClose={closeSuccessModal} message={message} />
            <LoadingModal open={loadingModal} handleClose={closeLoadingModal} message={'Uploading Your Image'} />
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

                    <Stack direction={'row'}>
                        <Stack width={'100%'}>
                            <Typography color={'gray'} fontSize={16} gutterBottom>About Me</Typography>
                            {(bio !== undefined && bio !== "" && editBio === false) ?
                                <>
                                    <Box margin={'2vh auto 0'} sx={{display: "flex", flexDirection: "row"}}>
                                        <Typography fontSize={20} width={'200px'} sx={{wordWrap: 'break-word'}}>{bio}</Typography>
                                    </Box>
                                    <Button variant="text" onClick={() => {setEditBio(true)}}>Edit your bio</Button>
                                </>
                                :
                                !editBio ? <Button variant="text" onClick={() => {setEditBio(true)}}>Add a bio</Button> :
                                <TextField size="small" onKeyDown={saveBio} placeholder='bio' multiline rows={'3'} defaultValue={bio} />
                                
                            }
                        </Stack>
                    </Stack>
                </Paper>
                <Stack padding={'1vh'} margin={'1vh'}>
                    <Stack direction={'row'}>
                        <InfoField field={'First Name'} value={firstName} setValue={setFirstName}></InfoField>
                        <InfoField field={'Last Name'} value={lastName} setValue={setLastName}></InfoField>
                    </Stack>
                    <InfoField field={'E-mail'} value={email} setValue={setEmail}></InfoField>
                    <DateField field={'Date Of Birth'} value={dateOfBirth} setValue={setDob}></DateField>
                    <Button variant="contained" onClick={updateInfo} startIcon={<SaveIcon />}>Save Changes</Button>
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