package com.example.springdemo.test.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdemo.test.domain.BbsRequestDTO;
import com.example.springdemo.test.domain.BbsResponseDTO;
import com.example.springdemo.test.service.BbsService;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


//비동기 통신을 사용 할 때
@RestController
// user : http://localhost:7777/api/bbs
@RequestMapping("api/bbs")

public class BbsController {
    
    @Autowired
    private BbsService bbsService;

    // user : http://localhost:7777/api/bbs/test
    //@RequestMapping("/test") //GET, POST 전부 적용
    //@PostMapping("/test") // POST 만 적용
    @GetMapping("/test") // GET 만 적용
    public ResponseEntity<BbsResponseDTO> test() {
        BbsResponseDTO response = BbsResponseDTO.builder()
                                    .id(1)
                                    .title("title")
                                    .content("content")
                                    .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    파라미터로 전달되는 id에 해당하는 데이터를 삭제한다면?
    */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        System.out.println("debug >>> bbs controller client path /delete");
        System.out.println("debug >>> id param value " + id);

        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);

        bbsService.delete(map);

        return new ResponseEntity<String>(id+"번째 데이터 삭제 ", HttpStatus.OK);
    }

    /*
    Bbs 목록 조회
    */
    @GetMapping("/lists")
    public ResponseEntity<List<BbsResponseDTO>> getLists() {
        System.out.println("debug >>> bbs controller client path /list");
        List<BbsResponseDTO> list = bbsService.lists();

        return new ResponseEntity<List<BbsResponseDTO>>(list, HttpStatus.OK);
    }
    
    /*
    Bbs 데이터 조회 
    */
    @GetMapping("/list/{id}")
    public ResponseEntity<BbsResponseDTO> getList(@PathVariable("id") Integer id) {
        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);

        BbsResponseDTO response = bbsService.list(map);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    Bbs 새 글을 작성
    user endpoint : http://localhost:7777/api/bbs/post?title=xxxx&content=xxxx
    */
    @PostMapping("/post")
    public ResponseEntity<String> save( @RequestParam("title") String title,
                                        @RequestParam("content") String content) {
        
        BbsRequestDTO params = BbsRequestDTO.builder()
                                    .title(title)
                                    .content(content)
                                    .build();

        System.out.println(">>>>>>>>>>> request dto , " + params);
        return null;
    }
    
    
}
