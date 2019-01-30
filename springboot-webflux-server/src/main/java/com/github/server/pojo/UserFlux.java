package com.github.server.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * 创建时间为 下午2:56-2019/1/29
 * 项目名称 SpringBootWebFlux
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
@Document(collection = "UserFlux")
public class UserFlux {

    @Id
    private String id;

    private String name;

    @Range(min = 10, max = 20)
    private int age;

}
