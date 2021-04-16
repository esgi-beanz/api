package fr.esgi.beanz.api.user;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        final var user = new User().setUsername(username);
        return userRepository.findOne(Example.of(user));
    }

    @Transactional()
    public User createUser(CreateUserDTO data) {
        final var encodedPassword = passwordEncoder.encode(data.getPassword());
        final var user = new User().setEmail(data.getEmail()).setUsername(data.getUsername())
                .setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
