package com.ks.app.util;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    public static final String MESSAGE = "message";

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        Map<String, Object> errorResponse = super.getErrorAttributes(request, options);

        //extract the status and put custom error message on the map
        HttpStatus status = HttpStatus.valueOf((Integer) errorResponse.get("status"));

        switch (status) {
            case BAD_REQUEST:
                errorResponse.put(MESSAGE, "Missing authorization header.");
                break;
            case UNAUTHORIZED:
                errorResponse.put(MESSAGE, "Invalid/Expired Token");
                break;
            default:
                errorResponse.put(MESSAGE, "Something went wrong! Please check the request/token");
        }

        return errorResponse;
    }
}
