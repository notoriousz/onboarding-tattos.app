package com.api.v1.onboarding.controller

import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.service.ArtistService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/artists")
class ArtistController(
    private val artistService: ArtistService
) {

    @GetMapping
    fun findAllArtists(): List<ArtistModel> {
        return artistService.findAllArtists()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewArtist(@RequestBody artistCredentials: ArtistModel) {
        artistService.createNewArtist(artistCredentials)
    }

    @GetMapping("/{id}")
    fun findOneArtist(@PathVariable id: Int): ArtistModel {
        return artistService.findOneArtist(id)
    }

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    fun updateOneArtist(@PathVariable id: Int, @RequestBody artist: ArtistModel) {
//        artistService.updateOneArtist(id, artist)
//    }

//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(NO_CONTENT)
//    fun deleteOneArtist(): MutableList<String> {
//        TODO()
//    }


}