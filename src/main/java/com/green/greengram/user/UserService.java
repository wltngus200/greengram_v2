package com.green.greengram.user;

import com.green.greengram.common.CustomFileUtils;
import com.green.greengram.common.model.SignInRes;
import com.green.greengram.user.model.SignInPostReq;
import com.green.greengram.user.model.SignUpPostReq;
import com.green.greengram.user.model.User;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils;

    @Transactional
    int postSignUp(MultipartFile pic, SignUpPostReq p){
        String randFile=customFileUtils.makeRandomFileName(pic);
        p.setPic(randFile);//위 메소드에 사진이 null이여도 null값을 반환하는 코드
        String hashPass= BCrypt.hashpw(p.getUpw(),BCrypt.gensalt());
        p.setUpw(hashPass);//암호화

        int result=mapper.postUser(p);//위의 정보를 데이터 베이스에 저장

        if(pic==null){return result;}//사진이 없을 경우 그냥 올림
        try{//사진이 있으면 후처리를 해서 컴퓨터에 저장
            String path=String.format("user/%d", p.getUserId());
            customFileUtils.makeFolders(path);
            String target=String.format("%s/%s", path, randFile);
            customFileUtils.transferTo(pic, target);

        }catch(Exception e){
            e.printStackTrace();//뭐가 문젠지 출력
            throw new RuntimeException("FILE ERROR"); //에러가 터지면 롤백
        }
        return result;
    }

    public SignInRes postSignIn(SignInPostReq p){
        String uid=p.getUid();//유저가 입력한 아이디
        User user=mapper.getUserId(uid);//해당 아이디로 가입된 유저가 있는지 확인
        if(user == null){throw new RuntimeException("일치하는 회원이 없습니다.");}
                                        //메세지를 남기며 오류 던짐
        else if(!BCrypt.checkpw(p.getUpw(), user.getUpw())){
                                //방금 입력, 아이디로 조회한 비번
            throw new RuntimeException("비밀번호를 다시 확인하세요.");
        }
        return SignInRes.builder()
                .userId(user.getUserId())
                .nm(user.getNm())
                .pic(user.getPic())
                .build();
    }
}
