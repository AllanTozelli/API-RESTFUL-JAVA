package API.restful.TAREFAS.service;

import API.restful.TAREFAS.model.User;

import java.util.List;


public interface UserService {

    User findById(Long id);

    List<User> findTasksByDoneStatus(boolean done);

    User create(User userToCreate);

    List<User> findAll();

    User updateTask(Long id, String nameTask, String descriptionTask, boolean done);

    User deleteById(Long id);


}
