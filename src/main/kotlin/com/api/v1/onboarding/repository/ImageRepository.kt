package com.api.v1.onboarding.repository

import com.api.v1.onboarding.model.ImageModel
import com.api.v1.onboarding.model.PortfolioModel
import org.springframework.data.jpa.repository.JpaRepository


interface ImageRepository : JpaRepository<ImageModel, Int>{

    fun findByPortfolio(portfolio: PortfolioModel): List<ImageModel>

}
