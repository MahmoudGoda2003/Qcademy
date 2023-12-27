import { Button } from "@mui/material";
import useDrivePicker from "react-google-drive-picker"
import globals from "../../utils/globals";
import { useEffect } from "react";

export default function GooglePicker () {

    const [openPicker, data, responce] = useDrivePicker();

    const handleOpen = () => {
        openPicker({
            clientId: globals.REACT_APP_CLIENT_ID,
            developerKey: globals.REACT_APP_DEV_KEY,
            token: "ya29.a0AfB_byByHBhC25Dd6kCobcrbT9fYJmk0GJO_ClsqegxP08vFcSwqHwUGrnCbAF3wnNfjXd1WNkJrqqOJa24WDQp6nzMJ3nCpEzwQCZnoyMyjQIpz7yjeSTZZ488gdPmhxx9sMx-dCKOVSSaU4xF61VV07cLLlPKw2AaCgYKAZUSARISFQHGX2Mi22tzyWzTHqqQanNv728jhQ0169",
            viewId: "DOCS",
            showUploadFolders: true,
            showUploadView: true,
            supportDrives: true,
            multiselect: true,
        });
    }

    useEffect(() => {
        if (data) {
            console.log(data);
        }
    }, [data]);

    return (
        <>
        <Button onClick={handleOpen} variant="contained">Open drive</Button>
        </>
    );
}