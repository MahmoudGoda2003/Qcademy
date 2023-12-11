import { Modal, Backdrop, Fade, CircularProgress, Box, Typography } from "@mui/material"
import styles from "../utils/styles";

export default function LoadingModal({ open, handleClose, message }) {

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
                <Box sx={styles.hiddenModalStyle}>
                <Typography color={'white'} margin={'2vh'} fontSize={20}>{message}...</Typography>
                <CircularProgress color="secondary" />
                </Box>
            </Fade>
        </Modal>
    );
}