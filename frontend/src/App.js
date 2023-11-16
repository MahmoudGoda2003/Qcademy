import { Routes, Route, Link } from "react-router-dom";
import { useState } from "react";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import IconButton from '@mui/material/IconButton';
import Home from "./components/Home";

import Header from "./components/Header";

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

  return (
    <>
      <ThemeProvider theme={theme} >
          <CssBaseline />
          <Routes>
            <Route path="/" element={<Home onThemeChange={toggleColorMode} theme={theme}/>} />
          </Routes>
      </ThemeProvider>
    </>
  );
}
