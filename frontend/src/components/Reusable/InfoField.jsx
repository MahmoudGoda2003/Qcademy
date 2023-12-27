import { IconButton, TextField, Typography, Paper, Stack } from "@mui/material";
import { useState } from "react";
import EditIcon from '@mui/icons-material/Edit';
import { isInteger } from "mathjs";

export default function InfoField ({field, value, setValue}) {

    const [edit, setEdit] = useState(false);

    const changeValue = (e) => {
        setEdit(false);
        if (field==='Phone Number'&&(isNaN(e.target.value)||!isInteger(e.target.value))) alert("Please enter a valid number");
        else setValue(e.target.value);;
    }

    return (
        <Stack margin={'1vh'}>
            <Paper elevation={2}>
                <Stack direction={'row'} margin={'1vh'} width={'100%'}>
                    <Typography fontSize={20} margin={'1vh 1vh 0'} padding={'1vh 1vh 0'}>{field}</Typography>
                    <IconButton size="small" aria-label="edit" sx={{margin: '2vh 0'}} onClick={() => setEdit(!edit)}>
                        <EditIcon />
                    </IconButton>
                </Stack>
                <Stack width="15vw" margin='1vh'>
                    {!edit ? 
                        <Typography fontSize={18} color="gray" margin={'1vh 0 1.4vh 2.5vh'} overflow={'hidden'}>{value? value : "You didn't tell us :^("}</Typography>
                        :
                        <TextField
                            defaultValue={value}
                            size='small'
                            sx={{
                                margin: '1vh',
                                marginTop: 0
                            }}
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