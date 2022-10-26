package com.api.v1.onboarding.controller.response

import com.api.v1.onboarding.enum.PortfolioStatus
import com.api.v1.onboarding.model.ArtistModel
import java.time.LocalDateTime

data class PortfolioResponse(
    var id: Int?,
    var type: String?,
    var artist: ArtistModel?,
    var status: PortfolioStatus?,
    var createdAt: LocalDateTime
)
