package com.bruno_pimenta_dev.to_do_list.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FieldMessage {
    private String fieldName;
    private String message;
}
