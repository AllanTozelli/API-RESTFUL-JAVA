package API.restful.TAREFAS.repository;

import API.restful.TAREFAS.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsById(int id);

    List<Task> findByDone(boolean done);


}
