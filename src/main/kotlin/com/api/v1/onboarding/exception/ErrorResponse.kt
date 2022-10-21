package com.api.v1.onboarding.exception

data class ErrorResponse(
    val message: String?,
    val status: Int,
    val internalCode: String,
    val error: String?
)
