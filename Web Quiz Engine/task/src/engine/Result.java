package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("feedback")
    private String feedback;

    public Result(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public Result() {}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
