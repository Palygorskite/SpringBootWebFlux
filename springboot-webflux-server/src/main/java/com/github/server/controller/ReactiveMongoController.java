package com.github.server.controller;

import com.github.server.pojo.UserFlux;
import com.github.server.repository.UserFluxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 创建时间为 下午3:25-2019/1/29
 * 项目名称 SpringBootWebFlux
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@RestController
public class ReactiveMongoController {

    private final UserFluxRepository repository;

    public ReactiveMongoController(UserFluxRepository repository) {
        this.repository = repository;
    }

    @GetMapping("user")
    public Flux<UserFlux> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "user/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserFlux> getStreamAll() {
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public Mono<ResponseEntity<UserFlux>> findById(@PathVariable String id) {
        return repository.findById(id)
            .map(one -> new ResponseEntity<UserFlux>(HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("user")
    public Mono<UserFlux> save(@RequestBody UserFlux userFlux) {
        return repository.save(userFlux);
    }



    /**
     * @param id       id
     * @param userFlux 数据
     * @return 返回修改过后的值
     */
    @PutMapping("/user/{id}")
    public Mono<ResponseEntity<UserFlux>> updateUser(@PathVariable String id, @RequestBody UserFlux userFlux) {
        return repository.findById(id)
            // 操作数据
            .flatMap(one -> {
                one.setName(userFlux.getName());
                return repository.save(one);
            })
            // 转换数据
            .map(one -> new ResponseEntity<>(one, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 没有返回值,不能使用 deleteById,不能判断是否存在
     * repository.deleteById(id);
     * <p>
     * 当要操作数据时,并返回一个Mono这个时候使用 flatMap
     * 如果不操作数据,只是转换数据,使用 map
     *
     * @param id id
     * @return 返回结果
     */
    @DeleteMapping("user/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
        return repository.findById(id)
            .flatMap(user -> repository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
