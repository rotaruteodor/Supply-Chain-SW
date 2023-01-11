package bGroup.SupplyChainSWbackend.handlers;

import bGroup.SupplyChainSWbackend.dtos.ApiExceptionDto;
import bGroup.SupplyChainSWbackend.exceptions.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class ApiExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> resourceNotFoundException(final ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiExceptionDto.builder().code("404 NOT FOUND").message(exception.getMessage()).build());
    }
}
