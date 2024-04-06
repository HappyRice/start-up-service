package startup.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = PlayerDto.Builder.class)
public class PlayerDto {

    private final Integer id;

    private final String guid;

    private final String name;

    private final GameDto game;

    private final int winCounter;

    public PlayerDto(final Builder builder) {
        this.id = builder.id;
        this.guid = builder.guid;
        this.name = builder.name;
        this.game = builder.game;
        this.winCounter = builder.winCounter;
    }

    public Integer getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getName() {
        return this.name;
    }

    public GameDto getGame() {
        return this.game;
    }

    public int getWinCounter() {
        return this.winCounter;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private Integer id;
        private String guid;
        private String name;
        private GameDto game;
        private int winCounter;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder withGuid(final String guid) {
            this.guid = guid;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withGame(final GameDto game) {
            this.game = game;
            return this;
        }

        public Builder withWinCounter(final int winCounter) {
            this.winCounter = winCounter;
            return this;
        }

        public PlayerDto build() {
            return new PlayerDto(this);
        }
    }

}