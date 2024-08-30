package com.example.springdemo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springdemo.test.domain.BbsRequestDTO;
import com.example.springdemo.test.domain.BbsResponseDTO;
import com.example.springdemo.test.domain.comment.CommentRequestDTO;
import com.example.springdemo.test.domain.comment.CommentResponseDTO;
import com.example.springdemo.test.mapper.BbsMapper;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BbsService {
    
    @Autowired
    private BbsMapper bbsMapper;

    public void save(BbsRequestDTO params) {
        System.out.println("debug >>> service save() " + bbsMapper);
        bbsMapper.saveRow(params);
    }

    public void update(BbsRequestDTO params) {
        System.out.println("debug >>> service update() " + bbsMapper);
        bbsMapper.updateRow(params);
    }

    public void delete(Map<String, Integer> map) {
        System.out.println("debug >>> service delete() " + map.get("id"));
        bbsMapper.deleteRow(map);
    }

    public List<BbsResponseDTO> lists() {
        System.out.println("debug >>> service list() " + bbsMapper);
        List<BbsResponseDTO> lists = bbsMapper.selectRow();

        return lists;
    }

    public Optional<BbsResponseDTO> list(Map<String, Integer> map) {
        System.out.println("debug >>> service list()");
        Optional<BbsResponseDTO> response = bbsMapper.getRow(map);
        System.out.println("debug >>> service list result " + response);
        ArrayList<CommentResponseDTO> list = bbsMapper.commentSelectRow(map);
        if ( response.isPresent()){
            response.get().setComment(list);
        }
        return response;
    }

    public void commentSave(CommentRequestDTO params) {
        System.out.println("debug >>> service commentSave() " + bbsMapper);
        bbsMapper.commentSave(params);
    }
}
