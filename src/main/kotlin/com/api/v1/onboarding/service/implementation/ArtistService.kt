package com.api.v1.onboarding.service.implementation

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.enum.DefaultExceptionResponse
import com.api.v1.onboarding.exception.BadRequestException
import com.api.v1.onboarding.exception.NotFoundException
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.repository.ArtistRepository
import com.api.v1.onboarding.service.Artist
import org.springframework.stereotype.Service


@Service
class ArtistService (
    private val artistRepository: ArtistRepository,
    private val portfolioService: PortfolioService
) : Artist {

    override fun getAll(name: String?): List<ArtistModel> {

        name?.let { return artistRepository.findByNameContaining(name) }

        return artistRepository.findAll()
    }

    override fun create(artist: ArtistModel) = artistRepository.save(artist)


    override fun getById(id: Int): ArtistModel =
        artistRepository.findById(id)
            .orElseThrow {
                NotFoundException(
                    DefaultExceptionResponse.ML201.message.format(id),
                    DefaultExceptionResponse.ML201.code
                )
            }


    override fun updateArtist(id: Int, artist: ArtistModel) {

        if (emailAvailable(artist.email)) throw BadRequestException(
            DefaultExceptionResponse.ML202.message.format(id),
            DefaultExceptionResponse.ML202.code
        )

        artistRepository.save(artist)
    }

    override fun deleteArtistById(id: Int) {
        val artist = getById(id)

        if(artist.status == ArtistStatus.USER_INACTIVE) throw BadRequestException(
            DefaultExceptionResponse.ML203.message.format(id),
            DefaultExceptionResponse.ML203.code
        )

        portfolioService.deleteByArtist(artist)

        artist.status = ArtistStatus.USER_INACTIVE

        artistRepository.save(artist)

    }


    fun emailAvailable(email: String): Boolean = !artistRepository.existsByEmail(email)

}