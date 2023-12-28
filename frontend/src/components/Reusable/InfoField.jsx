import { IconButton, TextField, Typography, Paper, Stack } from "@mui/material";
import { useState } from "react";
import EditIcon from '@mui/icons-material/Edit';

export default function InfoField ({field, value, setValue}) {

    const [edit, setEdit] = useState(false);

    const changeValue = (e) => {
        setEdit(false);
        setValue(e.target.value);
    }

    return (
        <Stack margin={'1vh'}>
            <Paper elevation={2} sx={{padding: '1vh 1vw'}}>
                <Stack direction={'row'} margin={'1vh'} width={'100%'}>
                    <Typography fontSize={20} margin={'1vh 1vh 0'} padding={'1vh 1vh 0'}>{field}</Typography>
                    <IconButton size='small' aria-label="edit" sx={{margin: '1.8vh 0'}} onClick={() => setEdit(!edit)}>
                        <EditIcon style={{fontSize: '18px'}} />
                    </IconButton>
                </Stack>
                <Stack margin='1vh'>
                    {!edit ? 
                        <Typography fontSize={18} color="gray" margin={'1vh 0 1.4vh 2.5vh'} overflow={'hidden'}>{value? value : "You didn't tell us :("}</Typography>
                        :
                        <TextField
                            defaultValue={value}
                            size='small'
                            sx={{margin: '1vh 0 0'}}
                            onKeyUp={(e)=>{if (e.key === "Enter") {changeValue(e)}}}
                            onBlur={(e)=>{changeValue(e)}}
                        >
                        </TextField>
                    }
                </Stack>
            </Paper>
        </Stack>
    );
}