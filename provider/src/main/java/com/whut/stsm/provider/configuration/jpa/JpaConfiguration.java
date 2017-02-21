package com.whut.stsm.provider.configuration.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * jpa扫描Entity和Repositories
 *
 * Created by null on 2017/2/21.
 */
@EntityScan("com.whut.stsm.common.dto")
@EnableJpaRepositories("com.whut.stsm.provider.dao")
@Configuration
public class JpaConfiguration {
}
