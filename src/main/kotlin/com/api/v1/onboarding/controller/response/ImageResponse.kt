package com.api.v1.onboarding.controller.response

import com.api.v1.onboarding.enum.ImageStatus
import com.api.v1.onboarding.model.PortfolioModel

data class ImageResponse(

    var id: Int?,

    var fileName: String,

    var filePath: String,

    var status: ImageStatus?,

    var portfolio: PortfolioModel?,

)
