package com.api.v1.onboarding.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty


data class PutArtistRequest(

    @field:NotEmpty
    var name: String,

    @field:Email
    var email: String,

    @field:NotEmpty
    var address: String,

)