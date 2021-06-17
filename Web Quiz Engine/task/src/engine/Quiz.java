package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Entity
public class Quiz {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @JsonProperty("id")
    private int id;

    @NotBlank
    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @NotBlank
    @Column(name = "text")
    @JsonProperty("text")
    private String text;

    @NotNull
    @Size(min = 2)
    @Column(name = "options")
    @JsonProperty("options")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "answer")
    private Integer[] answer;

    protected Quiz() {}

    public Quiz(String title, String text, String[] options, Integer[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        if (answer == null) {
            answer = new Integer[]{};
        }
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, title='%s', text='%s', options='%s', answer='%s']",
                id, title, text, Arrays.toString(options), Arrays.toString(answer));
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String[] getOptions() {
        return options;
    }

    public int getId() {
        return id;
    }

    public Integer[] getAnswer() {
        return answer;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setId(int id) {
        this.id = id;
    }
}
