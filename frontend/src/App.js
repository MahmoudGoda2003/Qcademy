import { Routes, Route, Link } from "react-router-dom";
import { useState } from "react";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import IconButton from '@mui/material/IconButton';
import Home from "./components/Home";
import InfoField from "./components/InfoField";
import Header from "./components/Header";
import Profile from "./components/Profile";

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

  const [theme, setTheme] = useState(lightMode);
  const toggleColorMode = () => setTheme(((theme === lightMode)? darkMode : lightMode));

  const [name, setName] = useState('CIARa');
  const [ymca, setYmca] = useState('it is fun to stay at');

  return (
    <>
      <ThemeProvider theme={theme} >
          <CssBaseline />
          <Header onThemeChange={toggleColorMode} theme={theme} userInfo={{img:"as"}} searchOptions={['1', '2', '3', '4']} />
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/home" element={<Profile></Profile>} />
          </Routes>
      </ThemeProvider>
    </>
  );
}
