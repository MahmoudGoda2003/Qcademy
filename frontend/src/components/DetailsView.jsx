import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Box from "@mui/material/Box";


const TextStyle = {fontFamily: 'Segoe Ui', fontWeight: 'lighter'};
const TextStyle2 = { color:'gray'};

export default function DetailsView({descriptionName , content}) {

    return (
        <Box sx={{ minWidth: '100%', maxWidth: '100%', margin: '2vh auto'}}>
            <Card sx={{minWidth:'flex', maxWidth:'flex', margin: '2vh auto'}}>
                <CardContent>
                    <Typography gutterBottom variant="h4" sx={TextStyle}>
                        {descriptionName}
                    </Typography>
                    <Typography variant="body" sx={TextStyle2}>
                        {content}
                    </Typography>

                </CardContent>
            </Card>
        </Box>
    );
}
