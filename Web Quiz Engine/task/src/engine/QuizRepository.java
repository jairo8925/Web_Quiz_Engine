package engine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {

    Quiz findById(int id);

    List<Quiz> findAll();
}
