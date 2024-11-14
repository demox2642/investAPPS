package org.example.integration.dataBase.shares

import org.example.integration.utils.Settings
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import ru.demox_bank.utils.ResponseDB
import ru.demox_bank.utils.ResponseError
import utils.Response

object Shares : Table("shares") {
    private val apiTradeAvailableFlag = Shares.bool("apiTradeAvailableFlag")
    private val assetUid = Shares.varchar("assetuid", Settings.DataBase.StringLenth)
    private val blockedTcaFlag = Shares.bool("blockedtcaflag")
    private val brand_id = Shares.long("brand_id")
    private val buyAvailableFlag = Shares.bool("buyavailableflag")
    private val classCode = Shares.varchar("classcode", Settings.DataBase.StringLenth)
    private val countryOfRisk = Shares.varchar("countryofrisk", Settings.DataBase.StringLenth)
    private val countryOfRiskName = Shares.varchar("countryofriskname", Settings.DataBase.StringLenth)
    private val currency = Shares.varchar("currency", Settings.DataBase.StringLenth)
    private val divYieldFlag = Shares.bool("divyieldflag")
    private val exchange = Shares.varchar("exchange", Settings.DataBase.StringLenth)
    private val figi = Shares.varchar("figi", Settings.DataBase.StringLenth)
    private val forIisFlag = Shares.bool("foriisflag")
    private val forQualInvestorFlag = Shares.bool("forqualinvestorflag")
    private val instrumentExchange = Shares.varchar("instrumentexchange", Settings.DataBase.StringLenth)
    private val ipoDate = Shares.date("ipodate")
    private val isin = Shares.varchar("isin", Settings.DataBase.StringLenth)
    private val issueSize = Shares.varchar("issuesize", Settings.DataBase.StringLenth)
    private val issueSizePlan = Shares.varchar("issuesizeplan", Settings.DataBase.StringLenth)
    private val liquidityFlag = Shares.bool("liquidityflag")
    private val lot = Shares.integer("lot")
    private val name = Shares.varchar("name", Settings.DataBase.StringLenth)
    private val nominal_units = Shares.varchar("nominal_units", Settings.DataBase.StringLenth)
    private val nominal_nano = Shares.long("nominal_nano")
    private val otcFlag = Shares.bool("otcflag")
    private val positionUid = Shares.varchar("positionuid", Settings.DataBase.StringLenth)
    private val realexchange_id = Shares.long("realexchange_id")
    private val sector = Shares.varchar("sector", Settings.DataBase.StringLenth)
    private val sellAvailableFlag = Shares.bool("sellavailableflag")
    private val shareType = Shares.varchar("sharetype", Settings.DataBase.StringLenth)
    private val shortEnabledFlag = Shares.bool("shortenabledflag")
    private val ticker = Shares.varchar("ticker", Settings.DataBase.StringLenth)
    private val tradingstatus_id = Shares.long("tradingstatus_id")
    private val uid = Shares.varchar("uid", Settings.DataBase.StringLenth)
    private val weekendFlag = Shares.bool("weekendflag")

    fun insertShares(sharesDTO: SharesDTO): ResponseDB =
        try {
            transaction {
                Shares.insert {
                    it[apiTradeAvailableFlag] = sharesDTO.apiTradeAvailableFlag
                    it[assetUid] = sharesDTO.assetUid
                    it[blockedTcaFlag] = sharesDTO.blockedTcaFlag
                    it[brand_id] = sharesDTO.brand_id
                    it[buyAvailableFlag] = sharesDTO.buyAvailableFlag
                    it[classCode] = sharesDTO.classCode
                    it[countryOfRisk] = sharesDTO.countryOfRisk
                    it[countryOfRiskName] = sharesDTO.countryOfRiskName
                    it[currency] = sharesDTO.currency
                    it[exchange] = sharesDTO.exchange
                    it[divYieldFlag] = sharesDTO.divYieldFlag
                    it[figi] = sharesDTO.figi
                    it[forIisFlag] = sharesDTO.forIisFlag
                    it[forQualInvestorFlag] = sharesDTO.forQualInvestorFlag
                    it[instrumentExchange] = sharesDTO.instrumentExchange
                    it[ipoDate] = sharesDTO.ipoDate
                    it[isin] = sharesDTO.isin
                    it[issueSize] = sharesDTO.issueSize
                    it[issueSizePlan] = sharesDTO.issueSizePlan
                    it[liquidityFlag] = sharesDTO.liquidityFlag
                    it[lot] = sharesDTO.lot
                    it[name] = sharesDTO.name
                    it[nominal_units] = sharesDTO.nominal_units ?: ""
                    it[nominal_nano] = sharesDTO.nominal_nano ?: 0L
                    it[otcFlag] = sharesDTO.otcFlag
                    it[positionUid] = sharesDTO.positionUid
                    it[realexchange_id ] = sharesDTO.realexchange_id
                    it[sector] = sharesDTO.sector
                    it[sellAvailableFlag] = sharesDTO.sellAvailableFlag
                    it[shareType ] = sharesDTO.shareType
                    it[ shortEnabledFlag] = sharesDTO.shortEnabledFlag
                    it[ ticker ] = sharesDTO.ticker
                    it[ tradingstatus_id] = sharesDTO.tradingstatus_id
                    it[ uid] = sharesDTO.uid
                    it[ weekendFlag] = sharesDTO.weekendFlag
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun updateShares(sharesDTO: SharesDTO) =
        try {
            transaction {
                Shares.update({ uid eq sharesDTO.uid }) {
                    it[apiTradeAvailableFlag] = sharesDTO.apiTradeAvailableFlag
                    it[assetUid] = sharesDTO.assetUid
                    it[blockedTcaFlag] = sharesDTO.blockedTcaFlag
                    it[brand_id] = sharesDTO.brand_id
                    it[buyAvailableFlag] = sharesDTO.buyAvailableFlag
                    it[classCode] = sharesDTO.classCode
                    it[countryOfRisk] = sharesDTO.countryOfRisk
                    it[countryOfRiskName] = sharesDTO.countryOfRiskName
                    it[currency] = sharesDTO.currency
                    it[exchange] = sharesDTO.exchange
                    it[divYieldFlag] = sharesDTO.divYieldFlag
                    it[figi] = sharesDTO.figi
                    it[forIisFlag] = sharesDTO.forIisFlag
                    it[forQualInvestorFlag] = sharesDTO.forQualInvestorFlag
                    it[instrumentExchange] = sharesDTO.instrumentExchange
                    it[ipoDate] = sharesDTO.ipoDate
                    it[isin] = sharesDTO.isin
                    it[issueSize] = sharesDTO.issueSize
                    it[issueSizePlan] = sharesDTO.issueSizePlan
                    it[liquidityFlag] = sharesDTO.liquidityFlag
                    it[lot] = sharesDTO.lot
                    it[name] = sharesDTO.name
                    it[nominal_units] = sharesDTO.nominal_units ?: ""
                    it[nominal_nano] = sharesDTO.nominal_nano ?: 0L
                    it[otcFlag] = sharesDTO.otcFlag
                    it[positionUid] = sharesDTO.positionUid
                    it[realexchange_id ] = sharesDTO.realexchange_id
                    it[sector] = sharesDTO.sector
                    it[sellAvailableFlag] = sharesDTO.sellAvailableFlag
                    it[shareType ] = sharesDTO.shareType
                    it[ shortEnabledFlag] = sharesDTO.shortEnabledFlag
                    it[ ticker ] = sharesDTO.ticker
                    it[ tradingstatus_id] = sharesDTO.tradingstatus_id
                    it[ weekendFlag] = sharesDTO.weekendFlag
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun getShares(uid: String): Response<SharesDTO> =
        try {
            transaction {
                val value = Shares.select { Shares.uid.eq(uid) }.single()
                Response(
                    success = true,
                    response =
                        SharesDTO(
                            apiTradeAvailableFlag = value[apiTradeAvailableFlag],
                            assetUid = value[assetUid],
                            blockedTcaFlag = value[blockedTcaFlag],
                            brand_id = value[brand_id],
                            buyAvailableFlag = value[buyAvailableFlag],
                            classCode = value[classCode],
                            countryOfRisk = value[countryOfRisk],
                            countryOfRiskName = value[countryOfRiskName],
                            currency = value[currency],
                            exchange = value[exchange],
                            divYieldFlag = value[divYieldFlag],
                            figi = value[figi],
                            forIisFlag = value[forIisFlag],
                            forQualInvestorFlag = value[forQualInvestorFlag],
                            instrumentExchange = value[instrumentExchange],
                            ipoDate = value[ipoDate],
                            isin = value[isin],
                            issueSize = value[issueSize],
                            issueSizePlan = value[issueSizePlan],
                            liquidityFlag = value[liquidityFlag],
                            lot = value[lot],
                            name = value[name],
                            nominal_units = value[nominal_units],
                            nominal_nano = value[nominal_nano ],
                            otcFlag = value[otcFlag],
                            positionUid = value[positionUid],
                            realexchange_id = value[realexchange_id],
                            sector = value[sector],
                            sellAvailableFlag = value[sellAvailableFlag],
                            shareType = value[shareType],
                            shortEnabledFlag = value[shortEnabledFlag],
                            ticker = value[ticker],
                            tradingstatus_id = value[tradingstatus_id],
                            uid = uid,
                            weekendFlag = value[weekendFlag],
                        ),
                )
            }
        } catch (e: Exception) {
            Response(
                success = false,
                error = ResponseError(errorCode = 42, errorMessage = e.message.toString()),
            )
        }

    fun equalsSharesDTO(sharesDTO: SharesDTO) {
        val databaseSearch = getShares(sharesDTO.uid)
        val result =
            if (databaseSearch.isSuccess) {
                updateShares(sharesDTO)
            } else {
                insertShares(sharesDTO)
            }

        println(result)
    }

    fun getSharesList(): Response<List<SharesDTO>> =
        transaction {
            val value = Shares.selectAll().toList()
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
                            SharesDTO(
                                apiTradeAvailableFlag = value[apiTradeAvailableFlag],
                                assetUid = value[assetUid],
                                blockedTcaFlag = value[blockedTcaFlag],
                                brand_id = value[brand_id],
                                buyAvailableFlag = value[buyAvailableFlag],
                                classCode = value[classCode],
                                countryOfRisk = value[countryOfRisk],
                                countryOfRiskName = value[countryOfRiskName],
                                currency = value[currency],
                                exchange = value[exchange],
                                divYieldFlag = value[divYieldFlag],
                                figi = value[figi],
                                forIisFlag = value[forIisFlag],
                                forQualInvestorFlag = value[forQualInvestorFlag],
                                instrumentExchange = value[instrumentExchange],
                                ipoDate = value[ipoDate],
                                isin = value[isin],
                                issueSize = value[issueSize],
                                issueSizePlan = value[issueSizePlan],
                                liquidityFlag = value[liquidityFlag],
                                lot = value[lot],
                                name = value[name],
                                nominal_units = value[nominal_units],
                                nominal_nano = value[nominal_nano ],
                                otcFlag = value[otcFlag],
                                positionUid = value[positionUid],
                                realexchange_id = value[realexchange_id],
                                sector = value[sector],
                                sellAvailableFlag = value[sellAvailableFlag],
                                shareType = value[shareType],
                                shortEnabledFlag = value[shortEnabledFlag],
                                ticker = value[ticker],
                                tradingstatus_id = value[tradingstatus_id],
                                uid = value[uid],
                                weekendFlag = value[weekendFlag],
                            )
                        },
                )
            }
        }
}
