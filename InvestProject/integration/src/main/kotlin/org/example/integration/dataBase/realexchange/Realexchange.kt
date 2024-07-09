package org.example.integration.dataBase.realexchange

import org.example.integration.utils.Settings
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.demox_bank.utils.ResponseDB
import ru.demox_bank.utils.ResponseError
import utils.Response

object Realexchange : Table("realexchange") {
    private val id = Realexchange.long("id").autoIncrement()
    private val realexchangeValue = Realexchange.varchar("realexchangevalue", Settings.DataBase.StringLenth)
    private val description = Realexchange.varchar("description", 100)

    fun insertRealexchange(realexchangeDTO: RealexchangeDTO): ResponseDB =
        try {
            transaction {
                Realexchange.insert {
                    it[realexchangeValue] = realexchangeDTO.realexchangeValue
                    it[description] = realexchangeDTO.description ?: ""
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun getRealexchangeByValue(value: String): Response<RealexchangeDTOResponse> =
        try {
            transaction {
                val value = Realexchange.select { realexchangeValue.eq(value) }.single()

                Response(
                    success = true,
                    response =
                        RealexchangeDTOResponse(
                            id = value[Realexchange.id],
                            realexchangeValue = value[realexchangeValue],
                            description = value[description],
                        ),
                )
            }
        } catch (e: Exception) {
            Response(
                success = false,
                error = ResponseError(errorCode = 42, errorMessage = e.message.toString()),
            )
        }

    fun getRealexchangeList(): Response<List<RealexchangeDTOResponse>> =
        transaction {
            val value = Realexchange.selectAll().toList()
            if (value.isNullOrEmpty()) {
                Response(
                    success = true,
                    response = emptyList(),
                )
            } else {
                Response(
                    success = true,
                    response =
                        value.map {
                            RealexchangeDTOResponse(
                                id = it[Realexchange.id],
                                realexchangeValue = it[realexchangeValue],
                                description = it[description],
                            )
                        },
                )
            }
        }
}
