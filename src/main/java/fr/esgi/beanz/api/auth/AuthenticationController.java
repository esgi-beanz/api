package fr.esgi.beanz.api.auth;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.beanz.api.auth.dto.LoginDTO;
import fr.esgi.beanz.api.exceptions.HttpErrorException;
import fr.esgi.beanz.api.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final JwtTokenProvider tokenProvider;

    @Autowired
    private final AuthenticationManagerBuilder authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginDTO data) throws HttpErrorException {
        try {
            final var authenticationToken = new UsernamePasswordAuthenticationToken(data.getUsername(),
                    data.getPassword());
            final var authentication = authenticationManager.getObject().authenticate(authenticationToken);
            final var token = tokenProvider.createToken(authentication);

            final var body = new HashMap<String, Object>();
            body.put("token", token);

            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            throw new HttpErrorException(HttpStatus.FORBIDDEN, "Bad credentials");
        }
    }
}
