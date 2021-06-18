package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Entity
@Getter @Setter
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
    private Integer[] answer = new Integer[]{};

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
}
