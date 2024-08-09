package com.green.greengramver2.user;

import com.green.greengramver2.common.ResultDto;
import com.green.greengramver2.user.model.SignUpPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@Tag(name="유저 컨트롤러", description="유저 CRUD, sign-in, sign-out")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    @Operation(summary="회원가입", description = "프로필 사진은 필수가 아님")
    ResultDto<Integer> postUser(@RequestPart(required = false) MultipartFile pic, @RequestPart SignUpPostReq p){
        int result=service.postSignUp(pic, p);
        return ResultDto.<Integer>builder()
                .status(HttpStatus.OK)
                .resultMsg("회원가입에 성공하였습니다.")
                .resultData(result)
                .build();
    }
}
