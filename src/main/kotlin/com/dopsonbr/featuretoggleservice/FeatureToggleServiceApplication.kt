package com.dopsonbr.featuretoggleservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class FeatureToggleServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(FeatureToggleServiceApplication::class.java, *args)
}
