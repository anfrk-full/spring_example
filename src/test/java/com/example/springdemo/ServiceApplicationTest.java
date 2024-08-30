package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springdemo.test.domain.BbsRequestDTO;
import com.example.springdemo.test.service.BbsService;

@SpringBootTest
public class ServiceApplicationTest {

    @Autowired
    private BbsService bbsService;
    
    @Test
    public void saveService() {
        System.out.println("debug >>>> junit service save" + bbsService);
        BbsRequestDTO request = BbsRequestDTO.builder()
                                    .title("오늘은 쉽다")
                                    .content("진짜 이렇게 간단한거였어~~")
                                    .build();
        bbsService.save(request);
        System.out.println("debug >>>> save OK!");
    }

    /*
    테이블에 있는 기본키값으로 title, content 수정하고 싶다.
    1. BbsRequestDTO 타입으로 더미데이터를 생성하고
    2. bbsService.update(BbsRequestDTO) 메서드의 인자로 전달
    3. update() 메서드에서 mapper 의 updateRow() 정의하고 BbsRequestDTO 전달하고
    4. BbsMapper.xml 에 update 태그를 추가해서
    수정이 구현되도록 실습
    */
    @Test
    public void updateService() {
        System.out.println("debug >>>> junit service update" + bbsService);
        BbsRequestDTO request = BbsRequestDTO.builder()
                                    .id(2)
                                    .title("오늘도 열심히!")
                                    .content("해보자")
                                    .build();
        bbsService.update(request);
        System.out.println("debug >>>> update OK!");
    }
}
