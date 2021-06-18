package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository repo;

    @Autowired
    public QuizService(QuizRepository repo) {
        this.repo = repo;
    }

    public Quiz add(Quiz quiz) {
        return repo.save(quiz);
    }

    public Quiz get(int id) {
        Quiz selectedQuiz = repo.findById(id);
        if (selectedQuiz == null) {
            throw new QuizNotFoundException();
        }
        return selectedQuiz;
    }

    public List<Quiz> getQuizzes() {
        List<Quiz> quizzes = repo.findAll();
        if (quizzes == null) {
            // return empty list if there are no quizzes
            return List.of();
        }
        return quizzes;
    }

    public Result answer(int id, Answer userAnswer) {
        // find the corresponding quiz using id given
        Quiz selectedQuiz = repo.findById(id);

        // check if specified quiz exists
        if (selectedQuiz == null) {
            throw new QuizNotFoundException();
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
