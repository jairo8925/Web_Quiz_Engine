package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final QuizService quizService;
    private final UserRepository userRepository;

    @Autowired
    public Controller(QuizService quizService, UserRepository userRepository) {
        this.quizService = quizService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody User newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailAlreadyTakenException();
        }
        // encodes the user's password
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    @PostMapping("/quizzes")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user) {
        // set the author of quiz
        quiz.setUser(user);
        return quizService.create(quiz);
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.get(id);
    }

    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes(@RequestParam int page) {
        return quizService.getAll(page);
    }

    @PostMapping("/quizzes/{id}/solve")
    public Result answerQuiz(@PathVariable int id, @RequestBody(required = false) Answer userAnswer) {
        return quizService.answer(id, userAnswer);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id, @AuthenticationPrincipal User user) {
        return quizService.delete(id, user);
    }

    @GetMapping("/quizzes/completed")
    public void getCompletedQuizzes(@AuthenticationPrincipal User user) {

    }
}
