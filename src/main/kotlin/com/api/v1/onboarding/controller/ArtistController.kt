package com.api.v1.onboarding.controller

import com.api.v1.onboarding.controller.extension.toArtistModel
import com.api.v1.onboarding.controller.extension.toResponse
import com.api.v1.onboarding.controller.request.PostArtistRequest
import com.api.v1.onboarding.controller.request.PutArtistRequest
import com.api.v1.onboarding.controller.response.ArtistResponse
import com.api.v1.onboarding.service.implementation.ArtistService
import mu.KotlinLogging
import mu.withLoggingContext
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("api/v1/artists")
class ArtistController(
    private val artistService: ArtistService
) {

    @GetMapping
    fun getAll(
        @RequestParam name: String?
    ): List<ArtistResponse> {

        logger.info { "Polling all artists" }

        return artistService.getAll(name)
            .map { it.toResponse() }

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody @Valid artistCredentials: PostArtistRequest
    ) {
        logger.info { "Creating new Artist [${artistCredentials.name}]"}

        artistService.create(artistCredentials.toArtistModel())
    }


    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Int
    ): ArtistResponse  {

        logger.info { "Pulling a artist" }

        return artistService.getById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateArtist(
        @PathVariable id: Int,
        @RequestBody artist: PutArtistRequest
    ) {

        logger.info { "Updating Artist [${artist.toString()}]"}

        val currentArtist = artistService.getById(id)

        artistService.updateArtist(id, artist.toArtistModel(currentArtist))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteArtist(
        @PathVariable id: Int
    ) {
        artistService.deleteArtistById(id)

        logger.info { "The Artist related and all portfolios was deleted"}
    }


}