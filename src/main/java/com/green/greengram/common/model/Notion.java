package com.green.greengram.common.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
public class Notion {
    private final String path;
    public Notion(@Value("${file.directory}")String path){
        this.path=path;
    }
}


