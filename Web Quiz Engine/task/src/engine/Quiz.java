package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @JsonProperty("title")
    private String title;

    @NotBlank
    @JsonProperty("text")
    private String text;

    @NotNull
    @Size(min = 2)
    @JsonProperty("options")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer[] answer = new Integer[]{};

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public Integer[] getAnswer() {
        return answer;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
