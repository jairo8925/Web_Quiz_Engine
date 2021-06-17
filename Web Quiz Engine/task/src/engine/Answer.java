package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashSet;

public class Answer {

    @JsonProperty("answer")
    private Integer[] answer;

    public Answer(Integer[] answer) {
        this.answer = answer;
    }

    public Answer() {}

    public Integer[] getAnswer() {
        return answer;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer other = (Answer) o;
        return new HashSet<>(Arrays.asList(this.answer)).equals(new HashSet<>(Arrays.asList((other.answer))));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(answer);
    }
}
