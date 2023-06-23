package com.sparta.springmvc.response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/response")
public class ResponseController {
    // [Response Header]
    // Content-Type: text/html
    // [Response Body]
    // {"name":"Robbie, "age":95}
    @GetMapping("/json/string")
    @ResponseBody
    public String helloStringJson() {
        return "{\"name\":\"Robbie\",\"age\":95}";
    }

    // [Response Header]
    // Content-Type: application/json
    // [Response Body]
    // {"name":"Robbie, "age":95}
    @GetMapping("/json/class")
    @ResponseBody // 데이터 반환시!(템플릿 말고)
    public Star helloClassJson() {
        return new Star("Robbie", 95); // 스프링 내부에서 자바 내부 객체를 반환시에 json형태로 바꿔서 반환!
    }
}
