package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;
    private final UserRepository userRepo;

    @Autowired
    public QuizController(QuizService quizService, UserRepository userRepo) {
        this.quizService = quizService;
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
    }

    @PostMapping("/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user) {
        quiz.setUser(user);
        return quizService.add(quiz);
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.get(id);
    }

    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getQuizzes();
    }

    @PostMapping("/quizzes/{id}/solve")
    public Result answerQuiz(@PathVariable int id, @RequestBody(required = false) Answer userAnswer) {
        return quizService.answer(id, userAnswer);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id, @AuthenticationPrincipal User user) {
        Quiz quiz = quizService.get(id);
        if (quiz == null) {
            throw new QuizNotFoundException();
        }
        if (quiz.getUser().getId() != user.getId()) {
            return new ResponseEntity<>("You are not the author of this quiz!", HttpStatus.FORBIDDEN);
        }
        quizService.remove(quiz);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
