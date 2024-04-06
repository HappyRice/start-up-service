package startup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = GenericResponseDto.Builder.class)
public class GenericResponseDto {

    private final Boolean success;

    private final Object entity;

    private final String message;

    public GenericResponseDto(final Builder builder) {
        this.success = builder.success;
        this.entity = builder.entity;
        this.message = builder.message;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public Object getEntity() {
        return this.entity;
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
        private Object entity;
        private String message;

        private Builder(){
            // Prevent Instantiation
        }

        public Builder withSuccess(final Boolean success) {
            this.success = success;
            return this;
        }

        public Builder withEntity(final Object entity) {
            this.entity = entity;
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