package engine.controller;

import engine.service.QuizService;
import engine.repository.UserRepository;
import engine.exceptions.EmailAlreadyTakenException;
import engine.model.Answer;
import engine.model.Quiz;
import engine.model.Result;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;

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
    public Map<String, ?> getAllQuizzes(@RequestParam int page) {
        return quizService.getAll(page);
    }

    @PostMapping("/quizzes/{id}/solve")
    public Result answerQuiz(@PathVariable int id, @RequestBody(required = false) Answer userAnswer, @AuthenticationPrincipal User user) {
        return quizService.answer(id, userAnswer, user);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id, @AuthenticationPrincipal User user) {
        return quizService.delete(id, user);
    }

    @GetMapping("/quizzes/completed")
    public Map<String, ?> getCompletedQuizzes(@RequestParam int page, @AuthenticationPrincipal User user) {
        return quizService.getCompletedQuizzes(user, page);
    }
}
