package com.green.greengram.feedfavorite;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feedfavorite.model.FeedFavoriteToggleReq;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed/favorite")
public class FeedFavoriteController {
    private final FeedFavoriteService service;
    //toggle : 내가 똑같은 행동을 하는데 상태가 바뀌는 것 =insert or delete
    //get 방식의 장점: 속도가 빠름
        //get을 사용하는 이유 : 전송 데이터가 적음(데이터를 URL에 모두 실을 수 있다)
        //                   데이터 노출 되면 안 되는 것(ex.비밀번호)URL에 찍혀버림(ex.로그인 상황)
                // get 방식 URL에 데이터 포함해 요청(길이 제한), 보안 취약

    @GetMapping//user log-in때, postmapping하고 select했음
    @Operation(summary="좋아요", description="Toggle 처리")
    public ResultDto<Integer> toggleFavorite(@ModelAttribute FeedFavoriteToggleReq p){
        int result=service.toggleFavorite(p);
        //result 0=not 좋아요 취소, result 1=좋아요 처리
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result==1?"좋아요 처리":"좋아요 취소")
                .resultData(result)
                .build();
    }
}
