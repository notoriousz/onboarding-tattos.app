package com.api.v1.onboarding.helper

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.enum.PortfolioStatus
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.model.PortfolioModel
import java.util.*

fun buildArtist(
    id: Int? = null,
    name: String = "Artist Name",
    email: String = "${UUID.randomUUID()}@gmail.com",
    address: String = "Rua test 123",
    status: ArtistStatus = ArtistStatus.USER_ACTIVE
) =
    ArtistModel(
        id = id,
        name = name,
        email = email,
        address = address,
        status = status
    )


fun buildPortfolio(
    id: Int? = Random().nextInt(),
    type: String = "GENERIC",
    artist: ArtistModel? = null
) =
    PortfolioModel(
        id = id,
        type = type,
        artist = artist,
        status = PortfolioStatus.P_AVAILABLE
    )
