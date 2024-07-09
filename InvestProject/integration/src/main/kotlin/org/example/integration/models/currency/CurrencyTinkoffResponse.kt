package org.example.integration.models.currency

import kotlinx.serialization.Serializable
import org.example.integration.dataBase.currency.CurrencyDTO
import org.example.integration.models.BrandTinkoffResponse
import org.example.integration.models.MinPriceIncrementTinkoffResponse
import org.example.integration.models.NominalTinkoffResponse

@Serializable
data class CurrencyTinkoffResponse(
    val apiTradeAvailableFlag: Boolean,
    val blockedTcaFlag: Boolean,
    val brand: BrandTinkoffResponse,
    val buyAvailableFlag: Boolean,
    val classCode: String,
    val countryOfRisk: String,
    val countryOfRiskName: String,
    val currency: String,
    val exchange: String,
    val figi: String,
//    val first1dayCandleDate: String?,
//    val first1minCandleDate: String?,
    val forIisFlag: Boolean,
    val forQualInvestorFlag: Boolean,
    val isin: String,
    val isoCurrencyName: String,
    val lot: Int,
    val minPriceIncrement: MinPriceIncrementTinkoffResponse,
    val name: String,
    val nominal: NominalTinkoffResponse,
    val otcFlag: Boolean,
    val positionUid: String,
    val realExchange: String,
    val sellAvailableFlag: Boolean,
    val shortEnabledFlag: Boolean,
    val ticker: String,
    val tradingStatus: String,
    val uid: String,
    val weekendFlag: Boolean,
)

fun CurrencyTinkoffResponse.toCurrencyDTO(brandId: Long): CurrencyDTO =
    CurrencyDTO(
        apiTradeAvailableFlag = this.apiTradeAvailableFlag,
        blockedTcaFlag = this.blockedTcaFlag,
        brand = brandId,
        buyAvailableFlag = this.buyAvailableFlag,
        classCode = this.classCode,
        currency = this.currency,
        exchange = this.exchange,
        figi = this.figi,
        forIisFlag = this.forIisFlag,
        forQualInvestorFlag = this.forQualInvestorFlag,
        isin = this.isin,
        isoCurrencyName = this.isoCurrencyName,
        name = this.name,
        otcFlag = this.otcFlag,
        positionUid = this.positionUid,
        sellAvailableFlag = this.sellAvailableFlag,
        shortEnabledFlag = this.shortEnabledFlag,
        ticker = this.ticker,
        uid = this.uid,
        weekendFlag = this.weekendFlag,
    )
