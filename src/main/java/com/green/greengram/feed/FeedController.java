package com.green.greengram.feed;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feed.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed")
@Tag(name="FEED", description = "FEED CRUD")
public class FeedController {
    private final FeedService service;

    @PostMapping
    @Operation(summary="FEED LIST", description = "loginUserId is log-in User's PK")
    public ResultDto<FeedPostRes> postFeed(@RequestPart List<MultipartFile> pics, @RequestPart FeedPostReq p){
        //들어온 값을 사진과 정보로 나눠서 생각
        FeedPostRes result=service.postFeedPics(pics, p);//들어온 정보를 Service로 보냄

        return ResultDto.<FeedPostRes>builder()
                .status(HttpStatus.OK)
                .resultMsg("성공적으로 업로드 하였습니다.")
                .resultData(result)
                .build();
    }
    @GetMapping
    public ResultDto<List<FeedGetRes>> getFeed(@ParameterObject @ModelAttribute FeedGetReq p){
        //리턴타입을 프론트가 요구하는 방향으로 맞춰주어야 함
        List<FeedGetRes> result = service.getFeed(p);
        return ResultDto.<List<FeedGetRes>>builder()
                .status(HttpStatus.OK)
                .resultMsg("성공적으로 업로드 하였습니다.")
                .resultData(result)
                .build();
    }

}
