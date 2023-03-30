package startup.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = UserDto.Builder.class)
public class UserDto {

    private final int id;

    private final String name;

    private final String email;

    public UserDto(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private int id;
        private String name;
        private String email;

        public Builder withId(final int id) {
            this.id = id;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }

}