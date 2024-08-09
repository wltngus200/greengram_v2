package com.green.greengramver2.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpPostReq {
    @JsonIgnore
    private long userId;

    @Schema(example = "ID",description = "User ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(example = "PASSWORD",description = "User PW", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
    @Schema(example = "김민지",description = "User NAME", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nm;

    @JsonIgnore
    private String pic;
}
