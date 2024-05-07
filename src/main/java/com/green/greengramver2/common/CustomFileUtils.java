package com.green.greengramver2.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Getter
@Component
public class CustomFileUtils {
    private final String uploadPath;
    //final이 붙어있어서 반드시 생성자에 파라미터로서 존재해야 함
    //그게 아래에 있는 것 path는 누가 들고 있고 누가 넣어주는가......?
    //빈등록의 차이(사진)
    public CustomFileUtils(@Value("${file.directory}") String uploadPath){
        this.uploadPath=uploadPath;
    }
    public String makeFolders(String path){//새 폴더 생성
        File folder=new File(uploadPath, path);
        folder.mkdir();//여러개의 폴더 생성
        return folder.getAbsolutePath();
    }
    public String getExt(String fileName){
        int idx=fileName.lastIndexOf(".");
        return fileName.substring(idx);
    }
    //랜덤이름 생성 3총사
    public String makeRandomFileName(){return UUID.randomUUID().toString();}
    public String makeRandomFileName(String fileName){return makeRandomFileName()+getExt(fileName);}
    public String makeRandomFileName(MultipartFile mf){
        return mf==null || mf.isEmpty()? null:makeRandomFileName(mf.getOriginalFilename());}

    //파일 올리기
    public void transferTo(MultipartFile mf, String target) throws Exception{
        File saveFile=new File(uploadPath, target);
        mf.transferTo(saveFile);
    }
}