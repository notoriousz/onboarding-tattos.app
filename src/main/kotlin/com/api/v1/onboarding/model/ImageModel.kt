package com.api.v1.onboarding.model

import com.api.v1.onboarding.enum.ImageStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "image")
data class ImageModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var fileName: String,

    @Column
    var filePath: String = "",

    @Column
    @Enumerated(EnumType.STRING)
    var status: ImageStatus?,

    @ManyToOne
    @JoinColumn(name= "portfolio_id")
    var portfolio: PortfolioModel? = null,

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()
)
