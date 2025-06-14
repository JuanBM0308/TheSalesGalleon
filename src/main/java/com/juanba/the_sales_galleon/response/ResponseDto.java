package com.juanba.the_sales_galleon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResponseDto {
    private Boolean status;
    private String detail;
    private Object response;
}
