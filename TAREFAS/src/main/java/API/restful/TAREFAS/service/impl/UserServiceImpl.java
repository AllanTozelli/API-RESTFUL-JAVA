package API.restful.TAREFAS.service.impl;

import API.restful.TAREFAS.model.User;
import API.restful.TAREFAS.repository.UserRepository;
import org.springframework.stereotype.Service;
import API.restful.TAREFAS.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();  // Retorna todos os usuários do banco
    }

    @Override
    public User updateTask(Long id, String nameTask, String descriptionTask, boolean done) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada"));

        user.setDone(done);
        user.setNameTask(nameTask);
        user.setDescriptionTask(descriptionTask);
        return userRepository.save(user);
    }


    @Override
    public List<User> findTasksByDoneStatus(boolean done) {
        return userRepository.findByDone(done);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsById(userToCreate.getId())) {
            throw new IllegalArgumentException("Já existe uma conta com esse Id");

        }
        userToCreate.setDatainclusao(new Date());
        return userRepository.save(userToCreate);
    }
}
