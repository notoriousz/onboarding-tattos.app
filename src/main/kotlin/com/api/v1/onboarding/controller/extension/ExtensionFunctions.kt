package com.api.v1.onboarding.controller.extension

import com.api.v1.onboarding.controller.request.PostArtistRequest
import com.api.v1.onboarding.controller.request.PutArtistRequest
import com.api.v1.onboarding.controller.response.ArtistResponse
import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.model.ArtistModel



// Artist Extensions

fun PostArtistRequest.toArtistModel(): ArtistModel {
    return ArtistModel(
        name = this.name,
        email = this.email,
        address = this.address,
        status = ArtistStatus.ACTIVE
    )
}

fun PutArtistRequest.toArtistModel(currentArtist: ArtistModel): ArtistModel {
    return ArtistModel(
        id = currentArtist.id,
        name = this.name,
        email = this.email,
        address = this.address,
        status = currentArtist.status,
        createdAt = currentArtist.createdAt
    )
}


fun ArtistModel.toResponse(): ArtistResponse {
    return ArtistResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        address = this.address,
        status = this.status
    )
}

