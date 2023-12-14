import { Modal } from "@mui/base";
import { Alert, Backdrop, Fade } from "@mui/material";
import { Box } from "@mui/system";
import styles from "../utils/styles";

export default function ErrorModal({ open, handleClose, message }) {
    return (
        <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-title"
        aria-describedby="modal-description"
        closeAfterTransition
        slots={{ backdrop: Backdrop }}
        slotProps={{
            backdrop: {
            timeout: 250,
            },
        }}
        >
            <Fade in={open}>
                <Box sx={styles.hiddenModalStyleTop}>
                    <Alert variant="filled" severity="error">{message}</Alert>
                </Box>
            </Fade>
        </Modal>
    );
}