package com.api.v1.onboarding.service.implementation

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.enum.DefaultExceptionResponse
import com.api.v1.onboarding.exception.NotFoundException
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.repository.ArtistRepository
import com.api.v1.onboarding.service.Artist
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class ArtistService (
    private val artistRepository: ArtistRepository,
    private val portfolioService: PortfolioService
) : Artist {

    override fun getAll(name: String?): List<ArtistModel> {

        name?.let {

            logger.info { "Polling all Artists filtering by [$name]" }

            return artistRepository.findByNameContaining(name)
        }

        logger.info { "Polling all Artists" }

        return artistRepository.findAll()
    }


    override fun create(artist: ArtistModel) {
        logger.info { "Creating new Artist [${artist.name}]"}
        artistRepository.save(artist)
    }


    override fun getById(id: Int): ArtistModel =
        artistRepository.findById(id)
            .orElseThrow {
                NotFoundException(
                    DefaultExceptionResponse.ML201.message.format(id),
                    DefaultExceptionResponse.ML201.code
                )
            }


    override fun updateArtist(id: Int, artist: ArtistModel) {

        if (!artistRepository.existsById(id)) {
            throw Exception("Not Found the user [${id}]")
        }

        logger.info { "Updating Artist [${artist.toString()}]"}

        artistRepository.save(artist)
    }

    override fun deleteArtistById(id: Int) {
        val artist = getById(id)

        portfolioService.deleteByArtist(artist)

        artist.status = ArtistStatus.INACTIVE

        artistRepository.save(artist)

        logger.info { "The Artist ${artist.name} and all portfolios related was deleted"}

    }


    fun emailAvailable(email: String): Boolean = !artistRepository.existsByEmail(email)

}