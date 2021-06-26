package engine.repository;

import engine.model.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletedQuizRepository extends PagingAndSortingRepository<CompletedQuiz, Integer> {

    CompletedQuiz findByEmail(String email);

    Page<CompletedQuiz> findAllByEmail(String email, Pageable pageable);

}
