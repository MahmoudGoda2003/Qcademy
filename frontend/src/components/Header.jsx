import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import { Link } from 'react-router-dom';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import SearchIcon from '@mui/icons-material/Search';
import Stack from '@mui/material/Stack';


const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];


export default function Header({ userInfo, searchOptions }) {

  const [anchorElUser, setAnchorElUser] = React.useState(null);

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = (setting) => {
    // TODO: Add functionality to each setting in the menu
    
    setAnchorElUser(null);
  };
  
  return (
    <AppBar position="relative">
      <Container maxWidth="x1" sx={{margin:'auto 1vh'}}>
        <Toolbar disableGutters>
          <Link to="/Home">
            <img src={require("./LogoFullLight.png")}
              style={{ display: 'block', maxHeight: '10vh', maxWidth: '20vh', marginRight: '5vh' }}
              alt="Logo"
            ></img>
          </Link>
          <Autocomplete
            freeSolo
            size='small'
            sx={{ width: '20%', marginRight: '10vh', marginLeft: 'auto' }}
            options={searchOptions}
            renderInput={(params) => (
              <Stack direction="row" alignItems={"center"} spacing={1}>
                <SearchIcon />
                <TextField {...params} placeholder="Search…" variant="standard" sx={{ input: { color: 'white' } }} />
              </Stack>
            )}
          />

          <Box sx={{ flexGrow: 0, position: "relative", right: "1%" }}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar alt="userPhoto" src={userInfo.img} />
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
        </Toolbar>
      </Container>
    </AppBar>
  )
}
