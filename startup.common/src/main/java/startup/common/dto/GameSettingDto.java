package startup.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import startup.common.enumeration.GameType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = GameSettingDto.Builder.class)
public class GameSettingDto {

    private final GameType type;

    private final Integer winsRequired;

    public GameSettingDto(final Builder builder) {
        this.type = builder.type;
        this.winsRequired = builder.winsRequired;
    }

    public GameType getType() {
        return this.type;
    }

    public Integer getWinsRequired() {
        return this.winsRequired;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private GameType type;
        private Integer winsRequired;

        private Builder() {
            // Prevent Instantiation
        }

        public Builder withType(final GameType type) {
            this.type = type;
            return this;
        }

        public Builder withWinsRequired(final Integer winsRequired) {
            this.winsRequired = winsRequired;
            return this;
        }

        public GameSettingDto build() {
            return new GameSettingDto(this);
        }
    }

}