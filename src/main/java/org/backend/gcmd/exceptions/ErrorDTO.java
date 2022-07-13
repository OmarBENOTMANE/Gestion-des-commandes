package org.backend.gcmd.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class ErrorDTO {
    private String code;
    private LocalDateTime dateTime;
    private String message;
    private int status;
}
