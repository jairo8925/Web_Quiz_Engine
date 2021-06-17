package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Quiz {

    @JsonProperty("id")
    private int id;

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
    private Integer[] answer;

    public Quiz() {}

    public Quiz(int id, String title, String text, String[] options, Integer[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.id = id;
        if (answer == null) {
            answer = new Integer[]{};
        }
        this.answer = answer;
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
