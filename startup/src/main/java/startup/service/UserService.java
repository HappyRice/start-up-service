package startup.service;

import org.springframework.stereotype.Service;
import startup.common.dto.UserDto;
import startup.persistence.UserRepository;
import startup.transformer.UserTransformer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserDtoById(final int id) {
        return UserTransformer.buildConsentDto(this.userRepository.getUserById(id));
    }

    public List<UserDto> getAllUsers() {
        return this.userRepository.getAllUsers().stream().map(UserTransformer::buildConsentDto).collect(Collectors.toList());
    }
}
