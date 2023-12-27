import { Modal } from "@mui/base";
import { Alert, Backdrop, Zoom } from "@mui/material";
import { Box } from "@mui/system";
import styles from "../../utils/styles";

export default function SuccessModal({ open, handleClose, message }) {
    return (
        <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-title"
        aria-describedby="modal-description"
        closeAfterTransition
        slots={{ backdrop: Backdrop }}
        style={{display:'flex',alignItems:'center', justifyContent: 'center'}}
        hideBackdrop
        slotProps={{
            backdrop: {
            timeout: 250,
            },
        }}
        >
            <Zoom in={open}>
                <Box sx={styles.hiddenModalStyleBottom}>
                    <Alert variant="filled" severity="success">{message}</Alert>
                </Box>
            </Zoom>
        </Modal>
    );
}