package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
public class QuizController {

    private final List<Quiz> quizzes = new ArrayList<>();
    private int counter = 1;

    @PostMapping("/api/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz q) {
        //if (q.getTitle().isEmpty() || q.getText().isEmpty() || q.getOptions().length < 2) {
        //    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        //}
        Quiz newQuiz = new Quiz(counter++, q.getTitle(), q.getText(), q.getOptions(), q.getAnswer());
        quizzes.add(newQuiz);
        return newQuiz;
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        Quiz quiz = null;

        for (Quiz q : quizzes) {
            if (q.getId() == id) {
                quiz = q;
            }
        }

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return quiz;
    }

    @GetMapping("/api/quizzes")
    public List<?> getAllQuizzes() {
        if (quizzes.isEmpty()) {
            return List.of();
        }
        return quizzes;
    }

    @PostMapping(value = "/api/quizzes/{id}/solve")
    public QuizResult answerQuiz(@PathVariable int id, @RequestParam(required = false) Integer[] answer) {
        Quiz quiz = null;

        for (Quiz q : quizzes) {
            if (q.getId() == id) {
                quiz = q;
            }
        }

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (answer == null) {
            answer = new Integer[]{};
        }

        Set<Integer> userAnswers = new HashSet<>(Arrays.asList(answer));
        Set<Integer> quizAnswers = new HashSet<>(Arrays.asList(quiz.getAnswer()));

        if (userAnswers.equals(quizAnswers)) {
            return new QuizResult(true, "Congratulations, you're right!");
        } else {
            assert(!quizAnswers.isEmpty());
            return new QuizResult(false, "Wrong answer! Please, try again.");
        }
    }
}
