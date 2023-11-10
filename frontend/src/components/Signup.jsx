import { Button, Grid, Paper, TextField, Typography } from "@mui/material"
import { Link } from "react-router-dom"
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

export default function Signup({theme}) {

    const gridElement = {
        margin: '2vh auto',
        width: '90%'
    }

    const gridElementText = {
        margin: '1vh auto',
        width: '90%'
    }

    const gridElement2 = {
        display: 'flex',
        flexDirection: 'row',
        margin: '2vh auto',
        width: '90%'
    }

    return (
        <Grid 
            sx={{
                margin: '2vh',
                padding: '2vh',
                justifyContent: 'center',
                alignItems: 'center'
            }}
        >
            <img src={theme.palette.mode === 'light'? require("./LogoFull.png") : require("./LogoFullLight.png")}
            style={{display: 'block', margin: 'auto', maxHeight: '10vh', maxWidth: '45vh'}}
            alt="Logo"
            ></img>
            <Paper 
                elevation={2}
                sx={{
                    height: 'fit',
                    display: 'flex',
                    maxWidth: '45vh',
                    minWidth: 'fit',
                    flexDirection: 'column',
                    margin: '2vh auto',
                    padding: '2vh',
                }}
            >
                <Grid
                    sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                        justifyContent: 'center'
                    }}
                >
                    <Typography sx={gridElement} component={'h1'} variant={'h4'} align="center">Create New Account</Typography>
                    <Grid sx={gridElement2}>
                        <TextField sx={{marginRight: '1vh'}} required label="First Name"/>
                        <TextField sx={{marginLeft: '1vh'}} required label="Last Name"/>
                    </Grid>
                    <TextField sx={gridElement} required label="E-mail"/>
                    <TextField sx={gridElement} required label="Password" type="password"/>
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DatePicker sx={gridElement} required label="Birth date" />
                    </LocalizationProvider>
                    <Typography sx={gridElementText}>
                        Already have an account? <Link to='/login'>Sign in</Link>
                    </Typography>
                    <Button variant="contained" size="large" sx={gridElement}>Create Account</Button>
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