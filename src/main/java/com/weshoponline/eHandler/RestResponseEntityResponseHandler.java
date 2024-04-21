package com.weshoponline.eHandler;

import com.weshoponline.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmployeeNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage employeeNotFoundHandler(EmployeeNotFoundException employeeNotFoundException) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, employeeNotFoundException.getMessage());
        return errorMessage;
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage genericExceptionHandler(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return errorMessage;
    }

}
