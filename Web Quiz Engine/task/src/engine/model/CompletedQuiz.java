package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class CompletedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int uniqueId;

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private int quizId;

    @JsonIgnore
    private String email;

    @JsonProperty(value = "completedAt", access = JsonProperty.Access.READ_ONLY)
    private String completedAt;

    protected CompletedQuiz() {}

    public CompletedQuiz(int quizId, String email, String completedAt) {
        this.quizId = quizId;
        this.email = email;
        this.completedAt = completedAt;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int id) {
        this.quizId = id;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
