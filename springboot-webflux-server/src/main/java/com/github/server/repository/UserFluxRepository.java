package com.github.server.repository;

import com.github.server.pojo.UserFlux;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * <p>
 * 创建时间为 下午3:16-2019/1/29
 * 项目名称 SpringBootWebFlux
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


public interface UserFluxRepository extends ReactiveMongoRepository<UserFlux,String> {

}
