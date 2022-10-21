package com.api.v1.onboarding.repository

import com.api.v1.onboarding.model.ArtistModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArtistRepository : JpaRepository<ArtistModel, Int> {



    fun findByNameContaining(pageable: Pageable, name: String?): Page<ArtistModel>

    fun existsByEmail(string: String): Boolean

}
