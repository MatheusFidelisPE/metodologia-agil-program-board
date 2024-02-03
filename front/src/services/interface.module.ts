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
