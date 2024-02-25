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
  label: string;
  id: string;
  dependencies?: Array<Task["id"]>;
  iteration: Iteration["id"];
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
