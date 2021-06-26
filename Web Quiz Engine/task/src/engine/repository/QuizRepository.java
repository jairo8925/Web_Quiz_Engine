package engine.repository;

import engine.model.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {

    Quiz findById(int id);

    List<Quiz> findAllById(int id);
}
