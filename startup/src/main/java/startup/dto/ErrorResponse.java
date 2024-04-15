package startup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = ErrorResponse.Builder.class)
public class ErrorResponse {

    private final Boolean success;

    private final String message;

    public ErrorResponse(final Builder builder) {
        this.message = builder.message;
        this.success = false;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private String message;

        private Builder(){
            // Prevent Instantiation
        }

        public Builder withMessage(final String message) {
            this.message = message;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

}