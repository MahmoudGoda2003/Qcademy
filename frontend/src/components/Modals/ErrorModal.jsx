import { Modal } from "@mui/base";
import { Alert, Backdrop, Grow } from "@mui/material";
import { Box } from "@mui/system";
import styles from "../../utils/styles";

export default function ErrorModal({ open, handleClose, message }) {
    return (
        <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-title"
        aria-describedby="modal-description"
        closeAfterTransition
        hideBackdrop
        slots={{ backdrop: Backdrop }}
        slotProps={{
            backdrop: {
            timeout: 250,
            },
        }}
        >
            <Grow in={open}>
                <Box sx={styles.hiddenModalStyleBottom}>
                    <Alert variant="filled" severity="error">{message}</Alert>
                </Box>
            </Grow>
        </Modal>
    );
}