import { Button, Grid, Paper, Typography, Modal, Backdrop, Fade, Box, CircularProgress } from "@mui/material"
import { Link, useNavigate } from "react-router-dom"
import { useState } from "react";
import { MuiOtpInput } from "mui-one-time-password-input";
import globals from '../utils/globals';
import axios from "axios";
import styles from "../utils/styles";


export default function ConfirmEmail({theme}) {
    const navigate = useNavigate();

    const [code, setCode] = useState('');
    const [modal, setModal] = useState(false);

    const handleCode = async (event) => {
        event.preventDefault();
        setModal(true);
        try {
            globals.user.code = code;
            await axios.post(`${globals.baseURL}/person/signup/validate`, globals.user)
            globals.user = null
            navigate('/login')
        } catch (error) {
            alert("A problem has occurred, please check the code and try again :^(")
            console.log(error);    
        }
        closeModal();
    }

    const closeModal = () => {
        setModal(false);
    }

    return (
        <>
            <Modal
                open={modal}
                onClose={closeModal}
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
                    <Fade in={modal}>
                        <Box sx={styles.hiddenModalStyle}>
                        <Typography color={'white'} margin={'2vh'} fontSize={20}>Validating Code...</Typography>
                        <CircularProgress margin={'1vh'} color="secondary" />
                        </Box>
                    </Fade>
            </Modal>
            <Grid sx={styles.gridStyle}>
                <img src={theme.palette.mode === 'light'? require("../img/LogoFull.png") : require("../img/LogoFullLight.png")}
                style={{display: 'block', margin: 'auto', maxHeight: '10vh', maxWidth: '45vh'}}
                alt="Logo"
                ></img>
                <Paper 
                    elevation={5}
                    sx={styles.paperStyle}
                >
                    <Grid sx={styles.innerGridStyle} component="form" onSubmit={handleCode}>
                        <Typography sx={styles.gridElement} component={'h1'} variant={'h4'} align="center">Confirm Your Email</Typography>
                        <Typography sx={styles.gridElement} variant={'body1'} align="center">We sent you an email with a 6 digit code</Typography>
                        <MuiOtpInput
                            sx ={styles.gridElement}
                            value={code}
                            length={6}
                            onChange={newValue => setCode(newValue)} />
                        <Button variant="contained" size="large" sx={styles.gridElement} type="submit">Confirm code</Button>
                    </Grid>
                </Paper>
                <Typography variant="body2" color="textSecondary" align="center">
                    {"Copyright © "}
                    <Link color="inherit" href="/home" underline="hover">Qcademy</Link>{" "}
                    {new Date().getFullYear()}
                    {"."}
                </Typography>
            </Grid>
        </>
    );
}