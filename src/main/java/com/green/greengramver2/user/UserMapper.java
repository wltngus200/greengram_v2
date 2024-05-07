package com.green.greengramver2.user;

import com.green.greengramver2.common.SignInRes;
import com.green.greengramver2.user.model.SignInPostReq;
import com.green.greengramver2.user.model.SignUpPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int postUser(SignUpPostReq p);
    SignInRes postSignIn(SignInPostReq p);
}
