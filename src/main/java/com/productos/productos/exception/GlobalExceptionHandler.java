package com.productos.productos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.productos.productos.dto.ErrorResponse;

@RestControllerAdvice

public class GlobalExceptionHandler {
    
    @ExceptionHandler({ErrorCategoriaYaExistente.class})
    public ResponseEntity<ErrorResponse> duplicado(RuntimeException ex){
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
   }

    @ExceptionHandler({ErrorCategoriaInexistente.class})
    public ResponseEntity<ErrorResponse> catInexistente(RuntimeException ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> campoVacioONulo(MethodArgumentNotValidException ex){
        String msj = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return buildResponse(msj, HttpStatus.BAD_REQUEST);
   }
    private ResponseEntity<ErrorResponse> buildResponse(String mensaje, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(
                                status.value(),
                                mensaje,
                                System.currentTimeMillis()
                            );
        return new ResponseEntity<>(errorResponse,status);
   }

}
