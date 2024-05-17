package com.green.greengram.feed;

import com.green.greengram.common.CustomFileUtils;
import com.green.greengram.common.GlobalConst;
import com.green.greengram.feed.model.*;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
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
        int result=mapper.postFeed(p); //사진을 제외한 내용과 위치정보는 매퍼로 보내 feed에 업로드+영향 받은 행 값

        //사진 다루기
        FeedPostPicReq postDto= FeedPostPicReq.builder().feedId(p.getFeedId()).build(); //feed_id값 주입
        //Dto는 DB에 정보를 전달하기 위한 것= n번 피드에 들어가는 사진들의 이름이다!

        try{
            String path=String.format("feed/%d", p.getFeedId());
            customFileUtils.makeFolders(path);//폴더 생성
        //파일 이름을 랜덤이름을 만들어 DB에 업로드
            for(MultipartFile pic:pics) {//사진 리스트
                String randFile=customFileUtils.makeRandomFileName(pic);//랜덤이름
                String target=String.format("%s/%s", path, randFile);
                customFileUtils.transferTo(pic, target);
                postDto.getFileNames().add(randFile);//Dto가 가진 리스트에 .add(넣어준다)
            } int upPics=mapper.postFeedPics(postDto);
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
        for (FeedGetRes res : list) {
            //사진 리스트
            List<String> pics = mapper.getFeedPicsByFeedId(res.getFeedId());
            res.setPics(pics);


            //피드마다 댓글이 몇개 있는지 알 수 없다
            //내가 3개 필요하다고 3개 들고왔을 때 댓글이 더 있을지 없을지 알 수 없다
            //댓글을 4개 가져오는 이유 3개보다 더 있나 없나 파악 0123은 그대로 comment에 들어감
            //댓글을 4개 가져왔는데 3개만 나온다 댓글 더 보기 버튼 x
            // 4개만 있다 1개 버리고 더보기 버튼 O
            //댓글 더보기 기능(페이징 처리, 부분만, 트래픽 발생X)
            // 아주 많으면 더 보기를 눌러도 일부만 가져옴
            // 피드에 달린 댓글을 4개 가져오기 시도 -> 4개가 넘어온다 1개는 버리고 3개만 가져옴
            // 댓글이 더 있었을 경우만 버튼을 생성
            List<FeedCommentGetRes> commentList = mapper.getFeedCommentTopBy4ByFeedId(res.getFeedId());
                if(commentList.size()== GlobalConst.COMMENT_SIZE_PER_FEED){
                    //isMoreComment는 왜 int야....
                    res.setIsMoreComment(1);
                    commentList.remove(commentList.size()-1);
                    //setIsMoreComment가 boolean으로 바뀔 수도 있고
                    //코멘트가 더 있다 <-> 마지막 인덱스를 구하기 위한 1 이기 때문에 분리 해서 쓰기
                }
                res.setComments(commentList);
            }
            return list;
    }
}
