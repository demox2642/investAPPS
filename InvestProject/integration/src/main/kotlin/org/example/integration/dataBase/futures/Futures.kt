package org.example.integration.dataBase.futures

import org.example.integration.utils.Settings
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.demox_bank.utils.ResponseDB
import ru.demox_bank.utils.ResponseError
import utils.Response

object Futures : Table("futures") {
    private val figi = Futures.varchar("figi", Settings.DataBase.StringLenth)
    private val ticker = Futures.varchar("ticker", Settings.DataBase.StringLenth)
    private val classCode = Futures.varchar("classCode", Settings.DataBase.StringLenth)
    private val lot = Futures.long("lot")
    private val currency_uid = Futures.varchar("currency_uid", Settings.DataBase.StringLenth)
    private val klong = Futures.double("klong")
    private val kshort = Futures.double("kshort")
    private val dlong = Futures.double("dlong")
    private val dshort = Futures.double("dshort")
    private val dlongMin = Futures.double("dlongMin")
    private val dshortMin = Futures.double("dshortMin")
    private val shortEnabledFlag = Futures.bool("shortEnabledFlag")
    private val name = Futures.varchar("name", Settings.DataBase.StringLenth)
    private val exchange = Futures.varchar("exchange", Settings.DataBase.StringLenth)
    private val firstTradeDate = Futures.varchar("firstTradeDate", Settings.DataBase.StringLenth)
    private val lastTradeDate = Futures.varchar("lastTradeDate", Settings.DataBase.StringLenth)
    private val futuresType = Futures.varchar("futuresType", Settings.DataBase.StringLenth)
    private val assetType = Futures.varchar("assetType", Settings.DataBase.StringLenth)
    private val basicAsset = Futures.varchar("basicAsset", Settings.DataBase.StringLenth)
    private val basicAssetSize = Futures.double("basicAssetSize")
    private val countryOfRisk = Futures.varchar("countryOfRisk", Settings.DataBase.StringLenth)
    private val countryOfRiskName = Futures.varchar("countryOfRiskName", Settings.DataBase.StringLenth)
    private val sector = Futures.varchar("sector", Settings.DataBase.StringLenth)
    private val expirationDate = Futures.varchar("expirationDate", Settings.DataBase.StringLenth)
    private val tradingStatus = Futures.long("tradingStatus")
    private val otcFlag = Futures.bool("otcFlag")
    private val buyAvailableFlag = Futures.bool("buyAvailableFlag")
    private val sellAvailableFlag = Futures.bool("sellAvailableFlag")
    private val minPriceIncrement = Futures.double("minPriceIncrement")
    private val apiTradeAvailableFlag = Futures.bool("apiTradeAvailableFlag")
    private val uid = Futures.varchar("uid", Settings.DataBase.StringLenth)
    private val realExchange = Futures.long("realExchange")
    private val positionUid = Futures.varchar("positionUid", Settings.DataBase.StringLenth)
    private val basicAssetPositionUid = Futures.varchar("basicAssetPositionUid", Settings.DataBase.StringLenth)
    private val forIisFlag = Futures.bool("forIisFlag")
    private val forQualInvestorFlag = Futures.bool("forQualInvestorFlag")
    private val weekendFlag = Futures.bool("weekendFlag")
    private val blockedTcaFlag = Futures.bool("blockedTcaFlag")
    private val first1minCandleDate = Futures.varchar("first1minCandleDate", Settings.DataBase.StringLenth)
    private val first1dayCandleDate = Futures.varchar("first1dayCandleDate", Settings.DataBase.StringLenth)
    private val initialMarginOnBuy_currency_name = Futures.varchar("initialMarginOnBuy_currency_name", Settings.DataBase.StringLenth)
    private val initialMarginOnBuy = Futures.double("initialMarginOnBuy")
    private val initialMarginOnSell_currency_name = Futures.varchar("initialMarginOnSell_currency_name", Settings.DataBase.StringLenth)
    private val initialMarginOnSell = Futures.double("initialMarginOnSell")
    private val minPriceIncrementAmount = Futures.double("minPriceIncrementAmount")
    private val brand_id = Futures.long("brand_id")

    fun insertFutures(futuresDTO: FuturesDTO): ResponseDB =
        try {
            transaction {
                Futures.insert {
                    it[figi] = futuresDTO.figi
                    it[ticker] = futuresDTO.ticker
                    it[classCode] = futuresDTO.classCode
                    it[lot] = futuresDTO.lot
                    it[currency_uid] = futuresDTO.currency
                    it[klong] = 0.0
                    it[kshort] = 0.0
                    it[dlong] = 0.0 // futuresDTO.dlong
                    it[dshort] = 0.0 // futuresDTO.dshort
                    it[dlongMin] = 0.0 // futuresDTO.dlongMin
                    it[dshortMin] = 0.0 // futuresDTO.dshortMin
                    it[shortEnabledFlag] = futuresDTO.shortEnabledFlag
                    it[name] = futuresDTO.name
                    it[exchange] = futuresDTO.exchange
                    it[firstTradeDate] = futuresDTO.firstTradeDate
                    it[lastTradeDate] = futuresDTO.lastTradeDate
                    it[futuresType] = futuresDTO.futuresType
                    it[assetType] = futuresDTO.assetType
                    it[basicAsset] = futuresDTO.basicAsset
                    it[basicAssetSize] = futuresDTO.basicAssetSize
                    it[countryOfRisk] = futuresDTO.countryOfRisk
                    it[countryOfRiskName] = futuresDTO.countryOfRiskName
                    it[sector] = futuresDTO.sector
                    it[expirationDate] = futuresDTO.expirationDate
                    it[tradingStatus] = futuresDTO.tradingStatusId
                    it[otcFlag] = futuresDTO.otcFlag
                    it[buyAvailableFlag] = futuresDTO.buyAvailableFlag
                    it[sellAvailableFlag] = futuresDTO.sellAvailableFlag
                    it[minPriceIncrement] = futuresDTO.minPriceIncrement
                    it[apiTradeAvailableFlag] = futuresDTO.apiTradeAvailableFlag
                    it[uid] = futuresDTO.uid
                    it[realExchange] = futuresDTO.realExchangeId
                    it[positionUid] = futuresDTO.positionUid
                    it[basicAssetPositionUid] = futuresDTO.basicAssetPositionUid
                    it[forIisFlag] = futuresDTO.forIisFlag
                    it[forQualInvestorFlag] = futuresDTO.forQualInvestorFlag
                    it[weekendFlag] = futuresDTO.weekendFlag
                    it[blockedTcaFlag] = futuresDTO.blockedTcaFlag
                    it[first1minCandleDate] = futuresDTO.first1minCandleDate
                    it[first1dayCandleDate] = futuresDTO.first1dayCandleDate
                    it[initialMarginOnBuy_currency_name] = futuresDTO.initialMarginOnBuy_currency_name
                    it[initialMarginOnBuy] = futuresDTO.initialMarginOnBuy
                    it[initialMarginOnSell_currency_name] = futuresDTO.initialMarginOnSell_currency_name
                    it[initialMarginOnSell] = futuresDTO.initialMarginOnSell
                    it[minPriceIncrementAmount] = futuresDTO.minPriceIncrementAmount
                    it[brand_id] = futuresDTO.brand_id
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun updateFutures(futuresDTO: FuturesDTO): ResponseDB =
        try {
            transaction {
                Futures.update({ uid eq futuresDTO.uid }) {
                    it[figi] = futuresDTO.figi
                    it[ticker] = futuresDTO.ticker
                    it[classCode] = futuresDTO.classCode
                    it[lot] = futuresDTO.lot
                    it[currency_uid] = futuresDTO.currency
                    it[klong] = 0.0
                    it[kshort] = 0.0
                    it[dlong] = 0.0 // futuresDTO.dlong
                    it[dshort] = 0.0 // futuresDTO.dshort
                    it[dlongMin] = 0.0 // futuresDTO.dlongMin
                    it[dshortMin] = 0.0 // futuresDTO.dshortMin
                    it[shortEnabledFlag] = futuresDTO.shortEnabledFlag
                    it[name] = futuresDTO.name
                    it[exchange] = futuresDTO.exchange
                    it[firstTradeDate] = futuresDTO.firstTradeDate
                    it[lastTradeDate] = futuresDTO.lastTradeDate
                    it[futuresType] = futuresDTO.futuresType
                    it[assetType] = futuresDTO.assetType
                    it[basicAsset] = futuresDTO.basicAsset
                    it[basicAssetSize] = futuresDTO.basicAssetSize
                    it[countryOfRisk] = futuresDTO.countryOfRisk
                    it[countryOfRiskName] = futuresDTO.countryOfRiskName
                    it[sector] = futuresDTO.sector
                    it[expirationDate] = futuresDTO.expirationDate
                    it[tradingStatus] = futuresDTO.tradingStatusId
                    it[otcFlag] = futuresDTO.otcFlag
                    it[buyAvailableFlag] = futuresDTO.buyAvailableFlag
                    it[sellAvailableFlag] = futuresDTO.sellAvailableFlag
                    it[minPriceIncrement] = futuresDTO.minPriceIncrement
                    it[apiTradeAvailableFlag] = futuresDTO.apiTradeAvailableFlag
                    it[uid] = futuresDTO.uid
                    it[realExchange] = futuresDTO.realExchangeId
                    it[positionUid] = futuresDTO.positionUid
                    it[basicAssetPositionUid] = futuresDTO.basicAssetPositionUid
                    it[forIisFlag] = futuresDTO.forIisFlag
                    it[forQualInvestorFlag] = futuresDTO.forQualInvestorFlag
                    it[weekendFlag] = futuresDTO.weekendFlag
                    it[blockedTcaFlag] = futuresDTO.blockedTcaFlag
                    it[first1minCandleDate] = futuresDTO.first1minCandleDate
                    it[first1dayCandleDate] = futuresDTO.first1dayCandleDate
                    it[initialMarginOnBuy_currency_name] = futuresDTO.initialMarginOnBuy_currency_name
                    it[initialMarginOnBuy] = futuresDTO.initialMarginOnBuy
                    it[initialMarginOnSell_currency_name] = futuresDTO.initialMarginOnSell_currency_name
                    it[initialMarginOnSell] = futuresDTO.initialMarginOnSell
                    it[minPriceIncrementAmount] = futuresDTO.minPriceIncrementAmount
                    it[brand_id] = futuresDTO.brand_id
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun getFutures(uid: String): Response<FuturesDTO> =
        try {
            transaction {
                val value = Futures.select { Futures.uid.eq(uid) }.single()

                Response(
                    success = true,
                    response =
                        FuturesDTO(
                            figi = value[figi],
                            ticker = value[ticker],
                            classCode = value[classCode],
                            lot = value[lot],
                            currency = value[currency_uid],
//                            klong = value[klong],
//                            kshort = value[kshort],
//                            dlong = value[dlong],
//                            dshort = value[dshort],
//                            dlongMin = value[dlongMin],
//                            dshortMin = value[dshortMin],
                            shortEnabledFlag = value[shortEnabledFlag],
                            name = value[name],
                            exchange = value[exchange],
                            firstTradeDate = value[firstTradeDate],
                            lastTradeDate = value[lastTradeDate],
                            futuresType = value[futuresType],
                            assetType = value[assetType],
                            basicAsset = value[basicAsset],
                            basicAssetSize = value[basicAssetSize],
                            countryOfRisk = value[countryOfRisk],
                            countryOfRiskName = value[countryOfRiskName],
                            sector = value[sector],
                            expirationDate = value[expirationDate],
                            tradingStatusId = value[tradingStatus],
                            otcFlag = value[otcFlag],
                            buyAvailableFlag = value[buyAvailableFlag],
                            sellAvailableFlag = value[sellAvailableFlag],
                            minPriceIncrement = value[minPriceIncrement],
                            apiTradeAvailableFlag = value[apiTradeAvailableFlag],
                            uid = value[Futures.uid],
                            realExchangeId = value[realExchange],
                            positionUid = value[positionUid],
                            basicAssetPositionUid = value[basicAssetPositionUid],
                            forIisFlag = value[forIisFlag],
                            forQualInvestorFlag = value[forQualInvestorFlag],
                            weekendFlag = value[weekendFlag],
                            blockedTcaFlag = value[blockedTcaFlag],
                            first1minCandleDate = value[first1minCandleDate],
                            first1dayCandleDate = value[first1dayCandleDate],
                            initialMarginOnBuy_currency_name = value[initialMarginOnBuy_currency_name],
                            initialMarginOnBuy = value[initialMarginOnBuy],
                            initialMarginOnSell_currency_name = value[initialMarginOnSell_currency_name],
                            initialMarginOnSell = value[initialMarginOnSell],
                            minPriceIncrementAmount = value[minPriceIncrementAmount],
                            brand_id = value[brand_id],
                        ),
                )
            }
        } catch (e: Exception) {
            Response(
                success = false,
                error = ResponseError(errorCode = 42, errorMessage = e.message.toString()),
            )
        }

    fun equalsFuturesDTO(futuresDTO: FuturesDTO) {
        val databaseSearch = getFutures(futuresDTO.uid)
        val result =
            if (databaseSearch.isSuccess) {
                updateFutures(futuresDTO)
            } else {
                insertFutures(futuresDTO)
            }

        println(result)
    }

    fun getFuturesList(): Response<List<FuturesDTO>> =
        transaction {
            val value = Futures.selectAll().toList()
            if (value.isEmpty()) {
                Response(
                    success = true,
                    response = emptyList(),
                )
            } else {
                Response(
                    success = true,
                    response =
                        value.map { value ->
                            FuturesDTO(
                                figi = value[figi],
                                ticker = value[ticker],
                                classCode = value[classCode],
                                lot = value[lot],
                                currency = value[currency_uid],
//                                klong = value[klong],
//                                kshort = value[kshort],
//                                dlong = value[dlong],
//                                dshort = value[dshort],
//                                dlongMin = value[dlongMin],
//                                dshortMin = value[dshortMin],
                                shortEnabledFlag = value[shortEnabledFlag],
                                name = value[name],
                                exchange = value[exchange],
                                firstTradeDate = value[firstTradeDate],
                                lastTradeDate = value[lastTradeDate],
                                futuresType = value[futuresType],
                                assetType = value[assetType],
                                basicAsset = value[basicAsset],
                                basicAssetSize = value[basicAssetSize],
                                countryOfRisk = value[countryOfRisk],
                                countryOfRiskName = value[countryOfRiskName],
                                sector = value[sector],
                                expirationDate = value[expirationDate],
                                tradingStatusId = value[tradingStatus],
                                otcFlag = value[otcFlag],
                                buyAvailableFlag = value[buyAvailableFlag],
                                sellAvailableFlag = value[sellAvailableFlag],
                                minPriceIncrement = value[minPriceIncrement],
                                apiTradeAvailableFlag = value[apiTradeAvailableFlag],
                                uid = value[uid],
                                realExchangeId = value[realExchange],
                                positionUid = value[positionUid],
                                basicAssetPositionUid = value[basicAssetPositionUid],
                                forIisFlag = value[forIisFlag],
                                forQualInvestorFlag = value[forQualInvestorFlag],
                                weekendFlag = value[weekendFlag],
                                blockedTcaFlag = value[blockedTcaFlag],
                                first1minCandleDate = value[first1minCandleDate],
                                first1dayCandleDate = value[first1dayCandleDate],
                                initialMarginOnBuy_currency_name = value[initialMarginOnBuy_currency_name],
                                initialMarginOnBuy = value[initialMarginOnBuy],
                                initialMarginOnSell_currency_name = value[initialMarginOnSell_currency_name],
                                initialMarginOnSell = value[initialMarginOnSell],
                                minPriceIncrementAmount = value[minPriceIncrementAmount],
                                brand_id = value[brand_id],
                            )
                        },
                )
            }
        }
}
