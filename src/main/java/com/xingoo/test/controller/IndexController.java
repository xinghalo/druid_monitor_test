package com.xingoo.test.controller;

import com.xingoo.test.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/1")
    public List<Map<String, Object>> test(String a){
        return indexService.query1(a);
    }

    @GetMapping("/2")
    public List<Map<String, Object>> test2(String a){
        return indexService.query2(a);
    }
}
