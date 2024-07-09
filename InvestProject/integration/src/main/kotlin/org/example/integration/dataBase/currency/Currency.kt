@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.integration.dataBase.currency

import org.example.integration.utils.Settings
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.demox_bank.utils.ResponseDB
import ru.demox_bank.utils.ResponseError
import utils.Response

object Currency : Table("currency") {
    private val apiTradeAvailableFlag = Currency.bool("apiTradeAvailableFlag")
    private val blockedTcaFlag = Currency.bool("blockedTcaFlag")
    private val brand = Currency.long("brand_id")
    private val buyAvailableFlag = Currency.bool("buyAvailableFlag")
    private val classCode = Currency.varchar("classCode", 100)
    private val currency = Currency.varchar("currency", Settings.DataBase.StringLenth)
    private val exchange = Currency.varchar("exchange", Settings.DataBase.StringLenth)
    private val figi = Currency.varchar("figi", Settings.DataBase.StringLenth)
    private val forIisFlag = Currency.bool("forIisFlag")
    private val forQualInvestorFlag = Currency.bool("forQualInvestorFlag")
    private val isin = Currency.varchar("isin", Settings.DataBase.StringLenth)
    private val isoCurrencyName = Currency.varchar("isoCurrencyName", Settings.DataBase.StringLenth)
    private val name = Currency.varchar("name", Settings.DataBase.StringLenth)
    private val otcFlag = Currency.bool("otcFlag")
    private val positionUid = Currency.varchar("positionUid", Settings.DataBase.StringLenth)
    private val sellAvailableFlag = Currency.bool("sellAvailableFlag")
    private val shortEnabledFlag = Currency.bool("shortEnabledFlag")
    private val ticker = Currency.varchar("ticker", Settings.DataBase.StringLenth)
    private val uid = Currency.varchar("uid", Settings.DataBase.StringLenth)
    private val weekendFlag = Currency.bool("weekendFlag")

    fun insertCurrency(currencyDTO: CurrencyDTO): ResponseDB =
        try {
            transaction {
                Currency.insert {
                    it[apiTradeAvailableFlag] = currencyDTO.apiTradeAvailableFlag
                    it[blockedTcaFlag] = currencyDTO.blockedTcaFlag
                    it[brand] = currencyDTO.brand
                    it[buyAvailableFlag] = currencyDTO.buyAvailableFlag
                    it[classCode] = currencyDTO.classCode
                    it[currency] = currencyDTO.currency
                    it[exchange] = currencyDTO.exchange
                    it[figi] = currencyDTO.figi
                    it[forIisFlag] = currencyDTO.forIisFlag
                    it[forQualInvestorFlag] = currencyDTO.forQualInvestorFlag
                    it[isin] = currencyDTO.isin
                    it[isoCurrencyName] = currencyDTO.isoCurrencyName
                    it[name] = currencyDTO.name
                    it[otcFlag] = currencyDTO.otcFlag
                    it[positionUid] = currencyDTO.positionUid
                    it[sellAvailableFlag] = currencyDTO.sellAvailableFlag
                    it[shortEnabledFlag] = currencyDTO.shortEnabledFlag
                    it[ticker] = currencyDTO.ticker
                    it[uid] = currencyDTO.uid
                    it[weekendFlag] = currencyDTO.weekendFlag
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun updateCurrency(currencyDTO: CurrencyDTO): ResponseDB =
        try {
            transaction {
                Currency.update({ uid eq currencyDTO.uid }) {
                    it[apiTradeAvailableFlag] = currencyDTO.apiTradeAvailableFlag
                    it[blockedTcaFlag] = currencyDTO.blockedTcaFlag
                    it[brand] = currencyDTO.brand
                    it[buyAvailableFlag] = currencyDTO.buyAvailableFlag
                    it[classCode] = currencyDTO.classCode
                    it[currency] = currencyDTO.currency
                    it[exchange] = currencyDTO.exchange
                    it[figi] = currencyDTO.figi
                    it[forIisFlag] = currencyDTO.forIisFlag
                    it[forQualInvestorFlag] = currencyDTO.forQualInvestorFlag
                    it[isin] = currencyDTO.isin
                    it[isoCurrencyName] = currencyDTO.isoCurrencyName
                    it[name] = currencyDTO.name
                    it[otcFlag] = currencyDTO.otcFlag
                    it[positionUid] = currencyDTO.positionUid
                    it[sellAvailableFlag] = currencyDTO.sellAvailableFlag
                    it[shortEnabledFlag] = currencyDTO.shortEnabledFlag
                    it[ticker] = currencyDTO.ticker
                    it[weekendFlag] = currencyDTO.weekendFlag
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun getCurrency(uid: String): Response<CurrencyDTO> =
        try {
            transaction {
                val value = Currency.select { Currency.uid.eq(uid) }.single()

                Response(
                    success = true,
                    response =
                        CurrencyDTO(
                            apiTradeAvailableFlag = value[apiTradeAvailableFlag],
                            blockedTcaFlag = value[apiTradeAvailableFlag],
                            brand = value[brand],
                            buyAvailableFlag = value[buyAvailableFlag],
                            classCode = value[classCode],
                            currency = value[currency],
                            exchange = value[exchange],
                            figi = value[figi],
                            forIisFlag = value[forIisFlag],
                            forQualInvestorFlag = value[forQualInvestorFlag],
                            isin = value[isin],
                            isoCurrencyName = value[isoCurrencyName],
                            name = value[name],
                            otcFlag = value[otcFlag],
                            positionUid = value[positionUid],
                            sellAvailableFlag = value[sellAvailableFlag],
                            shortEnabledFlag = value[shortEnabledFlag],
                            ticker = value[ticker],
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

    fun getCurrencyName(name: String): Response<CurrencyDTO> =
        try {
            transaction {
                val value = Currency.select { isoCurrencyName.eq(name) }.single()

                Response(
                    success = true,
                    response =
                        CurrencyDTO(
                            apiTradeAvailableFlag = value[apiTradeAvailableFlag],
                            blockedTcaFlag = value[apiTradeAvailableFlag],
                            brand = value[brand],
                            buyAvailableFlag = value[buyAvailableFlag],
                            classCode = value[classCode],
                            currency = value[currency],
                            exchange = value[exchange],
                            figi = value[figi],
                            forIisFlag = value[forIisFlag],
                            forQualInvestorFlag = value[forQualInvestorFlag],
                            isin = value[isin],
                            isoCurrencyName = value[isoCurrencyName],
                            name = name,
                            otcFlag = value[otcFlag],
                            positionUid = value[positionUid],
                            sellAvailableFlag = value[sellAvailableFlag],
                            shortEnabledFlag = value[shortEnabledFlag],
                            ticker = value[ticker],
                            uid = value[uid],
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

    fun equalsCurrencyDTO(currencyDTO: CurrencyDTO) {
        val databaseSearch = getCurrency(currencyDTO.uid)
        val result =
            if (databaseSearch.isSuccess) {
                updateCurrency(currencyDTO)
            } else {
                insertCurrency(currencyDTO)
            }

        println(result)
    }

    fun getCurrencyList(): Response<List<CurrencyDTO>> =
        transaction {
            val value = Currency.selectAll().toList()
            if (value.isEmpty()) {
                Response(
                    success = true,
                    response = emptyList(),
                )
            } else {
                Response(
                    success = true,
                    response =
                        value.map {
                            CurrencyDTO(
                                apiTradeAvailableFlag = it[apiTradeAvailableFlag],
                                blockedTcaFlag = it[apiTradeAvailableFlag],
                                brand = it[brand],
                                buyAvailableFlag = it[buyAvailableFlag],
                                classCode = it[classCode],
                                currency = it[currency],
                                exchange = it[exchange],
                                figi = it[figi],
                                forIisFlag = it[forIisFlag],
                                forQualInvestorFlag = it[forQualInvestorFlag],
                                isin = it[isin],
                                isoCurrencyName = it[isoCurrencyName],
                                name = it[name],
                                otcFlag = it[otcFlag],
                                positionUid = it[positionUid],
                                sellAvailableFlag = it[sellAvailableFlag],
                                shortEnabledFlag = it[shortEnabledFlag],
                                ticker = it[ticker],
                                uid = it[uid],
                                weekendFlag = it[weekendFlag],
                            )
                        },
                )
            }
        }
}
