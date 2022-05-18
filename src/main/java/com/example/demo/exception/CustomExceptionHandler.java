package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler({IndexOutOfBoundsException.class})
    @ResponseBody
    public String test(Exception e){
        log.info("e:",e);
       return e.getMessage();
    }

}
