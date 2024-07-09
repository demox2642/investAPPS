package org.example.integration.dataBase.futures

data class FuturesDTO(
    val figi: String,
    val ticker: String,
    val classCode: String,
    val lot: Long,
    val currency: String,
//    val klong: Double,
//    val kshort: Double,
//    val dlong: Double,
//    val dshort: Double,
//    val dlongMin: Double,
//    val dshortMin: Double,
    val shortEnabledFlag: Boolean,
    val name: String,
    val exchange: String,
    val firstTradeDate: String,
    val lastTradeDate: String,
    val futuresType: String,
    val assetType: String,
    val basicAsset: String,
    val basicAssetSize: Double,
    val countryOfRisk: String,
    val countryOfRiskName: String,
    val sector: String,
    val expirationDate: String,
    val tradingStatusId: Long,
    val otcFlag: Boolean,
    val buyAvailableFlag: Boolean,
    val sellAvailableFlag: Boolean,
    val minPriceIncrement: Double,
    val apiTradeAvailableFlag: Boolean,
    val uid: String,
    val realExchangeId: Long,
    val positionUid: String,
    val basicAssetPositionUid: String,
    val forIisFlag: Boolean,
    val forQualInvestorFlag: Boolean,
    val weekendFlag: Boolean,
    val blockedTcaFlag: Boolean,
    val first1minCandleDate: String,
    val first1dayCandleDate: String,
    val initialMarginOnBuy_currency_name: String,
    val initialMarginOnBuy: Double,
    val initialMarginOnSell_currency_name: String,
    val initialMarginOnSell: Double,
    val minPriceIncrementAmount: Double,
    val brand_id: Long,
)
