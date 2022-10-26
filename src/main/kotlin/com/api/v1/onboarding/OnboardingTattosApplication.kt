package com.api.v1.onboarding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class OnboardingTattosApplication

fun main(args: Array<String>) {
    runApplication<OnboardingTattosApplication>(*args)
}
