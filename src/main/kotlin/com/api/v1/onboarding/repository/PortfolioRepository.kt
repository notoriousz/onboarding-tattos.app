package com.api.v1.onboarding.repository

import com.api.v1.onboarding.enum.PortfolioStatus
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.model.PortfolioModel
import org.springframework.data.jpa.repository.JpaRepository

interface PortfolioRepository: JpaRepository<PortfolioModel, Int> {
    fun findAllByStatus(status: PortfolioStatus): List<PortfolioModel>
    fun findByArtist(artist: ArtistModel): List<PortfolioModel>

}
