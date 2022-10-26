package com.api.v1.onboarding.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.NotEmpty

data class PostPortfolioRequest(

    @JsonAlias("artist_id")
    var artistId: Int,

    @field:NotEmpty
    var type: String? = "GENERIC"

)
