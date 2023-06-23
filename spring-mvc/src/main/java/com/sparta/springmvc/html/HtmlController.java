package com.sparta.springmvc.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {


    private static long visitCount = 0;


    @GetMapping("/static-hello")
    public String hello() {
        return "hello.html"; // String 해당하는 것 찾아서 반환함, 컨트롤러를 거치지 않음...정적
        //http://localhost:8080/hello.html 즉, 파일을 직접 반환(정적으로 제공)
        //만약에 thymeleaf를 주석처리한다면
        //http://localhost:8080/static-hello 즉, 컨트롤러를 거침(동적으로 제공)
    }

    @GetMapping("/html/redirect")
    public String htmlStatic() {
        return "redirect:/hello.html"; // 재호출하여 다른경로로 호출 가능
                                        // http://localhost:8080/hello.html
    }


    // 앞으로 사용할 방식: @Controller 되어있는 Class 내에서 맵핑할 때 templates 폴더 내에 있는 파일의 이름까지만 반환
    @GetMapping("/html/templates")
    public String htmlTemplates() {
        return "hello"; // 이름에 해당하는 파일 반환(templates 내에서)
    }

    @GetMapping("/html/dynamic")
    public String htmlDynamic(Model model) {
        visitCount++;
        model.addAttribute("visits", visitCount); // 모델에 값을 입혀서
        return "hello-visit"; // 이름에 해당하는 파일로 반환!
    }
}
