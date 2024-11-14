package org.example.integration.models.futures

import kotlinx.serialization.Serializable
import org.example.integration.utils.DoubleData
import org.example.integration.utils.convertToDouble

@Serializable
data class Dlong(
    override val nano: Long,
    override val units: String,
) : DoubleData {
    override fun convert() = this.convertToDouble()
}
