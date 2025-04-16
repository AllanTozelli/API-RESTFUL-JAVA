package API.restful.TAREFAS.service.impl;

import API.restful.TAREFAS.model.Task;
import API.restful.TAREFAS.repository.TaskRepository;
import org.springframework.stereotype.Service;
import API.restful.TAREFAS.service.TaskService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// Responsavel pela Lógica de programação da aplicação
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();  // Retorna todos os usuários do banco
    }

    @Override
    public Task updateTask(Long id,  boolean done) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada"));

        task.setDone(done);
        return taskRepository.save(task);
    }


    //Implementação do Metodo da Interface, para retornar verdadeiro ou falso no "done"
    @Override
    public List<Task> findTasksByDoneStatus(boolean done) {
        return taskRepository.findByDone(done);
    }

    @Override
    public Task create(Task taskToCreate) {
        if (taskRepository.existsById(taskToCreate.getId())) {
            throw new IllegalArgumentException("Já existe uma conta com esse Id");

        }
        taskToCreate.setDatainclusao(new Date());
        return taskRepository.save(taskToCreate);
    }

    @Override
    public Task deleteById(Long id) {
        Optional<Task> userOptional = taskRepository.findById(id);
        if (userOptional.isPresent()) {
            Task task = userOptional.get();
            taskRepository.deleteById(id);
            return task;
        } else {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
    }

}
