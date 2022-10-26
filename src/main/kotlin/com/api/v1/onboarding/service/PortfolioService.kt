package com.api.v1.onboarding.service

import com.api.v1.onboarding.controller.request.PutPortfolioRequest
import com.api.v1.onboarding.enum.PortfolioStatus
import com.api.v1.onboarding.model.PortfolioModel
import com.api.v1.onboarding.repository.PortfolioRepository
import org.springframework.stereotype.Service

@Service
class PortfolioService(
    private val portfolioRepository: PortfolioRepository
) {

    fun createNewPortfolio(portfolio: PortfolioModel) {
        portfolioRepository.save(portfolio)
    }

    fun findAllPortfolios(): List<PortfolioModel> =
        portfolioRepository.findAll().toList()


    fun findOnePortfolio(id: Int): PortfolioModel =
        portfolioRepository.findById(id).orElseThrow { Exception("Portfolio not found")}


    fun updatePortfolioType(request: PutPortfolioRequest, id: Int) {
        val portfolio = findOnePortfolio(id)
        portfolio.type = request.type
        portfolioRepository.save(portfolio)
    }

    fun deleteOnePortfolio(id: Int) {
        val portfolio = findOnePortfolio(id)
        portfolio.status = PortfolioStatus.P_DELETED
        portfolioRepository.save(portfolio)
    }

}
