package engine.repository;

import engine.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

}
