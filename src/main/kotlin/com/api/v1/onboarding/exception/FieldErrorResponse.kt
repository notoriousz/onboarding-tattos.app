package com.api.v1.onboarding.exception

data class FieldErrorResponse(
    var message: String,
    var field: String
)
