package engine;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Quiz> getAllQuizzes() {
        if (quizzes.isEmpty()) {
            return List.of();
        }
        return quizzes;
    }

    @PostMapping(value = "/api/quizzes/{id}/solve")
    public Result answerQuiz(@PathVariable int id, @RequestBody(required = false) Answer userAnswer) {
        Quiz selectedQuiz = null;

        // find the corresponding quiz using id given
        for (Quiz q : quizzes) {
            if (q.getId() == id) {
                selectedQuiz = q;
            }
        }

        // check if specified quiz exists
        if (selectedQuiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // check if request provided answer
        if (userAnswer.getAnswer() == null) {
            userAnswer.setAnswer(new Integer[]{});
        }

        Answer quizAnswer = new Answer(selectedQuiz.getAnswer());

        if (userAnswer.equals(quizAnswer)) {
            return new Result(true, "Congratulations, you're right!");
        } else {
            return new Result(false, "Wrong answer! Please, try again.");
        }
    }
}
