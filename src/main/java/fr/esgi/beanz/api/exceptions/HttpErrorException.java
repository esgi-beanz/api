package fr.esgi.beanz.api.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HttpErrorException extends Throwable {
    private HttpStatus status;

    private String[] errors = {};

    public HttpErrorException(HttpStatus status) {
        this.status = status;
    }

    public HttpErrorException(HttpStatus status, String[] errors) {
        this.status = status;
        this.errors = errors;
    }

    public HttpErrorException(HttpStatus status, String error) {
        this.status = status;
        this.errors = new String[] { error };
    }

    public Map<String, Object> responseBody() {
        final var body = new HashMap<String, Object>();

        body.put("timestamp", Instant.now().toString());
        body.put("status", this.getStatus().value());
        body.put("error", this.getStatus().getReasonPhrase());
        body.put("messages", this.getErrors());

        return body;
    }
}
