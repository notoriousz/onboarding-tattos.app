package com.api.v1.onboarding.exception

class BadRequestException(
    override val message: String,
    val errorCode: String
) : Exception(){

}
