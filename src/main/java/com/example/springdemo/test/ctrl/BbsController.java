package com.example.springdemo.test.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdemo.test.domain.BbsRequestDTO;
import com.example.springdemo.test.domain.BbsResponseDTO;
import com.example.springdemo.test.domain.comment.CommentRequestDTO;
import com.example.springdemo.test.service.BbsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





//비동기 통신을 사용 할 때
@RestController
// user : http://localhost:7777/api/bbs
@RequestMapping("api/bbs")
@Tag(name="BBS API" , description = "게시글 관련 API 목록")

public class BbsController {
    
    @Autowired
    private BbsService bbsService;

    // user : http://localhost:7777/api/bbs/test
    //@RequestMapping("/test") //GET, POST 전부 적용
    //@PostMapping("/test") // POST 만 적용
    @GetMapping("/test") // GET 만 적용
    public ResponseEntity<BbsResponseDTO> test() {
        /*
        BbsResponseDTO response = BbsResponseDTO.builder()
                                    .id(1)
                                    .title("title")
                                    .content("content")
                                    .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
        */
        return null;
    }

    /*
    파라미터로 전달되는 id에 해당하는 데이터를 삭제한다면?
    */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "게시글 삭제" , description = "게시글 키값을 가지고 삭제한다")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        System.out.println("debug >>> bbs controller client path /delete");
        System.out.println("debug >>> id param value " + id);

        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);

        bbsService.delete(map);
        return new ResponseEntity<String>("삭제되었습니다.", HttpStatus.OK);
    }

    /*
    Bbs 목록 조회
    */
    @GetMapping("/lists")
    @Operation(summary = "전체 리스트 보기" , description = "전체 리스트를 보여준다")
    public ResponseEntity<List<BbsResponseDTO>> getLists() {
        System.out.println("debug >>> bbs controller client path /list");
        List<BbsResponseDTO> list = bbsService.lists();

        return new ResponseEntity<List<BbsResponseDTO>>(list, HttpStatus.OK);
    }
    
    /*
    Bbs 데이터 조회 
    */
    @GetMapping("/list/{id}")
    @Operation(summary = "한 개 리스트 보기" , description = "키 값을 가지고 한 개의 리스트를 보여준다")
    public ResponseEntity<Object> getList(@PathVariable("id") Integer id) {

        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        Optional<BbsResponseDTO> response = bbsService.list(map);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당하는 값이 없습니다.", HttpStatus.OK);
        }   
    }

    /*
    Bbs 새 글을 작성
    user endpoint : http://localhost:7777/api/bbs/post?title=xxxx&content=xxxx
    */
    /*
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
    */

    @PostMapping("/post")
    @Operation(summary = "게시글 추가" , description = "게시글을 추가한다.")
    public ResponseEntity<Void> save(BbsRequestDTO params) {
        System.out.println("debug >>> bbs controller client path /post");
        System.out.println(">>>>>>>>>>> request dto , " + params);
        bbsService.save(params);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "게시글 변경" , description = "게시글을 변경한다")
    public ResponseEntity<String> update(BbsRequestDTO params) {
        System.out.println("debug >>> bbs controller client path /path");
        System.out.println(">>>>>>>>>>> request dto , " + params);
        bbsService.update(params);
        return new ResponseEntity<String>("내용이 변경되었습니다.", HttpStatus.OK);
    }
    
    /////////////// comment
    /*
    comment 새 글을 작성
    user endpoint : http://localhost:7777/api/bbs/comment/post?content=xxxx&bbsid=xxxx
    */

    @PostMapping("/comment/save")
    @Operation(summary = "댓글 추가" , description = "댓글을 추가한다.")
    public ResponseEntity<String> commentSave(CommentRequestDTO params) {
        System.out.println("debug >>> bbs controller client path /comment/post");
        System.out.println(">>>>>>>>>>> request dto , " + params);

        bbsService.commentSave(params);
        return new ResponseEntity<String>("추가되었습니다.", HttpStatus.OK);
    }

    
    
}
