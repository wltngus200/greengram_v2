package com.green.greengram.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.greengram.common.GlobalConst;
import com.green.greengram.common.model.Paging;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
public class FeedGetReq extends Paging {
    private long signedUserId;

    public FeedGetReq(Integer page, Integer size,@BindParam("signed_user_id") long signedUserId){
        super(page, size ==null? GlobalConst.FEED_PAGING_SIZE:size);
        this.signedUserId=signedUserId;
    }
}
