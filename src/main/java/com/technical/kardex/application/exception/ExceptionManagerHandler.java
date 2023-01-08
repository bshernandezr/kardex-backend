package com.technical.kardex.application.exception;

import com.technical.kardex.application.models.GenericResponse;
import com.technical.kardex.domain.exception.InvalidStockException;
import com.technical.kardex.domain.exception.ProductAlreadyCreatedException;
import com.technical.kardex.domain.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionManagerHandler {

    @ExceptionHandler(value = {ProductAlreadyCreatedException.class})
    public ResponseEntity<GenericResponse<String>> productAlreadyCreatedException(Exception ex) {
        return new ResponseEntity<>(new GenericResponse<>("El producto ingresado posee un id existente en el sistema, " +
                "por favor ingresa uno diferente", HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<GenericResponse<String>> productNotFoundException(Exception ex) {
        return new ResponseEntity<>(new GenericResponse<>("No se encontró un producto asociado al id ingresado",
                HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT.value()), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = {InvalidStockException.class})
    public ResponseEntity<GenericResponse<String>> invalidStockException(Exception ex) {
        return new ResponseEntity<>(new GenericResponse<>("El stock no puede ser negativo, valida las cantidades ingresadas.", HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<Map<String,String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new GenericResponse<>(errors, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
