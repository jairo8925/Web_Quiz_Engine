package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;

@Getter @Setter @AllArgsConstructor
public class Answer {

    @JsonProperty("answer")
    private Integer[] answer;

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
