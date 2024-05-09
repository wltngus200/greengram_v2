package com.green.greengram.feedcomment;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed/comment")
public class FeedCommentController {
    private final FeedCommentService service;
    @PostMapping
    public ResultDto<Long> postFeedComment(@RequestBody FeedCommentPostReq p){
        long feedCommentId=service.postFeedComment(p);
        return ResultDto.<Long>builder()
                .status(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(feedCommentId)
                .build();
    }

    @DeleteMapping
    public ResultDto<Integer> deleteFeedComment(@ParameterObject @ModelAttribute FeedCommentDeleteReq p){
        int result= service.deleteFeedComment(p);
        //0은 삭제 실패, 1은 삭제 성공
        return ResultDto.<Integer>builder()
                .status(HttpStatus.OK)
                .resultMsg(result==1?"삭제성공":"삭제 실패")
                .resultData(result)
                .build();
    }
}
