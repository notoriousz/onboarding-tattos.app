package com.api.v1.onboarding.controller.response

import com.api.v1.onboarding.enum.ArtistStatus


data class ArtistResponse(

    var id: Int? = null,

    var name: String,

    var email: String,

    var address: String,

    var status: ArtistStatus?

)
