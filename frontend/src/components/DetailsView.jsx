import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import CourseDetailsCard from "./CourseDetailsCard";
import Box from "@mui/material/Box";


const TextStyle = {fontFamily: 'Segoe Ui', fontWeight: 'lighter'};
const TextStyle2 = { color:'gray'};

const gridElement = {
    margin: '2vh auto',
    width: '100%'
}
export default function DetailsView({descriptionName , content}) {

    return (
        <Box sx={{ minWidth: '100%', maxWidth: '100%', margin: '2vh auto'}}>
            <Card sx={{minWidth:'flex', maxWidth:'flex', margin: '2vh auto'}}>
                <CardContent>
                    <Typography gutterBottom variant="h3" component="div" sx={TextStyle}>
                        {descriptionName}
                    </Typography>
                    <Typography variant="body" color="text.secondary" sx={TextStyle2}>
                        {content}
                    </Typography>

                </CardContent>
            </Card>
        </Box>
    );
}
