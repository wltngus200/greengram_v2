package com.green.greengram.feed;

import com.green.greengram.common.CustomFileUtils;
import com.green.greengram.feed.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final CustomFileUtils customFileUtils;


    @Transactional
    FeedPostRes postFeedPics(List<MultipartFile> pics, FeedPostReq p){//사진이 없을 가능성은??//그런 코드를 넣었나?
        int result=mapper.postFeed(p); //사진을 제외한 내용과 위치정보는 매퍼로 보내 feed에 업로드

        //사진 다루기
        FeedPicPostDto postDto= FeedPicPostDto.builder().feedId(p.getFeedId()).build(); //feed_id값 주입

        try{
            String path=String.format("feed/%d", p.getFeedId());
            customFileUtils.makeFolders(path);//폴더 생성
        //파일 이름을 랜덤이름을 만들어 DB에 업로드
            for(MultipartFile pic:pics) {//사진 리스트
                String randFile=customFileUtils.makeRandomFileName(pic);//랜덤이름
                String target=String.format("%s/%s", path, randFile);
                customFileUtils.transferTo(pic, target);
                postDto.getFileNames().add(randFile);//Dto가 가진 리스트에 넣어준다
            } int uppics=mapper.postFeedPics(postDto);
        //폴더에 저장
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("UPROAD ERROR");
        }
        return FeedPostRes.builder()
                .feedId(postDto.getFeedId())
                .pics(postDto.getFileNames())
                .build();
    }
    List<FeedGetRes> getFeed(FeedGetReq p) { //페이지값 입력
        List<FeedGetRes> list = mapper.getFeed(p);//리턴해줄 리스트를 만드는 것
        for (FeedGetRes res : list){
            List<String> pics = mapper.getFeedPicsByFeedId(res.getFeedId());
            res.setPics(pics);
        }
        return list;
    }
}
