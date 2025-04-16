package API.restful.TAREFAS.repository;

import API.restful.TAREFAS.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Utilizada somente para o CRUD e consulta de persistencia de BD
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    boolean existsById(int id);

    //Assinatura do Metódo para verificar se a tarefa está finalizada ou não
    List<Task> findByDone(boolean done);


}
