package com.api.v1.onboarding.model

import com.api.v1.onboarding.enum.ArtistStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "artist")
data class ArtistModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    var address: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: ArtistStatus?,

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()

)
