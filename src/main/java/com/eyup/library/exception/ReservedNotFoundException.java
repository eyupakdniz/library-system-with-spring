package com.eyup.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReservedNotFoundException extends RuntimeException{
	public ReservedNotFoundException(String message) {
        super(message);
    }

	public ReservedNotFoundException() {
        super();
    }
}
