package com.rubenjg.weatherforecastservice.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DefaultErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "City not found");
        }
        return new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred. Please try again later.");
    }
}
