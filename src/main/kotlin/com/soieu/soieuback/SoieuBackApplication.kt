package com.soieu.soieuback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SoieuBackApplication

fun main(args: Array<String>) {
    runApplication<SoieuBackApplication>(*args)
}
