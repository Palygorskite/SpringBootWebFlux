package com.githun.client.api;

import com.githun.client.annotation.ApiServer;
import com.githun.client.pojo.UserFlux;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 创建时间为 下午4:43-2019/2/11
 * 项目名称 SpringBootWebFlux
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@ApiServer("http://127.0.0.1:8080/user")
public interface UserApi {

    @GetMapping("/")
    Flux<UserFlux> getAll();

    @GetMapping("/{id}")
    Mono<UserFlux> getUserByID(@PathVariable String id);

    @PostMapping("/")
    Mono<UserFlux> save(@RequestBody Mono<UserFlux> user);

}

