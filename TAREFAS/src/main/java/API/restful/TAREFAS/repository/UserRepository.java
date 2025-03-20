package API.restful.TAREFAS.repository;

import API.restful.TAREFAS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(int id);
    
}
