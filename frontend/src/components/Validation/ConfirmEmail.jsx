import { Button, Grid, Paper, Typography } from "@mui/material"
import { Link, useLocation, useNavigate } from "react-router-dom"
import { useState } from "react";
import { MuiOtpInput } from "mui-one-time-password-input";
import globals from '../../utils/globals';
import axios from "axios";
import styles from "../../utils/styles";
import LoadingModal from "../Modals/LoadingModal";
import ErrorModal from "../Modals/ErrorModal";


export default function ConfirmEmail({theme}) {
    const navigate = useNavigate();

    const location = useLocation();
    const user = location.state.user;
    const [code, setCode] = useState('');
    const [modal, setModal] = useState(false);
    const [errorModal, setErrorModal] = useState(false);

    const handleCode = async (event) => {
        event.preventDefault();
        setModal(true);
        try {
            console.log(globals.user);
            console.log(user);
            console.log(location);
            user.code = code;
            await axios.post(`${globals.baseURL}/person/signup/validate`, user, {withCredentials: true})
            globals.user = null
            navigate('/login')
            closeModal();
        } catch (error) {
            setErrorModal(true);
            closeModal();
            console.log(error);
            setTimeout(() => closeErrorModal(), 1000)
        }
    }

    const closeErrorModal = () => {
        setErrorModal(false);
    }

    const closeModal = () => {
        setModal(false);
    }

    return (
        <>
            <ErrorModal open={errorModal} handleClose={closeErrorModal} message={'A problem has occurred, please check the code and try again'} />
            <LoadingModal open={modal} handleClose={closeModal} message={'Checking Code'} />
            <Grid sx={styles.gridStyle}>
                <img src={theme.palette.mode === 'light'? require("../../img/LogoFull.png") : require("../../img/LogoFullLight.png")}
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