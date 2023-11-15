import { Routes, Route, Link } from "react-router-dom";
import { useState } from "react";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import IconButton from '@mui/material/IconButton';
import LightModeIcon from '@mui/icons-material/LightMode';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import Header from "./components/Header";
import Home from "./components/Home";

const lightMode = createTheme({
  palette: {
    primary: {
      main: '#3d5afe',
    },
    secondary: {
      main: '#2979ff',
    },
  },
});

const darkMode = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#3d5afe',
    },
    secondary: {
      main: '#2979ff',
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
    <ThemeProvider theme={theme} >
      <CssBaseline />
      <Routes>
        <Route path="/" element={<Home/>} />
      </Routes>
      </ThemeProvider>
    </>
  );
}

const Navigation = () => (
  <nav>
    <Link to="/"> Landing </Link>
    <Link to="/home"> Home </Link>
    <Link to="/login"> Login </Link>
    <Link to="/signup"> Signup </Link>
  </nav>
);