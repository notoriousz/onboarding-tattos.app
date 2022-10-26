package com.api.v1.onboarding.controller.request

import javax.validation.constraints.NotEmpty

data class PutPortfolioRequest(

    @field:NotEmpty
    var type: String,
)

