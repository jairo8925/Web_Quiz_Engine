package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }

    @PostMapping
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        return service.add(quiz);
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return service.getQuizzes();
    }

    @PostMapping("/{id}/solve")
    public Result answerQuiz(@PathVariable int id, @RequestBody(required = false) Answer userAnswer) {
        return service.answer(id, userAnswer);
    }
}
