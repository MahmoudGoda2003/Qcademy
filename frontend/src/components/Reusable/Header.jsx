import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import Avatar from '@mui/material/Avatar';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import { Link } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import SearchIcon from '@mui/icons-material/Search';
import Stack from '@mui/material/Stack';
import LightModeIcon from '@mui/icons-material/LightMode';
import DarkModeIcon from '@mui/icons-material/DarkMode';
import { useNavigate } from "react-router-dom";
import globals from '../../utils/globals';
import UserService from '../../service/UserService';
import { useEffect } from 'react';
import CourseService from '../../service/CourseService';
import { useState } from 'react';

const settings = ['Home', 'Profile', 'Settings', 'Logout'];



export default function Header({ onThemeChange, theme }) {

  const [anchorElUser, setAnchorElUser] = useState(null);
  const [courseNames, setCourseNames] = useState([]);
  const [courses, setCourses] = useState([])
  const navigate = useNavigate();

  useEffect(() => {
    const getCourseNames = async () => {
      if (globals.user !== undefined) {
        let results = [];
        if (globals.user.role === "STUDENT") {
          let recommended = await CourseService.getRecommendedCourses();
          let enrolled = await CourseService.getEnrolledCourses();
          results = results.concat(recommended)
          results = results.concat(enrolled)
        }
        else if (globals.user.role === "TEACHER") {
          let created = await CourseService.getCreatedCourses();
          results = results.concat(created)
          console.log(results)
        }
        else return;
        const names = results.map((course) => course.name)
        setCourses(results)
        setCourseNames(names)
      }
    }

    getCourseNames();

  }, [])

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

 
  const handleCloseUserMenu = async (setting) => {
    setAnchorElUser(null);
    switch(setting){
      case "Home":
        navigate(`/home`)
        break
      case "Profile":
        navigate(`/profile`)
        break
      case "Logout":
        globals.user = null;
        localStorage.removeItem("user")
        await UserService.logOut();
        navigate(`/login`)
        break
      default:
    }
  };

  const search = (e) => {
    if (e.key === "Enter"){
      const results = courses.filter((course) => {
        return course.name.toLowerCase().includes(e.target.value.toLowerCase());
      })
      navigate('/search', {state: {results: results}})
    }
  }

  return (
    <AppBar position="relative" width={'100%'}>
      <Stack direction={'row'} padding='1vh' margin='1vh' color='white' alignItems={"center"}>
          <Link to="/home">
            <img src={require("../../img/LogoFullLight.png")}
              style={{  maxHeight: '10vh', maxWidth: '20vh' }}
              alt="Logo"
            ></img>
          </Link>

          <Autocomplete
            freeSolo
            size='small'
            sx={{ width: '20%', marginRight: '2vh', marginLeft: 'auto' }}
            options={courseNames}
            renderInput={(params) => (
              <Stack direction="row" alignItems={"center"} spacing={1}>
                <SearchIcon />
                <TextField {...params} placeholder="Search…" variant="standard" sx={{ input: { color: 'white' } }} onKeyUp={search} />
              </Stack>
            )}
          />

          <IconButton onClick={onThemeChange} sx={{ margin: '0 1vw' }} color="inherit">
            {theme.palette.mode === 'dark' ? <DarkModeIcon /> : <LightModeIcon />}
          </IconButton>

          <Box justifyContent={'flex-end'}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                {globals.user && <Avatar alt="userPhoto" src={globals.user.photoLink} referrerPolicy="no-referrer" />}
              </IconButton>
            </Tooltip>
            <Menu
              sx={{ mt: '45px' }}
              id="menu-appbar"    
              anchorEl={anchorElUser}
              anchorOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {settings.map((setting) => (
                <MenuItem key={setting} onClick={() => handleCloseUserMenu(setting)}>
                  <Typography textAlign="center">{setting}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
      </Stack>
    </AppBar>
  )
}
