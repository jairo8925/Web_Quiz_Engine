package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizRepository repo;

    @PostMapping
    public Quiz createQuiz(@Valid @RequestBody Quiz q) {
        Quiz newQuiz = new Quiz(q.getTitle(), q.getText(), q.getOptions(), q.getAnswer());
        // save quiz to repository
        repo.save(newQuiz);
        return newQuiz;
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        Quiz selectedQuiz = repo.findById(id);
        if (selectedQuiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
        }
        return selectedQuiz;
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = repo.findAll();
        if (quizzes == null) {
            return List.of();
        }
        return quizzes;
    }

    @PostMapping("/{id}/solve")
    public Result answerQuiz(@PathVariable int id, @RequestBody(required = false) Answer userAnswer) {
        // find the corresponding quiz using id given
        Quiz selectedQuiz = repo.findById(id);

        // check if specified quiz exists
        if (selectedQuiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
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
