package org.example.integration.dataBase.currency

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyDTO(
    val apiTradeAvailableFlag: Boolean,
    val blockedTcaFlag: Boolean,
    val brand: Long,
    val buyAvailableFlag: Boolean,
    val classCode: String,
    val currency: String,
    val exchange: String,
    val figi: String,
    val forIisFlag: Boolean,
    val forQualInvestorFlag: Boolean,
    val isin: String,
    val isoCurrencyName: String,
    val name: String,
    val otcFlag: Boolean,
    val positionUid: String,
    val sellAvailableFlag: Boolean,
    val shortEnabledFlag: Boolean,
    val ticker: String,
    val uid: String,
    val weekendFlag: Boolean,
)
