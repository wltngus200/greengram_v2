package com.green.greengramver2.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRes {
    @Schema(example = "1", description = "USER PK")
    private long userId;
    @Schema(example = "김민지", description = "USER NAME")
    private String nm;
    @Schema(example = "bf11d325-f67f-43c1", description="USER IMAGE")
    private String pic;

}
