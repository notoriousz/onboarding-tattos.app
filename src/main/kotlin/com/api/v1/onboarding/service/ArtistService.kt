package com.api.v1.onboarding.service

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.repository.ArtistRepository
import org.springframework.stereotype.Service


@Service
class ArtistService(
    private val artistRepository: ArtistRepository
) {

    fun findAllArtists(): MutableList<ArtistModel> =
        artistRepository.findAll()


    fun createNewArtist(artist: ArtistModel) {
        try {
            artistRepository.save(artist)
        } catch (Ex: Exception) {
            throw Exception(Ex)
        }
    }

    fun findOneArtist(id: Int): ArtistModel =
        artistRepository.findById(id)
            .orElseThrow { Exception("Usuario não encontrado") }


    fun updateOneArtist(id: Int, artist: ArtistModel) {

        if (!artistRepository.existsById(id)) {
            throw Exception("Usuario não encontrado")
        }

        artistRepository.save(artist)
    }

    fun deleteArtistById(id: Int) {
        val artist = findOneArtist(id)
        artist.status = ArtistStatus.INACTIVE
        artistRepository.save(artist)
    }

    fun emailAvailable(email: String): Boolean {
        return !artistRepository.existsByEmail(email)
    }

}
