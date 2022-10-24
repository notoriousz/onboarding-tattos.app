package com.api.v1.onboarding.service

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.exception.NotFoundException
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.repository.ArtistRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

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

    @Test
    fun `should return all artists with the query param name`() {
        val name = UUID.randomUUID().toString()
        val fakeArtists = listOf(
            buildArtist(),
            buildArtist()
        )

        every { artistRepository.findByNameContaining(name) } returns fakeArtists

        val artists = artistService.findAllArtists(name)

        assertEquals(fakeArtists, artists)

        verify(exactly = 0) { artistRepository.findAll() }
        verify(exactly = 1) { artistRepository.findByNameContaining(any()) }
    }

    @Test
    fun `should create a artist`() {

        val fakeArtist = buildArtist()

        every { artistRepository.save(fakeArtist) } returns fakeArtist

        artistService.createNewArtist(fakeArtist)

        verify(exactly = 1) { artistRepository.save(fakeArtist)  }
    }


    @Test
    fun `should return artist by id`() {
        val id = Random().nextInt()
        val fakeArtist = buildArtist(id = id)

        every { artistRepository.findById(id) } returns Optional.of(fakeArtist)

        val customer = artistService.findOneArtist(id)

        assertEquals(fakeArtist, customer)
        verify(exactly = 1) { artistRepository.findById(id) }
    }

    @Test
    fun `should throw a exception if a artist does not exist`() {
        val id = Random().nextInt()

        every { artistRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException>{ artistService.findOneArtist(id) }

        assertEquals("Artist ID = [${id}] not exists", error.message)
        assertEquals("OT-201", error.errorCode)

        verify(exactly = 1) { artistRepository.findById(id) }
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