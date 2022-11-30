package com.api.v1.onboarding.controller

import com.api.v1.onboarding.controller.extension.toImageModel
import com.api.v1.onboarding.controller.extension.toResponse
import com.api.v1.onboarding.controller.request.PostImageRequest
import com.api.v1.onboarding.controller.response.ImageResponse
import com.api.v1.onboarding.service.implementation.ImageService
import com.api.v1.onboarding.service.implementation.PortfolioService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/images")
class ImageController(
    private val imageService: ImageService,
    private val portfolioService: PortfolioService
) {


    @GetMapping("/{id}")
    fun findOneImage(@PathVariable id: Int): ImageResponse {
        return imageService.findOneImage(id).toResponse()
    }


    @GetMapping
    fun findAllImages(): List<ImageResponse> {
        return imageService.findAllImages().map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewImage(
        @RequestBody request: PostImageRequest
    ) {
        val portfolio = portfolioService.findOnePortfolio(request.portfolioId)
        imageService.createNewImage(request.toImageModel(portfolio))
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOneImage(
        @PathVariable id: Int
    ) =
        imageService.deleteImageById(id)

}