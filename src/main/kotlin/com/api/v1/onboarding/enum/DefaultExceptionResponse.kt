package com.api.v1.onboarding.enum

enum class DefaultExceptionResponse(
    val code: String,
    val message: String
) {
    ML201("Onboarding-Tattos:201", "Artist ID [%s] doesn't exist"),
    ML202("Onboarding-Tattos:202", "Artist with email [%s] already exist")
}