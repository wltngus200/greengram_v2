package com.green.greengram.feedcomment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCommentPostReq {
    @JsonIgnore
    private long feedCommentId;

    private long feedId;
    private long userId;
    private String comment;
}
