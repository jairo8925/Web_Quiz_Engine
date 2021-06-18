package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

    public static final String CORRECT = "Congratulations, you're right!";
    public static final String INCORRECT = "Wrong answer! Please, try again.";

    @JsonProperty("success")
    private boolean success;
    @JsonProperty("feedback")
    private String feedback;

    public Result(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
