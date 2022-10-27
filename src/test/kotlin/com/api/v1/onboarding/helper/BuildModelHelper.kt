package com.api.v1.onboarding.helper

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.model.ArtistModel
import java.util.*

fun buildArtist(
    id: Int? = null,
    name: String = "Artist Name",
    email: String = "${UUID.randomUUID()}@gmail.com",
    address: String = "Rua test 123"
) =
    ArtistModel(
        id = id,
        name = name,
        email = email,
        address = address,
        status = ArtistStatus.ACTIVE
    )


fun buildPortfolio(
    id: Int? = null,
    name: String = "Artist Name",
    email: String = "${UUID.randomUUID()}@gmail.com",
    address: String = "Rua test 123"
) =
    ArtistModel(
        id = id,
        name = name,
        email = email,
        address = address,
        status = ArtistStatus.ACTIVE
    )
