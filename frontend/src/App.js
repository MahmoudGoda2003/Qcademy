import { Routes, Route, Link } from "react-router-dom";
import Home from "./components/Home";
import Signup from "./components/Signup";
import ConfirmEmail from "./components/ConfirmEmail";
import Login from "./components/Login";
import { useState } from "react";
import ProtectedRoute from "./components/ProtectedRoute";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import IconButton from '@mui/material/IconButton';
import LightModeIcon from '@mui/icons-material/LightMode';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import globals from "./globals";


const lightMode = createTheme({
  palette: {
    primary: {
      main: '#1a237e',
    },
    secondary: {
      main: '#edf2ff',
    },
  },
});

const darkMode = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#0052cc',
    },
    secondary: {
      main: '#edf2ff',
    },
  },
});

export default function App() {
  const [user, setUser] = useState(null);

  const [theme, setTheme] = useState(lightMode);
  const toggleColorMode = () => setTheme(((theme === lightMode)? darkMode : lightMode));

  return (
    <>
    <IconButton onClick={toggleColorMode} color="inherit">
        {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
    </IconButton>
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Routes>
        <Route path="/" element={
          <ProtectedRoute redirectPath={"/login"}>
            <Home />
          </ProtectedRoute>
        }/>
        <Route path="signup" element={<Signup theme = {theme} />} />
        <Route path="/confirmEmail" element={
          <ProtectedRoute redirectPath={"/login"}>
            <ConfirmEmail theme = {theme} />
          </ProtectedRoute>
        }/>
        <Route path="login" element={<Login theme = {theme} />} />
      </Routes>
      </ThemeProvider>
    </>
}
