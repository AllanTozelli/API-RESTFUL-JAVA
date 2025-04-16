package API.restful.TAREFAS.controller;

import API.restful.TAREFAS.model.Task;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import API.restful.TAREFAS.service.TaskService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name = "TASKS CONTROLLER", description = "Operações relacionadas a tarefas")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        var user = taskService.findById(id);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll(){
        List<Task> tasks = taskService.findAll(); // Chama o métod findAll() do seu UserService para obter todos os usuários
        return ResponseEntity.ok(tasks); // Retorna a lista de usuários com o status HTTP 200 (OK)
    }

    @GetMapping("/IsDone/{done}")
    public ResponseEntity<List<Task>> findTaksIsDone(@PathVariable boolean done){
       List<Task> tasks = taskService.findTasksByDoneStatus(done);
        return ResponseEntity.ok(tasks);
    }


    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody Task taskToCreate){
        var taskCreated = taskService.create(taskToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(taskCreated);
    }

    @PatchMapping("/Update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {

        Task updatedTaks = taskService.updateTask(id, updatedTask.getNameTask(), updatedTask.getDescriptionTask(),
                updatedTask.getDone());
        return ResponseEntity.ok(updatedTaks);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id){
        var task = taskService.deleteById(id);
        return  ResponseEntity.ok(task);
    }



}
