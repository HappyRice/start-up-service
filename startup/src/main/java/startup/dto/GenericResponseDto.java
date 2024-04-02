package startup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import startup.common.dto.GameDto;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = GenericResponseDto.Builder.class)
public class GenericResponseDto {

    private final Boolean success;

    private final String identifier;

    private final String message;

    public GenericResponseDto(final Builder builder) {
        this.success = builder.success;
        this.identifier = builder.identifier;
        this.message = builder.message;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getIdentifier() {
        return this.identifier;
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
        private Boolean success;
        private String identifier;
        private String message;

        private Builder(){
            // Prevent Instantiation
        }

        public Builder withSuccess(final Boolean success) {
            this.success = success;
            return this;
        }

        public Builder withIdentifier(final String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder withMessage(final String message) {
            this.message = message;
            return this;
        }

        public GenericResponseDto build() {
            return new GenericResponseDto(this);
        }
    }

}