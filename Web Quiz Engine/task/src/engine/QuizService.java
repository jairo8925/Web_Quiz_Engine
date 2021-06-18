package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz create(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Quiz get(int id) {
        Quiz selectedQuiz = quizRepository.findById(id);
        if (selectedQuiz == null) {
            throw new QuizNotFoundException();
        }
        return selectedQuiz;
    }

    public List<Quiz> getAll(int page) {
        Pageable paging = PageRequest.of(page, 10);
        Page<Quiz> pagedResult = quizRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return List.of();
        }
    }

    public Result answer(int id, Answer userAnswer) {
        // find the corresponding quiz using id given
        Quiz selectedQuiz = quizRepository.findById(id);

        // check if specified quiz exists
        if (selectedQuiz == null) {
            throw new QuizNotFoundException();
        }

        // check if request provided answer
        userAnswer = userAnswer.getAnswer() != null ? userAnswer : new Answer(new Integer[]{});

        // get the correct quiz answer
        Answer quizAnswer = new Answer(selectedQuiz.getAnswer());

        // check if answers match
        if (userAnswer.equals(quizAnswer)) {
            return new Result(true, Result.CORRECT);
        } else {
            return new Result(false, Result.INCORRECT);
        }
    }

    public ResponseEntity<?> delete(int id, User user) {
        Quiz selectedQuiz = quizRepository.findById(id);

        if (selectedQuiz == null) {
            throw new QuizNotFoundException();
        }

        if (selectedQuiz.getUser().getId() != user.getId()) {
            throw new WrongAuthorException();
        }

        quizRepository.delete(selectedQuiz);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
