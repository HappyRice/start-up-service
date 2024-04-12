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

    private final String guid;

    private final BoardDto board;

    public HandDto(final Builder builder) {
        this.state = builder.state;
        this.guid = builder.guid;
        this.board = builder.board;
    }

    public HandState getState() {
        return this.state;
    }

    public String getGuid() {
        return this.guid;
    }

    public BoardDto getBoard() {
        return this.board;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private HandState state;
        private String guid;
        private BoardDto board;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withState(final HandState state) {
            this.state = state;
            return this;
        }

        public Builder withGuid(final String guid) {
            this.guid = guid;
            return this;
        }

        public Builder withBoard(final BoardDto board) {
            this.board = board;
            return this;
        }

        public HandDto build() {
            return new HandDto(this);
        }
    }

}