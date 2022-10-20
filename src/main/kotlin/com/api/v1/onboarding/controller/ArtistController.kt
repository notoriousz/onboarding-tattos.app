package com.api.v1.onboarding.controller

import com.api.v1.onboarding.controller.extension.toArtistModel
import com.api.v1.onboarding.controller.extension.toResponse
import com.api.v1.onboarding.controller.request.PostArtistRequest
import com.api.v1.onboarding.controller.request.PutArtistRequest
import com.api.v1.onboarding.controller.response.ArtistResponse
import com.api.v1.onboarding.service.ArtistService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/artists")
class ArtistController(
    private val artistService: ArtistService
) {

    @GetMapping
    fun findAllArtists(): List<ArtistResponse> =
        artistService.findAllArtists().map { it.toResponse() }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewArtist(@RequestBody artistCredentials: PostArtistRequest) {
        artistService.createNewArtist(artistCredentials.toArtistModel())
    }

    @GetMapping("/{id}")
    fun findOneArtist(@PathVariable id: Int): ArtistResponse =
        artistService.findOneArtist(id).toResponse()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateOneArtist(@PathVariable id: Int, @RequestBody artist: PutArtistRequest) {
        val currentArtist = artistService.findOneArtist(id)
        artistService.updateOneArtist(id, artist.toArtistModel(currentArtist))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOneArtist(@PathVariable id: Int) =
        artistService.deleteArtistById(id)


}