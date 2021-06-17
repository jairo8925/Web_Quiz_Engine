package engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    private static final Logger log =
            LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(QuizRepository repo) {
        return (args) -> {
            repo.save(new Quiz(1, "Title", "Text", new String[]{"Option 1", "Option 2"}, new Integer[]{1}));
            repo.save(new Quiz(2, "Rawr", "Text", new String[]{"Option 1", "Option 2"}, new Integer[]{0, 1}));

            log.info("Quizzes found with findAll():");
            log.info("-------------------------------");
            for (Quiz quiz : repo.findAll()) {
                log.info(quiz.toString());
            }
            log.info("");

            Quiz quiz = repo.findById(2);
            log.info("Quiz found with findById(1):");
            log.info("--------------------------------");
            log.info(quiz.toString());
            log.info("");
        };
    }
}
