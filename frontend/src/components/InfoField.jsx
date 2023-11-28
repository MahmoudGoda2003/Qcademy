import { IconButton, TextField, Typography, Paper, Stack } from "@mui/material";
import { useState } from "react";
import EditIcon from '@mui/icons-material/Edit';

export default function InfoField ({field, value, setValue}) {

    const [edit, setEdit] = useState(false);

    const changeValue = (e) => {
        setValue(e.target.value);
    }

    return (
        <Stack margin={'1vh'} padding={'1vh'} >
            <Paper elevation={2}>
                <Stack direction={'row'} margin={'0 1vh'} padding={'1vh'}>
                    <Typography fontSize={20} textAlign="center">{field}</Typography>
                    <IconButton size="small" aria-label="edit" onClick={() => setEdit(!edit)}>
                        <EditIcon />
                    </IconButton>
                </Stack>
                <Stack width="15vw" height="8vh" margin='1vh'>
                    {!edit ? 
                        <Typography fontSize={16} color="gray" margin={'1vh'} padding={'1vh'}>{value}</Typography>
                        :
                        <TextField
                            onChange={changeValue}
                            defaultValue={value}
                            size='small'
                        >
                        </TextField>
                    }
                </Stack>
            </Paper>
        </Stack>
    );
}