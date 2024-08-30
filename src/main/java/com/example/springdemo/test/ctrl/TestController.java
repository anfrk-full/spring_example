package com.example.springdemo.test.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springdemo.test.domain.BbsResponseDTO;


//풀 브라우징 통신을 사용 할 때
@Controller
// spring container 는 TestController testController = new TestController();
public class TestController {
    
    @RequestMapping("/index.kdt")
    public String index(Model model) {
        System.out.println("debug >>>> test controller index client path : /index.kdt");
        model.addAttribute("msg", "처음으로 만나는 스프링 부트 ");
        return "index";
    }

    @RequestMapping(value="/api/bbs/ctrl/test", method=RequestMethod.GET)
    @ResponseBody   // @Controller를 쓸때 비동기를 하고싶으면 이거를 선언해야된다.
    public BbsResponseDTO test() {
        BbsResponseDTO response = BbsResponseDTO.builder()
                                    .id(1)
                                    .title("title")
                                    .content("content")
                                    .build();
        return response;
    }
    
    
}
