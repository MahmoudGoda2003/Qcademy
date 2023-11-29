import { Button, Grid, Paper, Typography } from "@mui/material"
import { Link, useNavigate } from "react-router-dom"
import { useState } from "react";
import { MuiOtpInput } from "mui-one-time-password-input";
import globals from "../globals";
import axios from "axios";


export default function ConfirmEmail({theme}) {
    const navigate = useNavigate();

    const [code, setCode] = useState('');

    const handleCode = async (event) => {
        event.preventDefault();
        console.log(code);
        try {
            globals.user.code = code;
            const response = await axios.post(`${globals.baseURL}/person/signup/validate`, globals.user)
            globals.user = null
            navigate('/login')
        } catch (error) {
            alert("A problem has occurred, please check the code and try again :^(")
            console.log(error);    
        }
        // TODO: send verification code to back
    }

    const gridStyle = {
        margin: '2vh',
        padding: '2vh',
        justifyContent: 'center',
        alignItems: 'center',
        minWidth: '25vw'
    }

    const paperStyle = {
        height: 'fit',
        display: 'flex',
        maxWidth: '45vh',
        minWidth: 'fit',
        flexDirection: 'column',
        margin: '2vh auto',
        padding: '2vh',
        minWidth: '25vw'
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
    

    return (
        <Grid sx={gridStyle}>
            <img src={theme.palette.mode === 'light'? require("./LogoFull.png") : require("./LogoFullLight.png")}
            style={{display: 'block', margin: 'auto', maxHeight: '10vh', maxWidth: '45vh'}}
            alt="Logo"
            ></img>
            <Paper 
                elevation={5}
                sx={paperStyle}
            >
                <Grid sx={innerGridStyle} component="form" onSubmit={handleCode}>
                    <Typography sx={gridElement} component={'h1'} variant={'h4'} align="center">Confirm Your Email</Typography>
                    <Typography sx={gridElement} variant={'body1'} align="center">We sent you an email with a 6 digit code</Typography>
                    <MuiOtpInput
                        sx ={gridElement}
                        value={code}
                        length={6}
                        onChange={newValue => setCode(newValue)} />
                    <Button variant="contained" size="large" sx={gridElement} type="submit">Confirm code</Button>
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