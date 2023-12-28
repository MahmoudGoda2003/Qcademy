import axios from "axios";
import globals from "../../utils/globals";
import { Modal } from "@mui/material";

export default function AssignmentSubmit({open, handleClose, courseId, moduleId, assignmentId}) {
    
    const [assignmentSolutionUrl, setAssignmentSolutionUrl] = useState('');
    const [assignmentSolutionFile, setAssignmentSolutionFile] = useState(null);

    const chooseFile = (e) => {
        setAssignmentSolutionUrl(URL.createObjectURL(e.target.files[0]));
        setAssignmentSolutionFile(e.target.files[0]);
    }

    const handleInputFile = () => {
        document.getElementById('fileInput').click();
    };

    const uploadFile = async (event) => {
        if (assignmentSolutionFile === null)
            return
        try {
            const uploadedFile = await UploadServices.uploadFile(assignmentSolutionFile);
            setAssignmentSolutionUrl(uploadedFile);
            handleClose()
        } catch (error) {
            console.log(error)
        }
    }

    const confirmSolution = async (event) => {
        uploadFile(event);
        if (assignmentSolutionUrl == null)
            return
        try {
            const assignment = {
                courseId: courseId,
                moduleId: moduleId,
                assignmentId: assignmentId,
                solutionUrl: assignmentSolutionUrl
            }
            //const response = await axios.post(`${globals.baseURL}/student/handInAssignment`, assignment, {WithCredentials: true})    modify when routine is added in the backend
            setAssignmentSolutionFile(null)
            setAssignmentSolutionUrl(null)
            handleClose()
        } catch (error) {
            console.log(error)
        }
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
                    <Typography variant='h5' marginBottom={'1vh'}>Submit Assignment's Solution</Typography>
                    <CardActionArea onClick={handleInputFile} >
                        { solutionUrl ?
                            <CardMedia
                                component="file"
                                alt="Submit your Solution Here"
                                sx={{ maxHeight: '25vh', minHeight: '25Vh' }}
                            /> 
                            :
                            <CardMedia
                                alt={solutionUrl}
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
                    <Input sx={VisuallyHiddenStyle} type="file" onChange={chooseFile} id='fileInput' />
                    <Button
                        variant='contained'
                        sx={{
                            margin: '2vh 0 0',
                        }}
                        onClick={confirmSolution}
                    >
                        Submit
                    </Button>
                </Stack>
            </Fade>
        </Modal>
    );
}