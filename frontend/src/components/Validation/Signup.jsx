import { Button, Grid, Paper, TextField, Typography, LinearProgress } from "@mui/material"
import { Link, useNavigate } from "react-router-dom"
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { useState } from "react";
import { useGoogleLogin } from '@react-oauth/google';
import GoogleIcon from '@mui/icons-material/Google';
import styles from "../../utils/styles";
import LoadingModal from "../Modals/LoadingModal";
import ErrorModal from "../Modals/ErrorModal";
import UserService from "../../service/UserService";


export default function Signup({theme}) {

    const navigate = useNavigate();

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [DOB, setDOB] = useState(null);
    const [modal, setModal] = useState(false)
    const [errorModal, setErrorModal] = useState(false)

    const fields = {
        firstName: "firstname",
        lastName: "lastname",
        email: "email",
        password: "password",
        DOB: "DOB"
    }

    const handleChange = event => {
        if (event.target.name === fields.firstName) setFirstName(event.target.value);
        if (event.target.name === fields.lastName) setLastName(event.target.value);
        if (event.target.name === fields.email) setEmail(event.target.value);
        if (event.target.name === fields.password) setPassword(event.target.value);
        document.getElementById(event.target.id).setCustomValidity("");
    }

    const handleSignUp =  async (event) => {
        event.preventDefault();
        setModal(true);
        const user = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            dateOfBirth: DOB.$D + "-" + DOB.$M + "-" + DOB.$y
        }
        // TODO: send info to backend
        setModal(true);
        try {
            await UserService.signUp(user.email);
            // globals.user = user;
            // console.log(globals.user);
            console.log(user);
            closeModal();
            navigate('/confirmEmail', {
                state: {
                    user: user
                }
            });
        } catch (error) {
            setErrorModal(true);
            closeModal();
            setTimeout(() => closeErrorModal(), 1000)
        }
    }

    const googleLogin = useGoogleLogin({
        onSuccess: async (response) => {
            setModal(true);
            try{
                await UserService.google(response.access_token);
                closeModal();
                navigate("/home");
            }catch (error) {
                setErrorModal(true);
                closeModal();
                setTimeout(() => closeErrorModal(), 1000);
                console.error(error);
            }
        },
        onError: error => console.log(error),
    });

    const closeModal = () => {
        setModal(false)
    }

    const closeErrorModal = () => {
        setErrorModal(false);
    }

    const minLength = 8;

    return (
        <>
            <ErrorModal open={errorModal} handleClose={closeErrorModal} message={'An error occurred, please try again later :('} />
            <LoadingModal open={modal} handleClose={closeModal} message={'Proccessing your info'} />
            <Grid sx={styles.gridStyle}>
                <img src={theme.palette.mode === 'light'? require("../../img/LogoFull.png") : require("../../img/LogoFullLight.png")}
                style={{display: 'block', margin: 'auto', maxHeight: '10vh', maxWidth: '45vh'}}
                alt="Logo"
                ></img>
                <Paper 
                    elevation={5}
                    sx={styles.paperStyle}
                >
                    <Grid sx={styles.innerGridStyle} component="form" onSubmit={handleSignUp}>
                        <Typography sx={styles.gridElement} component={'h1'} variant={'h4'} align="center">Create New Account</Typography>
                        <Grid sx={styles.gridElement2}>
                            <TextField
                                sx={{marginRight: '1vh'}}
                                required
                                label="First Name"
                                name={fields.firstName}
                                inputProps={{ pattern: '[A-z]*'}}
                                onChange={handleChange}
                            />
                            <TextField sx={{marginLeft: '1vh'}}
                            required
                            label="Last Name"
                            name={fields.lastName}
                            inputProps={{ pattern: '[A-z]*'}}
                            onChange={handleChange}
                            />
                        </Grid>
                        <TextField sx={styles.gridElement} required label="E-mail" type={fields.email} name={fields.email} onChange={handleChange}/>
                        <TextField
                            onInvalid={() => {
                                document
                                .getElementById("password-field")
                                .setCustomValidity("Password should include the following: \n • Number\n • Lowercase Letter\n • Uppercase Letter\n • Symbol");
                            }}
                            sx={styles.gridElement}
                            id="password-field"
                            required
                            label="Password"
                            type="Password"
                            name={fields.password}
                            inputProps={{ pattern: "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$" }}
                            onChange={handleChange}
                        />
                        {password.length > 0 &&
                        <>
                            <LinearProgress 
                            variant="determinate"
                            value={Math.min((password.length * 100) / minLength, 100)}
                            sx={{
                                margin: '1vh',
                                width: '88%',
                                color: 'secondry',
                            }}
                            />
                            <Typography
                                variant="body2"
                                sx={{ margin:'0', width: '88%'}}
                            >
                            <Typography component={'span'} variant="body2" sx={{fontWeight: '700'}}>Password Strength: </Typography>
                                {password.length < 2 && 'Very weak'}
                                {password.length >= 2 && password.length < 4 && 'Weak'}
                                {password.length >= 4 && password.length < 8 && 'Strong'}
                                {password.length >= 8 && 'Very strong'}
                            </Typography>
                        </>
                        }
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DatePicker
                                sx={styles.gridElement}
                                required 
                                disableFuture
                                format="DD/MM/YYYY"
                                label="Birth date"
                                value={DOB}
                                slotProps={{
                                    textField: {
                                    required: true,
                                    },
                                }}
                                onChange={(newValue) => setDOB(newValue)} />
                        </LocalizationProvider>
                        <Typography sx={styles.gridElementText}>
                            Already have an account? <Link to='/login'>Sign in</Link>
                        </Typography>
                        <Button variant="contained" size="large" sx={styles.gridElement} type="submit">Create Account</Button>
                        <Button variant="outlined" size="large" sx={styles.gridElement} onClick={googleLogin} startIcon={<GoogleIcon />}>Sign Up with Google</Button>
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