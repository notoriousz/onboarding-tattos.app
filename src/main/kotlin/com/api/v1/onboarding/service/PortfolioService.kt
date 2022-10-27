package com.api.v1.onboarding.service

import com.api.v1.onboarding.controller.request.PutPortfolioRequest
import com.api.v1.onboarding.enum.PortfolioStatus
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.model.PortfolioModel
import com.api.v1.onboarding.repository.PortfolioRepository
import org.springframework.stereotype.Service

@Service
class PortfolioService(
    private val portfolioRepository: PortfolioRepository,
    private val imageService: ImageService
) {

    fun createNewPortfolio(portfolio: PortfolioModel) {
        portfolioRepository.save(portfolio)
    }

    fun findAllPortfolios(): List<PortfolioModel> =
        portfolioRepository.findAll()
            .toList()

    fun findALlPortfoliosAvailables(): List<PortfolioModel> =
        portfolioRepository.findAllByStatus(PortfolioStatus.P_AVAILABLE)


    fun findOnePortfolio(id: Int): PortfolioModel =
        portfolioRepository.findById(id).orElseThrow { Exception("Portfolio not found")}


    fun updatePortfolioType(request: PutPortfolioRequest, id: Int) {
        val portfolio = findOnePortfolio(id)

        portfolio.type = request.type

        portfolioRepository.save(portfolio)
    }

    fun deleteOnePortfolio(id: Int) {
        val portfolio = findOnePortfolio(id)

        imageService.deleteByPortfolio(portfolio)

        portfolio.status = PortfolioStatus.P_DELETED

        portfolioRepository.save(portfolio)
    }

    fun deleteByArtist(artist: ArtistModel) {
        val portfolios = portfolioRepository.findByArtist(artist)

        // delete all images
        for (portfolio in portfolios) {
            imageService.deleteByPortfolio(portfolio)
        }

        // delete all portfolios
        for (portfolio in portfolios) {
            portfolio.status = PortfolioStatus.P_DELETED
        }

        portfolioRepository.saveAll(portfolios)

    }

}
