package engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class QuizController {

    private final Quiz quiz = new Quiz(
            "The Java Logo",
            "What is depicted on the Java logo?",
            new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"});

    @GetMapping("/api/quiz")
    public Quiz getQuiz() {
        return this.quiz;
    }

    @PostMapping("/api/quiz")
    public QuizResult answerQuiz(@RequestParam int answer) {
        if (answer == 2) {
            return new QuizResult(true, "Congratulations, you're right!");
        } else {
            return new QuizResult(false, "Wrong answer! Please, try again.");
        }
    }
}
