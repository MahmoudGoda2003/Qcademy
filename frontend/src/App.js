import logo from './logo.svg';
import './App.css';
import { Routes, Route, Link } from "react-router-dom";
import Home from "./components/Home";
import Signup from "./components/Signup";
import ConfirmEmail from "./components/ConfirmEmail";
import { useState } from "react";
import ProtectedRoute from "./components/ProtectedRoute";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import IconButton from '@mui/material/IconButton';
import LightModeIcon from '@mui/icons-material/LightMode';
import DarkModeIcon from '@mui/icons-material/DarkMode';

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

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
    <>
    <IconButton onClick={toggleColorMode} color="inherit">
        {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
    </IconButton>
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Routes>
        <Route path="/" element={
          <ProtectedRoute user={user} redirectPath={"/login"}>
            <Home />
          </ProtectedRoute>
        }/>
        <Route path="signup" element={<Signup theme = {theme} />} />
        <Route path="confirmEmail" element={<ConfirmEmail theme = {theme} />} />
      </Routes>
      </ThemeProvider>
    </>
  );
}

export default App;
