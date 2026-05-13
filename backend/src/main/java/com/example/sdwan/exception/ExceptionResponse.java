package com.example.sdwan.exception;

/**
 * @author subiksha
 * ***/

//Using a builder pattern to create an instance of ExceptionResponse class, which is a common practice for creating immutable objects with many parameters. The builder pattern allows for more readable and maintainable code when constructing complex objects.
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}