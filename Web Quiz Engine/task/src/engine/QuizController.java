package engine;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    private final List<Quiz> quizzes = new ArrayList<>();
    private int counter = 1;

    @PostMapping("/api/quizzes")
    public Quiz createQuiz(@RequestBody Quiz q) {
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

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public QuizResult answerQuiz(@PathVariable int id, @RequestParam int answer) {
        Quiz quiz = null;

        for (Quiz q : quizzes) {
            if (q.getId() == id) {
                quiz = q;
            }
        }

        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (answer == quiz.getAnswer()) {
            return new QuizResult(true, "Congratulations, you're right!");
        } else {
            return new QuizResult(false, "Wrong answer! Please, try again.");
        }
    }
}
