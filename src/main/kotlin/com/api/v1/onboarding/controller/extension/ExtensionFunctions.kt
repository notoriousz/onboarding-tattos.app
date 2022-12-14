package com.api.v1.onboarding.controller.extension

import com.api.v1.onboarding.controller.request.PostArtistRequest
import com.api.v1.onboarding.controller.request.PostImageRequest
import com.api.v1.onboarding.controller.request.PostPortfolioRequest
import com.api.v1.onboarding.controller.request.PutArtistRequest
import com.api.v1.onboarding.controller.response.ArtistResponse
import com.api.v1.onboarding.controller.response.ImageResponse
import com.api.v1.onboarding.controller.response.PortfolioResponse
import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.enum.ImageStatus
import com.api.v1.onboarding.enum.PortfolioStatus
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.model.ImageModel
import com.api.v1.onboarding.model.PortfolioModel


// Artist Extensions

fun PostArtistRequest.toArtistModel(): ArtistModel {
    return ArtistModel(
        name = this.name,
        email = this.email,
        address = this.address,
        status = ArtistStatus.USER_ACTIVE
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


// portfolio extensions

fun PostPortfolioRequest.toPortfolioModel(artist: ArtistModel): PortfolioModel {
    return PortfolioModel(
        type = this.type,
        artist = artist,
        status = PortfolioStatus.P_AVAILABLE,
    )
}


fun PortfolioModel.toResponse(): PortfolioResponse {
    return PortfolioResponse(
        id = this.id,
        type = this.type,
        artist = this.artist,
        status = this.status,
        createdAt = this.createdAt
    )
}



// images extensions


fun PostImageRequest.toImageModel(portfolio: PortfolioModel): ImageModel {
    return ImageModel(
        fileName = this.fileName,
        status = ImageStatus.IMG_AVAILABLE,
        portfolio = portfolio
    )
}


fun ImageModel.toResponse(): ImageResponse {
    return ImageResponse(
        id = this.id,
        fileName = this.fileName,
        filePath = this.filePath,
        status = this.status,
        portfolio = this.portfolio
    )
}