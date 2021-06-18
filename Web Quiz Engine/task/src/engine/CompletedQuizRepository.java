package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompletedQuizRepository extends PagingAndSortingRepository<CompletedQuiz, Integer> {

    CompletedQuiz findByEmail(String email);

    Page<CompletedQuiz> findAllByEmail(String email, Pageable pageable);

}
