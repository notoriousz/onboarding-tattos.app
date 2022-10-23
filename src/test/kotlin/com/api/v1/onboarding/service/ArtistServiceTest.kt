package com.api.v1.onboarding.service

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.repository.ArtistRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
class ArtistServiceTest {

    @MockK
    private lateinit var artistRepository: ArtistRepository


    @InjectMockKs
    private lateinit var artistService: ArtistService




    @Test
    fun `should return all artists`() {

        val fakeArtists = listOf(
            buildArtist(),
            buildArtist(),
        )

        every { artistRepository.findAll() } returns fakeArtists

        val artists = artistService.findAllArtists(name = null)

        assertEquals(fakeArtists, artists)

        verify(exactly = 1) { artistRepository.findAll() }
        verify(exactly = 0) { artistRepository.findByNameContaining(any()) }
    }


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


}