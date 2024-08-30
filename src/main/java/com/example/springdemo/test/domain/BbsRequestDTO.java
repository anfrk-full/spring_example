package com.example.springdemo.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class BbsRequestDTO {
    private int id;
    private String title;
    private String content;
}
