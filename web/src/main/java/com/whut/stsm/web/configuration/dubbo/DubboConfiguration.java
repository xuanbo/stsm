package com.whut.stsm.web.configuration.dubbo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by null on 2017/2/20.
 */
@Configuration
@ImportResource(locations = {"classpath:dubbo-spring.xml"})
public class DubboConfiguration {
}
