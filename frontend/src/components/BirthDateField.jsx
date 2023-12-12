import { Typography, Paper, Stack } from "@mui/material";
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import styles from "../utils/styles";
import dayjs from "dayjs";

export default function BirthDateField ({field, value, setValue}) {
    return (
        <Stack margin={'1vh'}>
            <Paper elevation={2}>
                <Stack direction={'row'} margin={'1vh'} width={'100%'}>
                    <Typography fontSize={20} margin={'1vh 1vh 0'} padding={'1vh 1vh 0'}>{field}</Typography>
                </Stack>
                <Stack>
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DatePicker
                                sx={styles.gridElement}
                                disableFuture
                                format="DD/MM/YYYY"
                                defaultValue={dayjs(value)}
                                value={dayjs(value)}
                                onChange={(newValue) => setValue(newValue)} />
                    </LocalizationProvider>
                </Stack>
            </Paper>
        </Stack>
    );
}