package com.api.v1.onboarding.service

import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.repository.ArtistRepository
import org.springframework.stereotype.Service


@Service
class ArtistService(
    private val artistRepository: ArtistRepository
) {

    fun findAllArtists(): MutableList<ArtistModel> {
        return artistRepository.findAll()
    }

    fun createNewArtist(artistCredentials: ArtistModel) {
        try {
            artistRepository.save(artistCredentials)
        } catch (Ex: Exception) {
            throw Exception(Ex)
        }
    }

    fun findOneArtist(id: Int): ArtistModel {
        return artistRepository.findById(id)
            .orElseThrow { Exception("Usuario não encontrado") }
    }

    fun updateOneArtist(id: Int, artist: ArtistModel) {

        if (!artistRepository.existsById(id)) {
            throw Exception("Usuario não encontrado")
        }

        artistRepository.save(artist)
    }

}
