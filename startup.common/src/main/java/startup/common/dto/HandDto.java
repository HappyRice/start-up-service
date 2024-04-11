package startup.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import startup.common.enumeration.HandState;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = HandDto.Builder.class)
public class HandDto {

    private final HandState state;

    public HandDto(final Builder builder) {
        this.state = builder.state;
    }

    public HandState getState() {
        return this.state;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private HandState state;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withState(final HandState state) {
            this.state = state;
            return this;
        }

        public HandDto build() {
            return new HandDto(this);
        }
    }

}