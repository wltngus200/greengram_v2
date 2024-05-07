package com.green.greengramver2.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInPostReq {
    @Schema(example="ID", description = "User Id", requiredMode=Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(example="qwerty", description = "User Password", requiredMode=Schema.RequiredMode.REQUIRED)
    private String upw;

}
