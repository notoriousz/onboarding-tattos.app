package com.api.v1.onboarding.service

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.enum.DefaultExceptionResponse
import com.api.v1.onboarding.exception.NotFoundException
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.repository.ArtistRepository
import org.springframework.stereotype.Service


@Service
class ArtistService(
    private val artistRepository: ArtistRepository,
    private val portfolioService: PortfolioService
) {

    fun findAllArtists(name: String?): List<ArtistModel> {

        name?.let {
            return artistRepository.findByNameContaining(name)
        }

        return artistRepository.findAll()
    }



    fun createNewArtist(artist: ArtistModel) =
        artistRepository.save(artist)

    fun findOneArtist(id: Int): ArtistModel =
        artistRepository.findById(id)
            .orElseThrow {
                NotFoundException(
                    DefaultExceptionResponse.ML201.message.format(id),
                    DefaultExceptionResponse.ML201.code
                )
            }


    fun updateOneArtist(id: Int, artist: ArtistModel) {

        if (!artistRepository.existsById(id)) {
            throw Exception("Not Found the user [${id}]")
        }

        artistRepository.save(artist)
    }

    fun deleteArtistById(id: Int) {
        val artist = findOneArtist(id)

        portfolioService.deleteByArtist(artist)

        artist.status = ArtistStatus.INACTIVE

        artistRepository.save(artist)

    }

    fun emailAvailable(email: String): Boolean = !artistRepository.existsByEmail(email)


}
