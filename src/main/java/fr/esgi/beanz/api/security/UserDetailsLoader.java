package fr.esgi.beanz.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.esgi.beanz.api.user.UserService;

@Service
public class UserDetailsLoader implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var appUser = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        return User.builder().username(username).password(appUser.getPassword())
                .roles(appUser.getRoles().toArray(new String[0])).accountExpired(false).accountLocked(false)
                .credentialsExpired(false).disabled(false).build();
    }
}