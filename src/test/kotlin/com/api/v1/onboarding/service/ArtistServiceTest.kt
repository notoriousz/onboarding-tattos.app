package com.api.v1.onboarding.service

import com.api.v1.onboarding.enum.ArtistStatus
import com.api.v1.onboarding.exception.BadRequestException
import com.api.v1.onboarding.exception.NotFoundException
import com.api.v1.onboarding.helper.buildArtist
import com.api.v1.onboarding.repository.ArtistRepository
import com.api.v1.onboarding.service.implementation.ArtistService
import com.api.v1.onboarding.service.implementation.PortfolioService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class ArtistServiceTest {

    @MockK
    private lateinit var artistRepository: ArtistRepository

    @MockK
    private lateinit var portfolioService: PortfolioService

    @InjectMockKs
    @SpyK
    private lateinit var artistService: ArtistService




    @Test
    fun `should return all artists`() {

        val fakeArtists = listOf(
            buildArtist(),
            buildArtist(),
        )

        every { artistRepository.findAll() } returns fakeArtists

        val artists = artistService.getAll(name = null)

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

        val artists = artistService.getAll(name)

        assertEquals(fakeArtists, artists)

        verify(exactly = 0) { artistRepository.findAll() }
        verify(exactly = 1) { artistRepository.findByNameContaining(any()) }
    }

    @Test
    fun `should create a artist`() {

        val fakeArtist = buildArtist()

        every { artistRepository.save(fakeArtist) } returns fakeArtist

        artistService.create(fakeArtist)

        verify(exactly = 1) { artistRepository.save(fakeArtist)  }
    }


    @Test
    fun `should return artist by id`() {
        val id = Random().nextInt()
        val fakeArtist = buildArtist(id = id)

        every { artistRepository.findById(id) } returns Optional.of(fakeArtist)

        val customer = artistService.getById(id)

        assertEquals(fakeArtist, customer)
        verify(exactly = 1) { artistRepository.findById(id) }
    }

    @Test
    fun `should throw a exception if a artist does not exist`() {
        val id = Random().nextInt()

        every { artistRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException>{ artistService.getById(id) }

        assertEquals("Artist ID [${id}] doesn't exist", error.message)
        assertEquals("Onboarding-Tattos:201", error.errorCode)

        verify(exactly = 1) { artistRepository.findById(id) }
    }

    @Test
    fun `should update a artist still registered`() {
        val id = Random().nextInt()
        val fakeArtist = buildArtist(id = id)

        every { artistService.emailAvailable(fakeArtist.email) } returns false
        every { artistRepository.save(fakeArtist) } returns fakeArtist

        artistService.updateArtist(id ,fakeArtist)

        verify(exactly = 1) { artistRepository.save(fakeArtist) }
        verify(exactly = 1) { artistService.emailAvailable(fakeArtist.email) }

    }

    @Test
    fun `should throw an exception when try update a non exist artist`() {
        val id = Random().nextInt()
        val fakeArtist = buildArtist(id = id)

        every { artistService.emailAvailable(fakeArtist.email) } returns true
        every { artistRepository.save(fakeArtist) } returns fakeArtist

        val error = assertThrows<BadRequestException>{ artistService.updateArtist(id, fakeArtist) }

        assertEquals("Artist with email [${id}] already exist", error.message)
        assertEquals("Onboarding-Tattos:202", error.errorCode)

        verify(exactly = 1) { artistService.emailAvailable(fakeArtist.email)  }
        verify(exactly = 0) { artistRepository.save(any()) }
    }

    @Test
    fun `should delete a artist and all portfolios`() {
        val id = Random().nextInt()
        val fakeArtist = buildArtist(id = id)
        val expectedDeletedArtist = fakeArtist.copy(status = ArtistStatus.USER_INACTIVE)

        every { artistService.getById(id) } returns fakeArtist
        every { portfolioService.deleteByArtist(fakeArtist) } just Runs
        every { artistRepository.save(expectedDeletedArtist) } returns expectedDeletedArtist

        artistService.deleteArtistById(id)

        assertEquals(expectedDeletedArtist.status, fakeArtist.status)

        verify(exactly = 1) { portfolioService.deleteByArtist(fakeArtist) }
        verify(exactly = 1) { artistRepository.save(expectedDeletedArtist) }
        verify(exactly = 1) { artistService.getById(id) }
    }


    @Test
    fun `should exists by email`() {
        val email = "${UUID.randomUUID()}@gmail.com"
        val fakeArtist = buildArtist(email = email)

        every { artistRepository.existsByEmail(fakeArtist.email) } returns true

        val exists = artistService.emailAvailable(email)

        assertEquals(false, exists)

        verify(exactly = 1) { artistRepository.existsByEmail(email) }
    }

    @Test
    fun `should not exists by email`() {
        val email = "${UUID.randomUUID()}@gmail.com"

        every { artistRepository.existsByEmail(email) } returns false

        val exists = artistService.emailAvailable(email)

        assertEquals(true, exists)

        verify(exactly = 1) { artistRepository.existsByEmail(email) }
    }


}