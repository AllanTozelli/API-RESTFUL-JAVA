package API.restful.TAREFAS.controller;

import API.restful.TAREFAS.model.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import API.restful.TAREFAS.service.TaskService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// Responsavel pelos EndPoints da API
@RestController
@RequestMapping("/task")
@Tag(name = "TASKS CONTROLLER", description = "Operações relacionadas a tarefas")
public class TaskController {

    // Instanciação da classe TaskService
    private final TaskService taskService;

   // Construtor da Classe TaskService
   public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Consultar Tarefa pelo Id",
            description = "Endpoint para consultar a tarefa pelo Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){ // Verificar Task pelo Id passado na URL
        var user = taskService.findById(id);
        return  ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Consultar todas s tarefas",
            description = "Endpoint para consultar todas as tarefa"
    )
    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll(){
        List<Task> tasks = taskService.findAll(); // Chama o métod findAll() do seu UserService para obter todos os usuários
        return ResponseEntity.ok(tasks); // Retorna a lista de usuários com o status HTTP 200 (OK)
    }

    @Operation(
            summary = "Verificar Tarefas",
            description = "Endpoint para verificar o status das tarefas"
    )

    @GetMapping("/isDone")
    public ResponseEntity<List<Task>> findTaksIsDone(@RequestParam boolean done){ // Verificação de Tasks Ativas e encerradas
       List<Task> tasks = taskService.findTasksByDoneStatus(done);
        return ResponseEntity.ok(tasks);
    }


    @Operation(
            summary = "Criar uma nova tarefa",
            description = "Endpoint para criar uma nova tarefa com os detalhes fornecidos",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da tarefa a ser criada",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Task.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de criação",
                                    value = "{\n  \"nameTask\": \"Finalizar relatório semanal\",\n  \"descriptionTask\": \"Relatório das atividades da equipe até sexta-feira\",\n  \"dataFinal\": \"16/04/2025 23:59\",\n  \"done\": false\n}"
                            )
                    )
            )
    )

    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody Task taskToCreate){ //Converter o Json em um objeto da classe Task
        var taskCreated = taskService.create(taskToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()// Captura a URL da requisição
                .path("/{id}")
                .buildAndExpand(taskCreated.getId())
                .toUri(); // Transforma em URI

        return ResponseEntity.created(location).body(taskCreated); // Retorno da Tarefa criada
    }

    @Operation(
            summary = "Alterar Status da Tarefa",
            description = "Endpoint para alterar o status da tarefa",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Alteração Status Da Tarefa",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Task.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de criação",
                                    value = "{\"done\": true\n}"
                            )
                    )
            )
    )
    @PatchMapping("/UpdateStatus/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) { //Atl campo Done, pelo ID passado na URL

        Task updatedTasks = taskService.updateTask(id, updatedTask.getDone()); // Transformando o Json em Objeto que quero dar update
        return ResponseEntity.ok(updatedTasks); // retornando o update
    }


    @Operation(
            summary = "Deletar Tarefa",
            description = "Endpoint para deletar a tarefa"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id){ //Delete por ID na URL
        var task = taskService.deleteById(id);
        return  ResponseEntity.ok(task);
    }



}
