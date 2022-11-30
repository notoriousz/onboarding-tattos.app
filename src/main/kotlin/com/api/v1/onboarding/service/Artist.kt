package com.api.v1.onboarding.service

import com.api.v1.onboarding.model.ArtistModel

interface Artist {

    fun getAll(name: String?): List<ArtistModel>

    fun create(artist: ArtistModel) : ArtistModel

    fun getById(id: Int): ArtistModel

    fun updateArtist(id: Int, artist: ArtistModel)

    fun deleteArtistById(id: Int)

}