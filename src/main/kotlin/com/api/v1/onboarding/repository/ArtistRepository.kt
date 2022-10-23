package com.api.v1.onboarding.repository

import com.api.v1.onboarding.model.ArtistModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArtistRepository : JpaRepository<ArtistModel, Int> {



    fun findByNameContaining(name: String?): List<ArtistModel>

    fun existsByEmail(string: String): Boolean

}
