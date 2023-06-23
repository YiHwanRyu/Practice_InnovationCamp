package com.sparta.springprepare;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor // 모든 필드의 생성자
//@NoArgsConstructor // 기본생성자
@Getter
@Setter
@RequiredArgsConstructor // final 키워드 필수
public class Memo {
    private final String username;
    private final String contents;

}