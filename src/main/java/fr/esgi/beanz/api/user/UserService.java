package fr.esgi.beanz.api.user;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.beanz.api.user.dto.CreateUserDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Transactional()
    public User createUser(CreateUserDTO data) {
        final var user = new User();

        user.setEmail(data.getEmail());
        user.setUsername(data.getUsername());

        final var encodedPassword = passwordEncoder.encode(data.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
