package com.ai.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GeminiRequest {

    private List<Content> contents;


    @Getter
    @Setter
    @ToString
   public static
    class Content{

        private List<Parts> parts;

    }


    @Getter
    @Setter
    @ToString
   public static
    class Parts{

        private String text;

    }
}


