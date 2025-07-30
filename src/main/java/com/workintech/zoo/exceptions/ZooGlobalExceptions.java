package com.workintech.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ZooGlobalExceptions {

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleZooExceptions(ZooException zooException) {
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(zooException.getHttpStatus().value(),
                zooException.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity(zooErrorResponse, zooException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleZooExceptions(Exception exception) {
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity(zooErrorResponse, HttpStatus.BAD_REQUEST);
    }


}
