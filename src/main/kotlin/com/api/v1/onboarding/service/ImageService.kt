package com.api.v1.onboarding.service

import com.api.v1.onboarding.enum.ImageStatus
import com.api.v1.onboarding.enum.PortfolioStatus
import com.api.v1.onboarding.model.ArtistModel
import com.api.v1.onboarding.model.ImageModel
import com.api.v1.onboarding.model.PortfolioModel
import com.api.v1.onboarding.repository.ImageRepository
import org.springframework.stereotype.Service

@Service
class ImageService(
    private val imageRepository: ImageRepository
) {

    fun findAllImages(): MutableList<ImageModel> {
        return imageRepository.findAll()
    }

    fun createNewImage(image: ImageModel) {
        imageRepository.save(image)
    }

    fun findOneImage(id: Int): ImageModel {
        return imageRepository.findById(id).orElseThrow{ throw Exception("Not Found User")}
    }

    fun deleteImageById(id: Int) {
        val image = findOneImage(id)
        image.status = ImageStatus.IMG_DELETED
        imageRepository.save(image)
    }

    fun deleteByPortfolio(portfolio: PortfolioModel) {
        val images = imageRepository.findByPortfolio(portfolio)

        for (image in images) {
            image.status = ImageStatus.IMG_DELETED
        }

        imageRepository.saveAll(images)

    }
}
