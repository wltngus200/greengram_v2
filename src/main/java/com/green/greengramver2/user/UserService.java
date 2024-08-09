package com.green.greengramver2.user;

import com.green.greengramver2.common.CustomFileUtils;
import com.green.greengramver2.user.model.SignUpPostReq;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils;

    @Transactional
    public int postSignUp(MultipartFile pic, SignUpPostReq p){
        String saveFileName=customFileUtils.makeRandomFileName(pic);
        p.setPic(saveFileName);
        String hashedPw=BCrypt.hashpw(p.getUpw(),BCrypt.gensalt());
        p.setUpw(hashedPw);
        int result=mapper.postUser(p);

        if(pic==null){return result;}
        try{
            String path=String.format("user/%d",p.getUserId());
            customFileUtils.makeFolders(path);
            String target=String.format("%s/%s", path, saveFileName);
            customFileUtils.transferTo(pic, target);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("파일 오류");
        }
        return result;
    }

}
