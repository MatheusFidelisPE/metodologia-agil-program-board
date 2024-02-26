import {
  Box,
  Button,
  FormControl,
  FormHelperText,
  Grid,
  Input,
  InputLabel,
  Modal,
} from "@mui/material";
import React, { useState } from "react";

const style = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const TaskModal: React.FC<any> = (props) => {
  const [editedData, setEdited] = useState<any>({});
  const { data } = props;

  const handleEdit = (property: string) => (e: any) =>
    setEdited((prev: any) => ({
      ...(prev || {}),
      [property]: e?.target?.value,
    }));

  const handleSubmit = (event: any) => {
    event.preventDefault();
    alert(JSON.stringify(editedData));
  };

  return (
    <Modal {...props}>
      <Box sx={style}>
        {data && (
          <form autoComplete="off" onSubmit={handleSubmit}>
            <Grid container spacing={5}>
              <Grid item>
                <FormControl>
                  <InputLabel>Título</InputLabel>
                  <Input
                    defaultValue={data.titulo}
                    key={data.titulo}
                    onChange={handleEdit("titulo")}
                  />
                </FormControl>
              </Grid>
            </Grid>
            <FormControl className="mt-2">
              <Button type="submit">Salvar</Button>
            </FormControl>
          </form>
        )}
      </Box>
    </Modal>
  );
};

export default TaskModal;
