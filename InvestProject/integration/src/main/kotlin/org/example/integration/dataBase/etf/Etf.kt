package org.example.integration.dataBase.etf

import org.example.integration.utils.Settings
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.demox_bank.utils.ResponseDB
import ru.demox_bank.utils.ResponseError
import utils.Response

object Etf : Table("etf") {
    private val figi = Etf.varchar("figi", Settings.DataBase.StringLenth)
    private val ticker = Etf.varchar("ticker", Settings.DataBase.StringLenth)
    private val classCode = Etf.varchar("classCode", Settings.DataBase.StringLenth)
    private val isin = Etf.varchar("isin", Settings.DataBase.StringLenth)
    private val lot = Etf.long("lot")
    private val currency = Etf.varchar("currency", Settings.DataBase.StringLenth)
    private val shortEnabledFlag = Etf.bool("shortEnabledFlag")
    private val name = Etf.varchar("name", Settings.DataBase.StringLenth)
    private val exchange = Etf.varchar("exchange", Settings.DataBase.StringLenth)
    private val focusType = Etf.varchar("focusType", Settings.DataBase.StringLenth)
    private val countryOfRisk = Etf.varchar("countryOfRisk", Settings.DataBase.StringLenth)
    private val countryOfRiskName = Etf.varchar("countryOfRiskName", Settings.DataBase.StringLenth)
    private val sector = Etf.varchar("sector", Settings.DataBase.StringLenth)
    private val rebalancingFreq = Etf.varchar("rebalancingFreq", Settings.DataBase.StringLenth)
    private val otcFlag = Etf.bool("otcFlag")
    private val buyAvailableFlag = Etf.bool("buyAvailableFlag")
    private val sellAvailableFlag = Etf.bool("sellAvailableFlag")
    private val minPriceIncrement_units = Etf.varchar("minPriceIncrement_units", Settings.DataBase.StringLenth)
    private val minPriceIncrement_nano = Etf.long("minPriceIncrement_nano")
    private val apiTradeAvailableFlag = Etf.bool("apiTradeAvailableFlag")
    private val uid = Etf.varchar("uid", Settings.DataBase.StringLenth)
    private val realExchange = Etf.varchar("realExchange", Settings.DataBase.StringLenth)
    private val positionUid = Etf.varchar("positionUid", Settings.DataBase.StringLenth)
    private val assetUid = Etf.varchar("assetUid", Settings.DataBase.StringLenth)
    private val forIisFlag = Etf.bool("forIisFlag")
    private val forQualInvestorFlag = Etf.bool("forQualInvestorFlag")
    private val weekendFlag = Etf.bool("weekendFlag")
    private val blockedTcaFlag = Etf.bool("blockedTcaFlag")
    private val liquidityFlag = Etf.bool("liquidityFlag")
    private val brand_id = Etf.long("brand_id")
    private val tradingStatusId = Etf.long("tradingstatus_id")
    private val realExchangeId = Etf.long("realexchange_id")

    fun insertEtf(etfDTO: EtfDTO): ResponseDB =
        try {
            transaction {
                Etf.insert {
                    it[figi] = etfDTO.figi
                    it[ticker] = etfDTO.ticker
                    it[classCode] = etfDTO.classCode
                    it[isin] = etfDTO.isin
                    it[lot] = etfDTO.lot
                    it[currency] = etfDTO.currency
                    it[shortEnabledFlag] = etfDTO.shortEnabledFlag
                    it[name] = etfDTO.name
                    it[exchange] = etfDTO.exchange
                    it[focusType] = etfDTO.focusType
                    it[countryOfRisk] = etfDTO.countryOfRisk
                    it[countryOfRiskName] = etfDTO.countryOfRiskName
                    it[sector] = etfDTO.sector
                    it[rebalancingFreq] = etfDTO.rebalancingFreq
                    it[otcFlag] = etfDTO.otcFlag
                    it[buyAvailableFlag] = etfDTO.buyAvailableFlag
                    it[sellAvailableFlag] = etfDTO.sellAvailableFlag
                    it[minPriceIncrement_units] = etfDTO.minPriceIncrement_units ?: ""
                    it[minPriceIncrement_nano] = etfDTO.minPriceIncrement_nano ?: 0
                    it[apiTradeAvailableFlag] = etfDTO.apiTradeAvailableFlag
                    it[uid] = etfDTO.uid
                    it[realExchange] = etfDTO.realExchange
                    it[positionUid] = etfDTO.positionUid
                    it[assetUid] = etfDTO.assetUid
                    it[forIisFlag] = etfDTO.forIisFlag
                    it[forQualInvestorFlag] = etfDTO.forQualInvestorFlag
                    it[weekendFlag] = etfDTO.weekendFlag
                    it[blockedTcaFlag] = etfDTO.blockedTcaFlag
                    it[liquidityFlag] = etfDTO.liquidityFlag
                    it[brand_id] = etfDTO.brand_id
                    it[tradingStatusId] = etfDTO.tradingStatusId
                    it[realExchangeId] = etfDTO.realExchangeId
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun updateEtf(etfDTO: EtfDTO): ResponseDB =
        try {
            transaction {
                Etf.update({ uid eq etfDTO.uid }) {
                    it[figi] = etfDTO.figi
                    it[ticker] = etfDTO.ticker
                    it[classCode] = etfDTO.classCode
                    it[isin] = etfDTO.isin
                    it[lot] = etfDTO.lot
                    it[currency] = etfDTO.currency
                    it[shortEnabledFlag] = etfDTO.shortEnabledFlag
                    it[name] = etfDTO.name
                    it[exchange] = etfDTO.exchange
                    it[focusType] = etfDTO.focusType
                    it[countryOfRisk] = etfDTO.countryOfRisk
                    it[countryOfRiskName] = etfDTO.countryOfRiskName
                    it[sector] = etfDTO.sector
                    it[rebalancingFreq] = etfDTO.rebalancingFreq
                    it[otcFlag] = etfDTO.otcFlag
                    it[buyAvailableFlag] = etfDTO.buyAvailableFlag
                    it[sellAvailableFlag] = etfDTO.sellAvailableFlag
                    it[minPriceIncrement_units] = etfDTO.minPriceIncrement_units ?: ""
                    it[minPriceIncrement_nano] = etfDTO.minPriceIncrement_nano ?: 0
                    it[apiTradeAvailableFlag] = etfDTO.apiTradeAvailableFlag
                    it[realExchange] = etfDTO.realExchange
                    it[positionUid] = etfDTO.positionUid
                    it[assetUid] = etfDTO.assetUid
                    it[forIisFlag] = etfDTO.forIisFlag
                    it[forQualInvestorFlag] = etfDTO.forQualInvestorFlag
                    it[weekendFlag] = etfDTO.weekendFlag
                    it[blockedTcaFlag] = etfDTO.blockedTcaFlag
                    it[liquidityFlag] = etfDTO.liquidityFlag
                    it[brand_id] = etfDTO.brand_id
                    it[tradingStatusId] = etfDTO.tradingStatusId
                    it[realExchangeId] = etfDTO.realExchangeId
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun getEtf(uid: String): Response<EtfDTO> =
        try {
            transaction {
                val value = Etf.select { Etf.uid.eq(uid) }.single()

                Response(
                    success = true,
                    response =
                        EtfDTO(
                            figi = value[figi],
                            ticker = value[ticker],
                            classCode = value[classCode],
                            isin = value[isin],
                            lot = value[lot],
                            currency = value[currency],
                            shortEnabledFlag = value[shortEnabledFlag],
                            name = value[name],
                            exchange = value[exchange],
                            focusType = value[focusType],
                            countryOfRisk = value[countryOfRisk],
                            countryOfRiskName = value[countryOfRiskName],
                            sector = value[sector],
                            rebalancingFreq = value[rebalancingFreq],
                            otcFlag = value[otcFlag],
                            buyAvailableFlag = value[buyAvailableFlag],
                            sellAvailableFlag = value[sellAvailableFlag],
                            minPriceIncrement_units = value[minPriceIncrement_units],
                            minPriceIncrement_nano = value[minPriceIncrement_nano],
                            apiTradeAvailableFlag = value[apiTradeAvailableFlag],
                            uid = uid,
                            realExchange = value[realExchange],
                            positionUid = value[positionUid],
                            assetUid = value[assetUid],
                            forIisFlag = value[forIisFlag],
                            forQualInvestorFlag = value[forQualInvestorFlag],
                            weekendFlag = value[weekendFlag],
                            blockedTcaFlag = value[blockedTcaFlag],
                            liquidityFlag = value[liquidityFlag],
                            brand_id = value[brand_id],
                            tradingStatusId = value[tradingStatusId],
                            realExchangeId = value[realExchangeId],
                        ),
                )
            }
        } catch (e: Exception) {
            Response(
                success = false,
                error = ResponseError(errorCode = 42, errorMessage = e.message.toString()),
            )
        }

    fun equalsEtfDTO(etfDTO: EtfDTO) {
        val databaseSearch = getEtf(etfDTO.uid)
        val result =
            if (databaseSearch.isSuccess) {
                updateEtf(etfDTO)
            } else {
                insertEtf(etfDTO)
            }

        println(result)
    }

    fun getEtfList(): Response<List<EtfDTO>> =
        transaction {
            val value = Etf.selectAll().toList()
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
                            EtfDTO(
                                figi = value[figi],
                                ticker = value[ticker],
                                classCode = value[classCode],
                                isin = value[isin],
                                lot = value[lot],
                                currency = value[currency],
                                shortEnabledFlag = value[shortEnabledFlag],
                                name = value[name],
                                exchange = value[exchange],
                                focusType = value[focusType],
                                countryOfRisk = value[countryOfRisk],
                                countryOfRiskName = value[countryOfRiskName],
                                sector = value[sector],
                                rebalancingFreq = value[rebalancingFreq],
                                otcFlag = value[otcFlag],
                                buyAvailableFlag = value[buyAvailableFlag],
                                sellAvailableFlag = value[sellAvailableFlag],
                                minPriceIncrement_units = value[minPriceIncrement_units],
                                minPriceIncrement_nano = value[minPriceIncrement_nano],
                                apiTradeAvailableFlag = value[apiTradeAvailableFlag],
                                uid = value[uid],
                                realExchange = value[realExchange],
                                positionUid = value[positionUid],
                                assetUid = value[assetUid],
                                forIisFlag = value[forIisFlag],
                                forQualInvestorFlag = value[forQualInvestorFlag],
                                weekendFlag = value[weekendFlag],
                                blockedTcaFlag = value[blockedTcaFlag],
                                liquidityFlag = value[liquidityFlag],
                                brand_id = value[brand_id],
                                tradingStatusId = value[tradingStatusId],
                                realExchangeId = value[realExchangeId],
                            )
                        },
                )
            }
        }
}
