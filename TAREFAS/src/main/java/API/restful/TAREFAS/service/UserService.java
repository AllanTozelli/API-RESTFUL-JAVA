package API.restful.TAREFAS.service;

import API.restful.TAREFAS.model.User;

import java.util.List;


public interface UserService {

    User findById(Long id);

    User create(User userToCreate);

    List<User> findAll();
}
