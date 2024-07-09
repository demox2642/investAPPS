package org.example.integration.models

import kotlinx.serialization.Serializable
import org.example.integration.dataBase.brand.BrandDTO

@Serializable
data class BrandTinkoffResponse(
    val logoBaseColor: String,
    val logoName: String,
    val textColor: String,
)

fun BrandTinkoffResponse.toBrandDTO(): BrandDTO =
    BrandDTO(
        logoName = this.logoName,
        logoBaseColor = this.logoBaseColor,
        textColor = this.textColor,
    )
