package com.green.greengram.feedcomment;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed/comment")//주소값을 구분 같은 주소값이라도 아래의 메소드로 구분
public class FeedCommentController {
    private final FeedCommentService service;
    @PostMapping
    public ResultDto<Long> postFeedComment(@RequestBody FeedCommentPostReq p){
        //Request Body에 담긴 Json( File도 받아올 수 있다) == post, put에 적절(노출 되어서는 안 되는 데이터)
        //                       <-> 쿼리스트링(URL) , pathvalueable ==get delete에 적절(많은 데이터 X)
                                                        //ㄴ 패스베리어블은 post put 때도 사용 가능

        //put은 대량 //patch는 하나 정도 소량의 데이터 수정
        long feedCommentId=service.postFeedComment(p);
        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(feedCommentId)
                .build();
    }

    @DeleteMapping
    public ResultDto<Integer> deleteFeedComment(@ParameterObject @ModelAttribute FeedCommentDeleteReq p){
        int result= service.deleteFeedComment(p);
        //0은 삭제 실패, 1은 삭제 성공
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result==1?"삭제 성공":"삭제 실패")//메세지는 프론트가 이해하기 쉽게
                .resultData(result)
                .build();
    }
    @GetMapping
    public ResultDto<List<FeedCommentGetRes>> getFeedComment(@RequestParam(name="feed_id") long feedId){
        List<FeedCommentGetRes> list=service.getFeedComment(feedId);//4~n

        return ResultDto.<List<FeedCommentGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(String.format("rows: %,d",list.size()))//메세지는 프론트가 이해하기 쉽게
                .resultData(list)
                .build();

    }
}
