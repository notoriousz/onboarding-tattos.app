package com.api.v1.onboarding.model

import com.api.v1.onboarding.enum.PortfolioStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "portfolio")
data class PortfolioModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var type: String?,

    @Column
    @Enumerated(EnumType.STRING)
    var status: PortfolioStatus?,

    @ManyToOne
    @JoinColumn(name= "artist_id")
    var artist: ArtistModel? = null,


    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()
)
