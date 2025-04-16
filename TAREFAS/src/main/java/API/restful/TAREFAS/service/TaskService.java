package API.restful.TAREFAS.service;

import API.restful.TAREFAS.model.Task;

import java.util.List;


public interface TaskService {

    Task findById(Long id);

    List<Task> findTasksByDoneStatus(boolean done);

    Task create(Task taskToCreate);

    List<Task> findAll();

    Task updateTask(Long id, String nameTask, String descriptionTask, boolean done);

    Task deleteById(Long id);


}
