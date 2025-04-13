package API.restful.TAREFAS.controller;

import API.restful.TAREFAS.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import API.restful.TAREFAS.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
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



}
