package com.api.v1.onboarding.exception


class NotFoundException(
    override val message: String,
    val errorCode: String
) : RuntimeException()