package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Result {

    @JsonProperty("success")
    private boolean success;
    @JsonProperty("feedback")
    private String feedback;

}
