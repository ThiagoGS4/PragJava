package com.antiprag.prag.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DuplicateDataHandler {
    private static final Pattern ENTRY_PATTERN = Pattern.compile("entry '([^']+)'", Pattern.CASE_INSENSITIVE);

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse duplicateEmailException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        String message = ex.getMessage();

        if (message != null) {
            Matcher matcher = ENTRY_PATTERN.matcher(message);
            int index = 1;

            while (matcher.find()) {
                String duplicatedValue = matcher.group(1);
                
                errors.put("erro: campo conflituoso " + index, duplicatedValue);
                index++;
            }
        }

        if (errors.isEmpty()) {
            errors.put("erro", "Dados duplicados violaram a integridade do banco.");
        }

        return new ErrorResponse(
            409,
            "Erro de conflito",
            errors
        );
        
    }
}
