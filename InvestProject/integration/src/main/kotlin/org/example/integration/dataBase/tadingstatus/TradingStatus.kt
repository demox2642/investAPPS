package org.example.integration.dataBase.tadingstatus

import org.example.integration.dataBase.error_log.ErrorLog
import org.example.integration.utils.Settings
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.demox_bank.utils.ResponseDB
import ru.demox_bank.utils.ResponseError
import utils.Response

object TradingStatus : Table("tradingstatus") {
    private val id = TradingStatus.long("id").autoIncrement()
    private val name = TradingStatus.varchar("name", Settings.DataBase.StringLenth)
    private val description = TradingStatus.varchar("description", 100)

    fun insertTradingStatus(tradingStatusDTO: TradingStatusDTO): ResponseDB =
        try {
            transaction {
                TradingStatus.insert {
                    it[name] = tradingStatusDTO.name
                    it[description] = tradingStatusDTO.description ?: ""
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ErrorLog.insertErrorLog(errorLog = "RealExchangeUpdate ERROR: ${e.message!!}")
            ResponseDB.Failed(message = e.message!!)
        }

    fun getTradingStatusByValue(value: String): Response<TradingStatusDTOResponse> =
        try {
            transaction {
                val value = TradingStatus.select { name.eq(value) }.single()

                Response(
                    success = true,
                    response =
                        TradingStatusDTOResponse(
                            id = value[TradingStatus.id],
                            name = value[name],
                            description = value[description],
                        ),
                )
            }
        } catch (e: Exception) {
            ErrorLog.insertErrorLog(errorLog = "RealExchangeUpdate ERROR: ${e.message!!}")
            Response(
                success = false,
                error = ResponseError(errorCode = 42, errorMessage = e.message.toString()),
            )
        }

    fun getTradingStatusList(): Response<List<TradingStatusDTOResponse>> =
        transaction {
            val value = TradingStatus.selectAll().toList()
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
                            TradingStatusDTOResponse(
                                id = it[TradingStatus.id],
                                name = it[name],
                                description = it[description],
                            )
                        },
                )
            }
        }
}
