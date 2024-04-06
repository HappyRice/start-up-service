package startup.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import startup.common.enumeration.GameType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = GameDto.Builder.class)
public class GameDto {

    private final Integer id;

    private final String guid;

    private final String code;

    private final GameType type;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    private final Integer winsRequired;

    private final List<PlayerDto> players;

    public GameDto(final Builder builder) {
        this.id = builder.id;
        this.guid = builder.guid;
        this.code = builder.code;
        this.type = builder.type;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.winsRequired = builder.winsRequired;
        this.players = builder.players != null ? builder.players: new ArrayList<>();
    }

    public Integer getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getCode() {
        return this.code;
    }

    public GameType getType() {
        return this.type;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public Integer getWinsRequired() {
        return this.winsRequired;
    }

    public List<PlayerDto> getPlayers() {
        return this.players;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private Integer id;
        private String guid;
        private String code;
        private GameType type;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer winsRequired;
        private List<PlayerDto> players;

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

        public Builder withCode(final String code) {
            this.code = code;
            return this;
        }

        public Builder withType(final GameType type) {
            this.type = type;
            return this;
        }

        public Builder withStartDate(final LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(final LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withWinsRequired(final Integer winsRequired) {
            this.winsRequired = winsRequired;
            return this;
        }

        public Builder withPlayers(final List<PlayerDto> players) {
            this.players = players;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }
    }

}