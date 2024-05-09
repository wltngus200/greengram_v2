package com.green.greengram.feedcomment.model;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
public class FeedCommentDeleteReq {
    private long feedCommentId;
    private long signedUserId;

    @ConstructorProperties({"feedCommentId", "signedUserId"})
    public FeedCommentDeleteReq(long feedCommentId, long signedUserId) {
        this.feedCommentId = feedCommentId;
        this.signedUserId = signedUserId;
    }
}
