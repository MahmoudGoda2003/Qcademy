import { Cancel, Tag } from "@mui/icons-material";
import { Chip, FormControl, Grid, Stack, TextField, Typography } from "@mui/material";
import { Box } from "@mui/system";
import { useRef, useState } from "react";

const Tags = ({ data, handleDelete }) => {
  return (
    <Chip
      sx={{
        padding: "0.2vh",
        margin: "0.6vh 0.4vh 0",
      }}
      label={data}
      onDelete={() => {handleDelete(data)}}
    />
  );
};

export default function InputTags() {
  const [tags, SetTags] = useState([]);
  const tagRef = useRef();

  const handleDelete = (value) => {
    const newtags = tags.filter((val) => val !== value);
    SetTags(newtags);
  };
  const handleOnSubmit = (e) => {
    e.preventDefault();
    if (tags.includes(tagRef.current.value)) return;
    SetTags([...tags, tagRef.current.value]);
    tagRef.current.value = "";
  };
  return (
    <Box sx={{ flexGrow: 1 }}>
        <Grid sx={{ margin: "0 0.2vh 0 0" }}>
                {tags.map((data, index) => {
                  return (
                    <Tags data={data} handleDelete={handleDelete} key={index} />
                  );
                })}
        </Grid>
      <form onSubmit={handleOnSubmit}>
        <TextField
          inputRef={tagRef}
          fullWidth
          variant='outlined'
          margin="normal"
          label="Tags"
        />
      </form>
    </Box>
  );
}
