package com.api.v1.onboarding.enum

enum class DefaultExceptionResponse(
    val code: String,
    val message: String
) {
    ML201("OT-201", "Artist ID = [%s] not exists"),
    ML202("OT-202", "Artist with email [%s] already exists")
}