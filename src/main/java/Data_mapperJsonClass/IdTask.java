package Data_mapperJsonClass;

import java.util.Objects;

public class IdTask {
    public IdTask(int id_task) {
        this.id_task = id_task;
    }
    public IdTask(){}

    private int id_task;

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdTask idTask = (IdTask) o;
        return id_task == idTask.id_task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_task);
    }
}
