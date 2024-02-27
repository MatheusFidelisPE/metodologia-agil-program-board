import api from "@/services/api";
import {
  Box,
  Button,
  FormControl,
  FormHelperText,
  Grid,
  Input,
  InputLabel,
  MenuItem,
  Modal,
  OutlinedInput,
  Select,
} from "@mui/material";
import { differenceWith, isEqual, last } from "lodash";
import React, { useEffect, useState } from "react";

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

const FeatureModal: React.FC<any> = (props) => {
  const [editedData, setEdited] = useState<any>({});
  const [dependencies, setDependencies] = useState<Array<Dependency>>({});
  const { data, features = [], refresh, getAllDependencies } = props;

  useEffect(() => {
    setDependencies(
      (props.dependencies || [])?.filter(
        (x: Dependency) => data && x && x.idIndependente === data?.idFeature
      )
    );
  }, [JSON.stringify(props.dependencies)]);

  const handleEdit = (property: string) => (e: any) =>
    setEdited((prev: any) => ({
      ...(prev || {}),
      [property]: e?.target?.value,
    }));

  const handleSubmit = async (event: any) => {
    event.preventDefault();
    const body = {
      ...data,
      ...editedData,
    };

    if (!!body.idFeature) {
      await api.put("/feature/update-feature", body).then(() => {
        refresh();
        props.onClose();
      });
    }
  };

  const handleDependency = async (e: any) => {
    console.log(e.target.value)
    Array(e.target.value).map(async (dependencyId) => {
      if (!dependencies.find((x) => x.idDependente === dependencyId)) {
        await api.post(
          `/feature/criar-dependencia/${data.idFeature}/${dependencyId}`
        );
      }
    });
    
    await getAllDependencies();

    console.log('dependencies')
    dependencies?.map(async (x) => {
      if (!Array(e.target.value).includes(x.idDependente)) {
        await api.delete(
          `/feature/deletar-dependencia/${data.idFeature}/${x.idDependente}`
        );
      }
    });
  };

  return (
    <Modal {...props}>
      <Box sx={style}>
        {data && (
          <form autoComplete="off" onSubmit={handleSubmit}>
            <Grid container spacing={5}>
              <Grid item sm={6}>
                <FormControl>
                  <InputLabel>Título</InputLabel>
                  <OutlinedInput
                    defaultValue={data.title}
                    key={data.title}
                    onChange={handleEdit("title")}
                    label="Título"
                  />
                </FormControl>
              </Grid>
              <Grid item sm={6}>
                <FormControl>
                  <InputLabel>Hypothesis</InputLabel>
                  <OutlinedInput
                    defaultValue={data.hypothesis}
                    key={data.hypothesis}
                    label="Hypothesis"
                    onChange={handleEdit("hypothesis")}
                  />
                </FormControl>
              </Grid>
              <Grid item sm={6}>
                <FormControl>
                  <InputLabel>Critério de Aceitação</InputLabel>
                  <OutlinedInput
                    defaultValue={data.acceptanceCriteria}
                    key={data.acceptanceCriteria}
                    onChange={handleEdit("acceptanceCriteria")}
                    label="Critério de Aceitação"
                  />
                </FormControl>
              </Grid>
              <Grid item sm={6}>
                <FormControl>
                  <InputLabel>Esforço</InputLabel>
                  <OutlinedInput
                    defaultValue={data.effort}
                    key={data.effort}
                    onChange={handleEdit("effort")}
                    label="Esforço"
                  />
                </FormControl>
              </Grid>
              {JSON.stringify(props.dependencies)}
              {JSON.stringify({ f: data?.idFeature })}
              <Grid item sm={6}>
                <FormControl sx={{ width: "100%" }}>
                  <InputLabel id="demo-multiple-name-label">
                    Dependências
                  </InputLabel>
                  <Select
                    labelId="demo-multiple-checkbox-label"
                    id="demo-multiple-checkbox"
                    multiple
                    value={dependencies?.map((x) => x.idDependente)}
                    onChange={handleDependency}
                    renderValue={(selected) => selected.join(", ")}
                    input={<OutlinedInput label="Name" />}
                  >
                    {features.map((feature: Feature) => (
                      <MenuItem
                        key={feature.idFeature}
                        value={feature.idFeature}
                      >
                        {`F-${feature.idFeature} ${feature.title}`}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Grid>
            </Grid>
            <FormControl className="mt-4">
              <Button type="submit">Salvar</Button>
            </FormControl>
          </form>
        )}
      </Box>
    </Modal>
  );
};

export default FeatureModal;
