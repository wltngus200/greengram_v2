package com.green.greengram.user;

import com.green.greengram.common.model.SignInRes;
import com.green.greengram.user.model.SignInPostReq;
import com.green.greengram.user.model.SignUpPostReq;
import com.green.greengram.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface UserMapper {
    int postUser(SignUpPostReq p);
    SignInRes postSignIn (SignInPostReq p);
    User getUserId(String p);
}
