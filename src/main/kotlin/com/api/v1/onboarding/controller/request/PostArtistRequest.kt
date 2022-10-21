package com.api.v1.onboarding.controller.request

import com.api.v1.onboarding.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostArtistRequest(

    @field:NotEmpty
    var name: String,

    @field:Email
    @EmailAvailable
    var email: String,

    @field:NotEmpty
    var address: String,
)
