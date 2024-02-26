interface Team {
  id: number;
  nome: string;
  features: Array<Feature>;
}

interface Iteration {
  id: number;
  dataInicio: string | null;
  dataFim: string | null;
}

interface Task {
  id: 1;
  featureId: 1;
  titulo: string;
  descricao: string;
  dev: string;
  deadLine: string;
  prioridade: string;
  status: string;
  endDate: string;
  notas: string;
}

interface Feature {
  idFeature: number;
  title: string;
  hypothesis: string;
  acceptanceCriteria: string;
  priority: number;
  valueDate: string;
  effort: number;
  idSprint: number;
  idTime: number;
}

interface TeamListResponse {
  id: number;
  nome: string;
}
