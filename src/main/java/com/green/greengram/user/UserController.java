package com.green.greengram.user;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.common.model.SignInRes;
import com.green.greengram.user.model.SignInPostReq;
import com.green.greengram.user.model.SignUpPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@Slf4j
@Tag(name="USER CONTROLLER", description = "USER CR(sign-in, sign=up)")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    @Operation(summary="회원가입", description = "프로필 사진은 필수가 아님")
    public ResultDto<Integer> postUser(@RequestPart MultipartFile pic, @RequestPart SignUpPostReq p){
        int result=service.postSignUp(pic, p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("회원가입 성공")
                .resultData(result)
                .build();
    }
    @PostMapping("sign-in")
    @Operation(summary="인증처리")
    public ResultDto<SignInRes> postSignIn(@RequestBody SignInPostReq p){
        SignInRes result=service.postSignIn(p);
        log.info("{}",result);
        return ResultDto.<SignInRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("로그인 완료되었습니다.")
                .resultData(result)
                .build();
    }
}
