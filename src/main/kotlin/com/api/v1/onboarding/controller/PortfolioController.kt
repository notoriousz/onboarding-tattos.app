package com.api.v1.onboarding.controller

import com.api.v1.onboarding.controller.extension.toPortfolioModel
import com.api.v1.onboarding.controller.extension.toResponse
import com.api.v1.onboarding.controller.request.PostPortfolioRequest
import com.api.v1.onboarding.controller.request.PutPortfolioRequest
import com.api.v1.onboarding.controller.response.PortfolioResponse
import com.api.v1.onboarding.service.implementation.ArtistService
import com.api.v1.onboarding.service.implementation.PortfolioService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/portfolios")
class PortfolioController(
    private val portfolioService: PortfolioService,
    private val artistService: ArtistService
) {


    @GetMapping
    fun findAllPortfolios() : List<PortfolioResponse> =
        portfolioService.findAllPortfolios().map { it.toResponse() }

    @GetMapping("/availables")
    fun findALlPortfoliosAvailables() : List<PortfolioResponse> =
        portfolioService.findALlPortfoliosAvailables().map { it.toResponse() }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewPortfolio(
        @RequestBody request: PostPortfolioRequest
    ) {
        val artist = artistService.getById(request.artistId)
        portfolioService.createNewPortfolio(request.toPortfolioModel(artist))
    }

    @GetMapping("/{id}")
    fun findOnePortfolio(@PathVariable id: Int) : PortfolioResponse =
        portfolioService.findOnePortfolio(id).toResponse()



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updatePortfolioType(@RequestBody request: PutPortfolioRequest, @PathVariable id: Int) {
        portfolioService.updatePortfolioType(request, id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOnePortfolio(@PathVariable id:Int) {
        portfolioService.deleteOnePortfolio(id)
    }


}