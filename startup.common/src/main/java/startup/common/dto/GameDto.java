package startup.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import startup.common.enumeration.GameState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = GameDto.Builder.class)
public class GameDto {

    private final String guid;

    private final String code;

    private final GameState state;

    private final LocalDateTime activeDate;

    private final List<PlayerDto> players;

    private final GameSettingDto setting;

    public GameDto(final Builder builder) {
        this.guid = builder.guid;
        this.code = builder.code;
        this.state = builder.state;
        this.activeDate = builder.activeDate;
        this.players = builder.players != null ? builder.players: new ArrayList<>();
        this.setting = builder.setting;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getCode() {
        return this.code;
    }

    public GameState getState() {
        return this.state;
    }

    public LocalDateTime getActiveDate() {
        return this.activeDate;
    }

    public List<PlayerDto> getPlayers() {
        return this.players;
    }

    public GameSettingDto getSetting() {
        return this.setting;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private String guid;
        private String code;
        private GameState state;
        private LocalDateTime activeDate;
        private List<PlayerDto> players;
        private GameSettingDto setting;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withGuid(final String guid) {
            this.guid = guid;
            return this;
        }

        public Builder withCode(final String code) {
            this.code = code;
            return this;
        }

        public Builder withState(final GameState state) {
            this.state = state;
            return this;
        }

        public Builder withActiveDate(final LocalDateTime activeDate) {
            this.activeDate = activeDate;
            return this;
        }

        public Builder withPlayers(final List<PlayerDto> players) {
            this.players = players;
            return this;
        }

        public Builder withSetting(final GameSettingDto setting) {
            this.setting = setting;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }
    }

}