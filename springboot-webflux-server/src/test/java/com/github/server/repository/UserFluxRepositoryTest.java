package com.github.server.repository;

import com.github.server.pojo.UserFlux;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;


/**
 * <p>
 * 创建时间为 下午2:52-2019/1/31
 * 项目名称 SpringBootWebFlux
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFluxRepositoryTest {

    @Autowired
    private UserFluxRepository repository;

    @Test
    @SneakyThrows({Exception.class})
    public void save() {
        Mono<UserFlux> mono = repository.save(getUserFlux());
        mono.block();
//        mono.then().block();
    }

    private UserFlux getUserFlux() {
        UserFlux userFlux = new UserFlux();
        userFlux.setAge(2);
        userFlux.setName("name");
        return userFlux;
    }

}