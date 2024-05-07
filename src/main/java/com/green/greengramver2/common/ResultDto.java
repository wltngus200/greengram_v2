package com.green.greengramver2.common;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResultDto<T> {
    HttpStatus status;
    String resultMsg;
    T resultData;
}
