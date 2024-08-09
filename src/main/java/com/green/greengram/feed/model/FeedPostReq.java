package com.green.greengram.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedPostReq {
    @JsonIgnore
    private long feedId;

    private long userId;
    private String contents;
    private String location;
}
