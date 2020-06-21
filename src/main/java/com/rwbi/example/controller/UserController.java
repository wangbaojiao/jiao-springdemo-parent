package com.rwbi.example.controller;

import com.github.pagehelper.PageInfo;
import com.rwbi.example.model.User;
import com.rwbi.example.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author ：wangbaojiao
 * @date ：Created in 6/19/2020 9:49 AM
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IUserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("list")
    public PageInfo<User> getUserList(@RequestParam(defaultValue = "") String searchName,
                                      @RequestParam(defaultValue = "0") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize) {
        return userService.getUserList(searchName, pageNum, pageSize);
    }

    @GetMapping("remove-cache")
    public String removeCache() {
        Object o = applicationContext.getBean("cacheManager");
        userService.removeCache();
        return "success";
    }

    @GetMapping("not-understand")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public String notUnderstand() {
        log.info("无法解析");
        return "无法解析";
    }

    @GetMapping("test-template")
    public PageInfo<User> testTemplate() {
        String[] requestParams = {"0","1"};

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/user/list")
                .build(requestParams);
        RequestEntity<Void> req = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .build();
        PageInfo<User> userPageInfo = new PageInfo<>();

        ParameterizedTypeReference<PageInfo<User>> ptr =
                new ParameterizedTypeReference<PageInfo<User>>() {};
        ResponseEntity<String> resp2 = restTemplate.getForEntity("http://localhost:8080/user/list", String.class);
        ResponseEntity<PageInfo<User>> resp = restTemplate
                .exchange(uri, HttpMethod.GET, null, ptr);
        log.info("Response Status: {}, Response Headers: {}", resp.getStatusCode(), resp.getHeaders().toString());
        log.info("PageInfo<User>: {}", resp.getBody());
        return resp.getBody();
    }

}
