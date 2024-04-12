package startup.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import startup.common.enumeration.Card;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = BoardDto.Builder.class)
public class BoardDto {

    private final Card flop1;

    private final Card flop2;

    private final Card flop3;

    private final Card turn;

    private final Card river;

    public BoardDto(final Builder builder) {
        this.flop1 = builder.flop1;
        this.flop2 = builder.flop2;
        this.flop3 = builder.flop3;
        this.turn = builder.turn;
        this.river = builder.river;
    }

    public Card getFlop1() {
        return this.flop1;
    }

    public Card getFlop2() {
        return this.flop2;
    }

    public Card getFlop3() {
        return this.flop3;
    }

    public Card getTurn() {
        return this.turn;
    }

    public Card getRiver() {
        return this.river;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private Card flop1;
        private Card flop2;
        private Card flop3;
        private Card turn;
        private Card river;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withFlop1(final Card flop1) {
            this.flop1 = flop1;
            return this;
        }

        public Builder withFlop2(final Card flop2) {
            this.flop2 = flop2;
            return this;
        }

        public Builder withFlop3(final Card flop3) {
            this.flop3 = flop3;
            return this;
        }

        public Builder withTurn(final Card turn) {
            this.turn = turn;
            return this;
        }

        public Builder withRiver(final Card river) {
            this.river = river;
            return this;
        }

        public BoardDto build() {
            return new BoardDto(this);
        }
    }

}