package org.example.integration.models.futures

import kotlinx.serialization.Serializable
import utils.DoubleData
import utils.convertToDouble

@Serializable
data class MinPriceIncrementAmount(
    override val nano: Long,
    override val units: String,
) : DoubleData {
    override fun convert() = this.convertToDouble()
}
