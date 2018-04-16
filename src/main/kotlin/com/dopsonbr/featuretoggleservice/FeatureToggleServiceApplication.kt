package com.dopsonbr.featuretoggleservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import io.micrometer.core.instrument.config.MeterFilter.commonTags
import io.micrometer.spring.autoconfigure.MeterRegistryConfigurer
import org.springframework.context.annotation.Bean


@SpringBootApplication
class FeatureToggleServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(FeatureToggleServiceApplication::class.java, *args)
}
