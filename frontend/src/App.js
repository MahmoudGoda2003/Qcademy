import { Routes, Route, Navigate } from "react-router-dom";
import Home from "./components/MainPages/Home";
import Header from "./components/Reusable/Header";
import Profile from "./components/MainPages/Profile";
import Signup from "./components/Validation/Signup";
import ConfirmEmail from "./components/Validation/ConfirmEmail";
import Login from "./components/Validation/Login";
import { useState } from "react";
import ProtectedRoute from "./components/RouteGuards/ProtectedRoute";
import UnProtectedRoute from "./components/RouteGuards/UnProtectedRoute";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import IconButton from '@mui/material/IconButton';
import LightModeIcon from '@mui/icons-material/LightMode';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import CourseDetails from "./components/Course/CourseDetails";
import globals from "./utils/globals";
import CourseInfo from "./components/Course/CourseInfo";
import EditCourse from "./components/Course/EditCourse";


const lightMode = createTheme({
  palette: {
    primary: {
      main: '#3f51b5',
    },
    secondary: {
      main: '#00b0ff',
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
      main: '#00b0ff',
    },
  },
});

export default function App() {

  const [theme, setTheme] = useState(lightMode);
  const toggleColorMode = () => setTheme(((theme === lightMode)? darkMode : lightMode));

  //TODO: get user info from backend here again if null

  

  return (
    <>
      <ThemeProvider theme={theme}>
        <CssBaseline />
          <Routes>
            <Route path="/" element={ <Navigate to={'/home'}/>} />
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
            <Route path="/signup" element={
              <UnProtectedRoute redirectPath={"/home"}>
                <IconButton onClick={toggleColorMode} color="inherit">
                      {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
                </IconButton>
                <Signup theme = {theme} />
              </UnProtectedRoute>
            } />
            <Route path="/login" element={
              <UnProtectedRoute redirectPath={"/home"}>
                  <IconButton onClick={toggleColorMode} color="inherit">
                      {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
                  </IconButton>
                  <Login theme = {theme} />
              </UnProtectedRoute>
            } />
            <Route path="/confirmEmail" element={
              <UnProtectedRoute redirectPath={"/login"}>
                <IconButton onClick={toggleColorMode} color="inherit">
                      {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
                </IconButton>
                <ConfirmEmail theme = {theme} />
              </UnProtectedRoute>
            }/>
            <Route path="course/:courseId" element={
              <ProtectedRoute redirectPath={"/login"}>
                <Header onThemeChange={toggleColorMode} theme={theme} searchOptions={['1', '2', '3', '4']} />
                <CourseDetails />
              </ProtectedRoute>
            } />
            <Route path="course/manage/:courseId/" element={
              <ProtectedRoute redirectPath={"/login"}>
                <Header onThemeChange={toggleColorMode} theme={theme} searchOptions={['1', '2', '3', '4']} />
                <EditCourse />
              </ProtectedRoute>
            } />
            <Route path="course/learn/:courseId/" element={
              <ProtectedRoute redirectPath={"/login"}>
                <Header onThemeChange={toggleColorMode} theme={theme} searchOptions={['1', '2', '3', '4']} />
                <CourseInfo />
              </ProtectedRoute>
            } />
          </Routes>
      </ThemeProvider>
    </>
  );
}
