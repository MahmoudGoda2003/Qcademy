import { Routes, Route, Link } from "react-router-dom";
import Home from "./components/Home";
import Header from "./components/Header";
import Profile from "./components/Profile";
import TeacherHome from "./components/TeacherHome";
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
import globals from "./utils/globals";


const lightMode = createTheme({
  palette: {
    primary: {
      main: '#3f51b5',
    },
    secondary: {
      main: '#3d5afe',
    },
  },
});

const darkMode = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#3f51b5',
    },
    secondary: {
      main: '#3d5afe',
    },
  },
});

export default function App() {
  const [user, setUser] = useState(null);

  const [route, setRoute] = useState('/')

  const [theme, setTheme] = useState(lightMode);
  const toggleColorMode = () => setTheme(((theme === lightMode)? darkMode : lightMode));

  //TODO: get user info from backend here again if null

  return (
    <>
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Routes>
        <Route path="/home" element={
          <ProtectedRoute redirectPath={"/login"}>
            <Header onThemeChange={toggleColorMode} theme={theme} searchOptions={['1', '2', '3', '4']} />
            <Home />
          </ProtectedRoute>
        }/>
        <Route path="/profile" element={
          <ProtectedRoute redirectPath={"/login"}>
            <Header onThemeChange={toggleColorMode} theme={theme} searchOptions={['1', '2', '3', '4']} />
            <Profile />
          </ProtectedRoute>
        }/>
        <Route path="signup" element={
          <>
            <IconButton onClick={toggleColorMode} color="inherit">
                  {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
            </IconButton>
            <Signup theme = {theme} />
          </>
        } />
        <Route path="testing" element={
          <>
            <Header onThemeChange={toggleColorMode} theme={theme} searchOptions={['1', '2', '3', '4']} />
            <Home />
          </>
        } />
        <Route path="/confirmEmail" element={
          <ProtectedRoute redirectPath={"/login"}>
            <IconButton onClick={toggleColorMode} color="inherit">
                  {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
            </IconButton>
            <ConfirmEmail theme = {theme} />
          </ProtectedRoute>
        }/>
        <Route path="login" element={
          <>
              <IconButton onClick={toggleColorMode} color="inherit">
                  {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
              </IconButton>
              <Login theme = {theme} />
          </>
        } />
      </Routes>
      </ThemeProvider>
    </>
  );
}
