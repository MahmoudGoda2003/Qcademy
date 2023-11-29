import { Button, Grid, Paper, TextField, Typography, Checkbox, FormControlLabel } from "@mui/material"
import { useState } from "react"
import { Link } from "react-router-dom"

export default function Login({theme}) {

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

    const handleSignIn = e => {
        e.preventDefault();
        const checkUser = {
            email: email,
            password: password,
            remember: remember,
        }

    }

    const gridStyle = {
        margin: '2vh',
        padding: '2vh',
        justifyContent: 'center',
        alignItems: 'center'
    }

    const paperStyle = {
        height: 'fit',
        display: 'flex',
        maxWidth: '45vh',
        minWidth: 'fit',
        flexDirection: 'column',
        margin: '2vh auto',
        padding: '2vh',
    }

    const innerGridStyle = {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center'
    }

    const gridElement = {
        margin: '2vh auto',
        width: '90%'
    }

    const gridElementText = {
        margin: '1vh auto',
        width: '90%'
    }

    return (
        <Grid sx={gridStyle} >
            <img src={theme.palette.mode === 'light'? require("./LogoFull.png") : require("./LogoFullLight.png")}
            style={{display: 'block', margin: 'auto', maxHeight: '10vh', maxWidth: '45vh'}}
            alt="Logo"
            ></img>
            <Paper 
                elevation={5}
                sx={paperStyle}
            >
                <Grid
                    sx={innerGridStyle}
                    component='form'
                    onSubmit={handleSignIn}
                >
                    <Typography sx={gridElement} component={'h1'} variant={'h4'} align="center">Sign in</Typography>
                    <TextField
                        sx={gridElement}
                        required
                        label="E-mail"
                        type={fields.email}
                        name={fields.email}
                        onChange={handleChange}
                    />
                    <TextField 
                        sx={gridElement}
                        required
                        label="Password"
                        type={fields.password}
                        name={fields.password}
                        onChange={handleChange}
                    />
                    <FormControlLabel
                        sx={gridElementText}
                        control={
                            <Checkbox
                                name={fields.remember}
                                onChange={() => setRemember(!remember)}
                            />
                        }
                        label="Remember Me"
                    />
                    <Typography sx={gridElementText}>
                        Don't have an account? <Link to='/signup'>Create New Account</Link>
                    </Typography>
                    <Button variant="contained" size="large" sx={gridElement} type="submit">Sign in</Button>
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