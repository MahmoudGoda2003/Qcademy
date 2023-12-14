export default {

    gridStyle : {
        margin: '2vh',
        padding: '2vh',
        justifyContent: 'center',
        alignItems: 'center',
        minWidth: '25vw'
    },

    paperStyle : {
        height: 'fit',
        display: 'flex',
        maxWidth: '45vh',
        minWidth: '25vw',
        flexDirection: 'column',
        margin: '2vh auto',
        padding: '2vh',
    },

    innerGridStyle : {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center'
    },

    gridElement : {
        margin: '2vh 0vh auto',
        width: '90%'
    },

    gridElementText : {
        margin: '1vh auto',
        width: '90%'
    },

    gridElement2 : {
        display: 'flex',
        flexDirection: 'row',
        margin: '2vh 0vh auto',
        width: '90%'
    },

    modalStyle : {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        borderRadius: '2%',
        boxShadow: 24,
        p: 4,
    },

    modalStyle2 : {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: "35vw",
        bgcolor: 'background.paper',
        borderRadius: '2%',
        boxShadow: 24,
        p: 4,
    },

    hiddenModalStyle : {
        display: 'flex',
        flexDirection: 'column',
        position: 'absolute',
        alignItems: 'center',
        top: '35%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'transparent',
        border: 'none',
    },

    hiddenModalStyleTop : {
        display: 'flex',
        flexDirection: 'column',
        position: 'absolute',
        alignItems: 'center',
        top: '5%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'transparent',
        border: 'none',
    },

    VisuallyHiddenStyle : {
        clip: 'rect(0 0 0 0)',
        clipPath: 'inset(50%)',
        height: 1,
        overflow: 'hidden',
        position: 'absolute',
        bottom: 0,
        left: 0,
        whiteSpace: 'nowrap',
        width: 1,
    },
}