package com.green.greengram.feedcomment;

import com.green.greengram.common.GlobalConst;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedCommentService {
    private final FeedCommentMapper mapper;

    long postFeedComment(FeedCommentPostReq p){
        int affectedRows=mapper.insFeedComment(p);
        return p.getFeedCommentId();
    }
    int deleteFeedComment(FeedCommentDeleteReq p){
        return mapper.deleteFeedComment(p);
    }

    List<FeedCommentGetRes> getFeedComment(long feedId){
        List<FeedCommentGetRes> commentList=mapper.getFeedComment(feedId);
//        if(commentList.size()> GlobalConst.COMMENT_SIZE_PER_FEED){
//            for(int i=0; i<(GlobalConst.COMMENT_SIZE_PER_FEED-1);i++){
//              FeedCommentGetRes a=commentList.remove(i);//리무브가 들고 오는 게 res네.......
//                //지운 리스트를 저장하고 싶은데
        commentList=commentList.subList((GlobalConst.COMMENT_SIZE_PER_FEED-1),commentList.size());
                //commentList.subList(fromIdx, toIdx)->리턴도 리스트니 얘가 n부터 m까지를 리턴 해주지 않을까
                //paging은 지우고 다시 세팅한다면, 얘는 추가로 꺼내 온 걸 이어붙이는 느낌으로 이해 함
        return commentList;
    }
}
