import { Typography, Paper, Stack, IconButton } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { useState } from "react";

export default function DateField ({field, value, setValue}) {

    const [edit, setEdit] = useState(false);

    const changeValue = () => {
        setEdit(false);
    }

    return (
        <Stack margin={'1vh'}>
            <Paper elevation={2} sx={{padding: '1vh 1vw'}}>
                <Stack direction={'row'} margin={'1vh'} width={'100%'}>
                    <Typography fontSize={20} margin={'1vh 1vh 0'} padding={'1vh 1vh 0'}>{field}</Typography>
                    <IconButton size="small" aria-label="edit" sx={{margin: '2vh 0'}} onClick={() => setEdit(!edit)}>
                        <EditIcon style={{fontSize: '18px'}} />
                    </IconButton>
                </Stack>
                <Stack width="15vw" margin='1vh'>
                    {!edit ? 
                        <Typography fontSize={18} color="gray" margin={'1vh 0 1.4vh 2.5vh'} overflow={'hidden'}>{value? (value.$D ? value.$D + "-" + value.$M + "-" + value.$y : value) : "You didn't tell us :^("}</Typography>
                        :
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DatePicker
                                disableFuture
                                format="DD/MM/YYYY"
                                label={field}
                                onClose={changeValue}
                                onChange={(newValue) => setValue(newValue)}
                            />
                        </LocalizationProvider>
                    }
                </Stack>
            </Paper>
        </Stack>
    );
}