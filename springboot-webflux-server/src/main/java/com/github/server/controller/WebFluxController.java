package com.github.server.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>
 * 创建时间为 下午2:58-2019/1/29
 * 项目名称 SpringBootWebFlux
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@RestController
public class WebFluxController {


    @GetMapping("1")
    public String getMvc() {
        log.info("getMvc start!");
        String result = createString();
        log.info("getMvc end!");
        return result;
    }


    /**
     * Mono 表示 0-1 个元素
     * Flux 表示 0-N 个元素
     *
     * @return Mono
     */
    @GetMapping("2")
    private Mono<String> getMono() {
        log.info("getMono start!");
        Mono<String> result = Mono.fromSupplier(this::createString);
        log.info("getMono end!");
        return result;
    }


    @GetMapping(value = "3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        return Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "flux data--" + i;
            }
        ));
    }


    @SneakyThrows(InterruptedException.class)
    private String createString() {
        TimeUnit.SECONDS.sleep(5);
        return "Some String";
    }


}
