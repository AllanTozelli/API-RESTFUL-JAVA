package API.restful.TAREFAS.controller;

import API.restful.TAREFAS.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import API.restful.TAREFAS.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@Tag(name = "TASKS CONTROLLER", description = "Operações relacionadas a tarefas")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll(); // Chama o métod findAll() do seu UserService para obter todos os usuários
        return ResponseEntity.ok(users); // Retorna a lista de usuários com o status HTTP 200 (OK)
    }

    @GetMapping("/taksIsDone/{done}")
    public ResponseEntity<List<User>> findTaksIsDone(@PathVariable boolean done){
       List<User> users = userService.findTasksByDoneStatus(done);
        return ResponseEntity.ok(users);
    }


    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate){
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(userCreated);
    }

    @PatchMapping("/taksUpdate/{id}")
    public ResponseEntity<User> updateTask(@PathVariable Long id, @RequestBody User updatedTask) {

        User updatedUser = userService.updateTask(id, updatedTask.getNameTask(), updatedTask.getDescriptionTask(),
                updatedTask.getDone());
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        var user = userService.deleteById(id);
        return  ResponseEntity.ok(user);
    }



}
