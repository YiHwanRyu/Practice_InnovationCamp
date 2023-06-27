package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanTest {

//    @Autowired // 기본적으로 Food 타입을 찾음
//    Food pizza; // 하나의 인터페이스 구현체가 2개이상이면 확실히 명시해줘야함.
//
//    @Autowired
//    Food chicken;


    @Autowired //@Primary 를 Chicken에 적용하면 우선적으로 구현체지정(빈 등록)
    @Qualifier("pizza") // @Qualifier를 Pizza에 지정하면 우선적용됨. -> 지엽적일 때 사용
    Food food;

    @Test
    @DisplayName("Primary와 Qualifier의 우선순위")
    void test1() {
        food.eat(); // 치킨을 먹습니다.
        // @Qualifier 우선적용 -> 피자를 먹습니다.
    }



}
