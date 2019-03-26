package com.githun.client.controller;

import com.githun.client.api.UserApi;
import com.githun.client.pojo.UserFlux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

/**
 * <p>
 * 创建时间为 下午5:09-2019/2/11
 * 项目名称 SpringBootWebFlux
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@RestController
public class UserController {

    @Autowired
    private UserApi userApi;

    @GetMapping("/")
    public Flux<UserFlux> getAll() {
        return userApi.getAll();
    }




}
