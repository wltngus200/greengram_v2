package com.green.greengram.feedfavorite;

import com.green.greengram.feedfavorite.model.FeedFavoriteToggleReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedFavoriteService {
    private final FeedFavoriteMapper mapper;
    //result 0=not 좋아요 취소, result 1=좋아요 처리
        //1. select해서 있으면 delete, 없으면 insert
        //2. insert 해서 에러가 터지면 delete
        // <-> 3. delete해서 1이 넘어오면(지워지면) 0리턴, 0이 넘어오면 insert하고 1 리턴
        //pk문제인지 신텍스 에러인지

    int toggleFavorite(FeedFavoriteToggleReq p){
        int delAffectedRow=mapper.delFeedFavorite(p);
        if(delAffectedRow==1){
            return 0;
        }//토글 처리를 하지 않고 프론트에서 직접적으로 날리는 것도 가능
        return mapper.insFeedFavorite(p);
    }



}
