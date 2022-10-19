package com.api.v1.onboarding.controller.request

data class PostArtistRequest(
    var name: String,
    var email: String,
    var address: String,
)
