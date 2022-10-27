package com.api.v1.onboarding.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.NotEmpty

data class PostImageRequest(

    @JsonAlias("portfolio_id")
    var portfolioId: Int,

    @field:NotEmpty
    @JsonAlias("file_name")
    var fileName: String,

)
