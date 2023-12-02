import { Modal, Backdrop, Fade, CircularProgress } from "@mui/material"
import { useState } from "react";

export default function LoadingModal(message) {
    const [modal, setModal] = useState(false);

    return (
        <Modal
        open={modal}
        onClose={closeModal}
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
            <Fade in={modal}>
                <Box sx={styles.hiddenModalStyle}>
                <Typography color={'white'} margin={'2vh'} fontSize={20}>{message}...</Typography>
                <CircularProgress color="secondary" />
                </Box>
            </Fade>
        </Modal>
    );
}