package org.backend.gcmd.exceptions;

import org.backend.gcmd.enums.ErrorCode;
import org.backend.gcmd.exceptions.business.ConstraintViolationException;
import org.backend.gcmd.exceptions.technical.AlreadyExistsException;
import org.backend.gcmd.exceptions.technical.IllegalNullParamException;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalNullParamException.class})
    protected ResponseEntity<ErrorDTO> handleConflict(IllegalNullParamException ex, WebRequest request) {

        return new ResponseEntity<>(ErrorDTO.builder()
                .code(ErrorCode.CODE_1.name())
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build(),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {ObjectNotFoundException.class})
    protected ResponseEntity<ErrorDTO> handleConflict(ObjectNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(ErrorDTO.builder()
                .code(ErrorCode.CODE_2.getCode())
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    protected ResponseEntity<ErrorDTO> handleConflict(AlreadyExistsException ex  , WebRequest request){
         return new ResponseEntity<>(ErrorDTO.builder()
                 .code(ErrorCode.CODE_3.getCode())
                 .dateTime(LocalDateTime.now())
                 .message(ex.getMessage())
                 .status(HttpStatus.BAD_REQUEST.value())
                 .build(),
                 HttpStatus.NOT_FOUND);
    }

}
