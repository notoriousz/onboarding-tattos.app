package com.api.v1.onboarding.repository

import com.api.v1.onboarding.model.PortfolioModel
import org.springframework.data.jpa.repository.JpaRepository

interface PortfolioRepository: JpaRepository<PortfolioModel, Int> {
}
