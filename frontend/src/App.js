import { Routes, Route, Link } from "react-router-dom";
import { useState } from "react";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Home from "./components/Home";
import Header from "./components/Header";
import Profile from "./components/Profile";
import TeacherHome from "./components/TeacherHome";

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

  return (
    <>
      <ThemeProvider theme={theme} >
          <CssBaseline />
          <Header onThemeChange={toggleColorMode} theme={theme} userInfo={{img:"as"}} searchOptions={['1', '2', '3', '4']} />
          <Routes>
            <Route path="/home" element={<Home/>} />
            <Route path="/teacher" element={<TeacherHome/>} />
            <Route path="/profile" element={<Profile></Profile>} />
          </Routes>
      </ThemeProvider>
    </>
  );
}
