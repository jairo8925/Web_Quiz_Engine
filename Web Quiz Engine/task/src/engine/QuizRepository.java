package engine;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface QuizRepository extends CrudRepository<Quiz, Integer> {

    Quiz findById(int id);

    List<Quiz> findAll();
}
