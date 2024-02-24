interface Team {
  id: string;
  name: string;
  iterations?: Array<Iteration>;
  tasks: Array<Task>;
}

interface Iteration {
  id: string;
  tasks: Array<Task["id"]>;
  start_at?: string;
  end_at?: string;
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
