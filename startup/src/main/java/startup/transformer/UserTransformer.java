package startup.transformer;

import startup.common.dto.UserDto;
import startup.model.User;

public final class UserTransformer {

    private UserTransformer() {
        // prevent instantiation
    }

    public static UserDto buildConsentDto(final User user) {
        return new UserDto.Builder()
                .withId(user.getId())
                .withName(user.getName())
                .withEmail(user.getEmail())
                .build();
    }

}