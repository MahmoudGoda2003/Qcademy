import { Button, Grid, Paper, TextField, Typography, Checkbox, FormControlLabel } from "@mui/material"
import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { useGoogleLogin } from '@react-oauth/google';
import GoogleIcon from '@mui/icons-material/Google';
import axios from "axios";
import globals from '../utils/globals';
import styles from "../utils/styles";

export default function Login({theme}) {

    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [remember, setRemember] = useState(false);

    const fields = {
        email: "email",
        password: "password",
        remember: "remember",
    }

    const handleChange = e => {
        if (e.target.name === fields.email) setEmail(e.target.value);
        if (e.target.name === fields.password) setPassword(e.target.value);
    }

    const googleLogin = useGoogleLogin({
        onSuccess: async (response) => {
            try{
                const result = await axios.post(`${globals.baseURL}/person/google`, response.access_token, {headers: {"Content-Type": "text/plain"}, withCredentials: true})
                globals.user = {
                    firstName: result.data.firstName,
                    lastName: result.data.lastName,
                    photoLink: result.data.photoLink
                }
                navigate("/home")
            }catch (error) {
                alert('An error occurred, please try again later :(')
                console.error(error);
            }
            // console.log(response);
        },
        onError: error => console.log(error),
    });
    
    const handleSignIn = async (event) => {
        event.preventDefault();
        const checkUser = {
            email: email,
            password: password,
            remember: remember,
        }
        try {
            const response = await axios.post(`${globals.baseURL}/person/login`, {}, {
                params: {
                    email: email,
                    password: password
                },
                withCredentials: true
            })
            globals.user = {
                firstName: response.data.firstName,
                lastName: response.data.lastName,
                photoLink: response.data.photoLink,
                email: response.data.email,
                dateOfBirth: response.data.dateOfBirth
            }
            navigate("/home")
        } catch (error) {
            alert('Invalid email or password')
            console.error(error);
        }

    }

    return (
        <Grid sx={styles.gridStyle} >
            <img src={theme.palette.mode === 'light'? require("../img/LogoFull.png") : require("../img/LogoFullLight.png")}
            style={{display: 'block', margin: 'auto', maxHeight: '10vh', maxWidth: '45vh'}}
            alt="Logo"
            ></img>
            <Paper 
                elevation={5}
                sx={styles.paperStyle}
            >
                <Grid
                    sx={styles.innerGridStyle}
                    component='form'
                    onSubmit={handleSignIn}
                >
                    <Typography sx={styles.gridElement} component={'h1'} variant={'h4'} align="center">Sign in</Typography>
                    <TextField
                        sx={styles.gridElement}
                        required
                        label="E-mail"
                        type={fields.email}
                        name={fields.email}
                        onChange={handleChange}
                    />
                    <TextField 
                        sx={styles.gridElement}
                        required
                        label="Password"
                        type={fields.password}
                        name={fields.password}
                        onChange={handleChange}
                    />
                    <FormControlLabel
                        sx={styles.gridElementText}
                        control={
                            <Checkbox
                                name={fields.remember}
                                onChange={() => setRemember(!remember)}
                            />
                        }
                        label="Remember Me"
                    />
                    <Typography sx={styles.gridElementText}>
                        Don't have an account? <Link to='/signup'>Create New Account</Link>
                    </Typography>
                    <Button variant="contained" size="large" sx={styles.gridElement} type="submit">Sign in</Button>
                    <Button variant="outlined" size="large" sx={styles.gridElement} onClick={googleLogin} startIcon={<GoogleIcon />}>Sign in with Google</Button>
                </Grid>
            </Paper>
            <Typography variant="body2" color="textSecondary" align="center">
                {"Copyright © "}
                <Link color="inherit" href="/home" underline="hover">Qcademy</Link>{" "}
                {new Date().getFullYear()}
                {"."}
            </Typography>
        </Grid>
    );
}