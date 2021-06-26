package engine.service;

import engine.exceptions.QuizNotFoundException;
import engine.exceptions.WrongAuthorException;
import engine.model.*;
import engine.repository.CompletedQuizRepository;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CompletedQuizRepository completedQuizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, CompletedQuizRepository completedQuizRepository) {
        this.quizRepository = quizRepository;
        this.completedQuizRepository = completedQuizRepository;
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

    public Map<String, ?> getAll(int page) {
        Pageable paging = PageRequest.of(page, 10);
        Page<Quiz> pagedResult = quizRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return Map.of("content", pagedResult.getContent());
        } else {
            return Map.of("content", List.of());
        }
    }

    public Result answer(int id, Answer userAnswer, User user) {
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
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date date = new Date();
            String completedAt = dateFormat.format(date);
            CompletedQuiz completedQuiz = new CompletedQuiz(selectedQuiz.getId(), user.getEmail(), completedAt);
            // add completed quiz to complete quizzes repo
            completedQuizRepository.save(completedQuiz);
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

    public Map<String, ?> getCompletedQuizzes(User user, int page) {
        Pageable paging = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("completedAt")));
        Page<CompletedQuiz> pagedResult = completedQuizRepository.findAllByEmail(user.getEmail(), paging);

        if (pagedResult.hasContent()) {
            return Map.of("content", pagedResult.getContent());
        } else {
            return Map.of("content", List.of());
        }
    }


}
