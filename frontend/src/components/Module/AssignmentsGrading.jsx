import { Card, CardActionArea, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import InfoField from "../Reusable/InfoField";

export default function AssignmentList ({assignmentsList}) {
    return(
        <Stack margin={'1.5vh 1.5vw'} padding={'1vh 1vw'} border={1} borderColor={'lightgray'} borderRadius={3}>
            {assignmentsList.map((assignment) => (
                <>
                <Assignment module={assignment} />
                <Divider sx={{margin: '1vw'}} />
                </>
          ))}
        </Stack>
    );
}

export default function Assignment ({assignment}) {

    const [grade, setGrade] = useState(assignment.grade);

    useEffect(() =>{
        assignment.grade = grade;
        try {
            //axios.post(`${globals.baseURL}/teacher/setGrade`, assignment, {WithCredentials: true});
        } catch (error) {
            console.log(error);
        }
    })

    return(
        <Card elevation={0}>
            <Typography variant="h6" fontSize={20}>{assignment.solutionURL}</Typography>
            <Typography variant="h6" fontSize={20}>{assignment.studentId}</Typography>
            <InfoField field={"Grade"} value={grade} setValue={setGrade}></InfoField>
        </Card>
    );
}