package org.example.integration.models.futures

import kotlinx.serialization.Serializable

@Serializable
data class Kshort(
    val nano: Int,
    val units: String,
) {
    fun convert(): Double =
        try {
            (this.units + "." + this.nano.toString()).toDouble()
        } catch (e: Exception) {
            println("DoubleData.convert() ERROR: $e convert is $this")
            0.0
        }
}
