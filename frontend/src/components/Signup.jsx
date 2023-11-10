import { Button, Grid, Paper, TextField, Typography, LinearProgress } from "@mui/material"
import { Link } from "react-router-dom"
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { useState } from "react";

export default function Signup({theme}) {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [DOB, setDOB] = useState(null);

    const handleChange = event => {
        if (event.target.name === "firstname") setFirstName(event.target.value);
        if (event.target.name === "lastname") setLastName(event.target.value);
        if (event.target.name === "email") setEmail(event.target.value);
        if (event.target.name === "password") setPassword(event.target.value);
    }

    const handleSignUp = e => {
        e.preventDefault();
        const user = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            DOB: DOB.$D + "-" + DOB.$M + "-" + DOB.$y,
        }
        console.log(user);
    }

    const minLength = 8;

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
        margin: '2vh 0vh auto',
        width: '90%'
    }

    const gridElementText = {
        margin: '1vh auto',
        width: '90%'
    }

    const gridElement2 = {
        display: 'flex',
        flexDirection: 'row',
        margin: '2vh 0vh auto',
        width: '90%'
    }

    return (
        <Grid sx={gridStyle}>
            <img src={theme.palette.mode === 'light'? require("./LogoFull.png") : require("./LogoFullLight.png")}
            style={{display: 'block', margin: 'auto', maxHeight: '10vh', maxWidth: '45vh'}}
            alt="Logo"
            ></img>
            <Paper 
                elevation={2}
                sx={paperStyle}
            >
                <Grid sx={innerGridStyle} component="form" onSubmit={handleSignUp}>
                    <Typography sx={gridElement} component={'h1'} variant={'h4'} align="center">Create New Account</Typography>
                    <Grid sx={gridElement2}>
                        <TextField sx={{marginRight: '1vh'}} required label="First Name" name="firstname" onChange={handleChange} />
                        <TextField sx={{marginLeft: '1vh'}} required label="Last Name" name="lastname" onChange={handleChange} />
                    </Grid>
                    <TextField sx={gridElement} required label="E-mail" type="email" name="email" onChange={handleChange}/>
                    <TextField sx={gridElement} required label="Password" type="password" name="password" onChange={handleChange}/>
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
                            sx={gridElement}
                            required 
                            disableFuture
                            label="Birth date"
                            value={DOB}
                            slotProps={{
                                textField: {
                                  required: true,
                                },
                              }}
                            onChange={(newValue) => setDOB(newValue)} />
                    </LocalizationProvider>
                    <Typography sx={gridElementText}>
                        Already have an account? <Link to='/login'>Sign in</Link>
                    </Typography>
                    { password.length < minLength ?
                        <Button variant="contained" size="large" sx={gridElement} type="submit" disabled>Create Account</Button>
                        : <Button variant="contained" size="large" sx={gridElement} type="submit">Create Account</Button>
                    }
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