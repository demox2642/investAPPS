@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.integration.dataBase.brand

import org.example.integration.utils.Settings
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.demox_bank.utils.ResponseDB
import ru.demox_bank.utils.ResponseError
import utils.Response

object Brand : Table("brand") {
    private val id = Brand.long("id").autoIncrement()
    private val logoName = Brand.varchar("logoname", Settings.DataBase.StringLenth)
    private val logoBaseColor = Brand.varchar("logobasecolor", Settings.DataBase.StringLenth)
    private val textColor = Brand.varchar("textcolor", Settings.DataBase.StringLenth)

    fun insertBrand(brandDTO: BrandDTO): ResponseDB =
        try {
            transaction {
                Brand.insert {
                    it[logoName] = brandDTO.logoName
                    it[logoBaseColor] = brandDTO.logoBaseColor
                    it[textColor] = brandDTO.textColor
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun updateBrand(brandDTO: BrandDTO): ResponseDB =
        try {
            transaction {
                Brand.update({ logoName eq brandDTO.logoName }) {
                    it[logoBaseColor] = brandDTO.logoBaseColor
                    it[textColor] = brandDTO.textColor
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }

    fun getBrandByLogoName(value: String): Response<BrandDTOResponse> =
        try {
            transaction {
                val value = Brand.select { logoName.eq(value) }.single()

                Response(
                    success = true,
                    response =
                        BrandDTOResponse(
                            id = value[Brand.id],
                            logoName = value[logoName],
                            logoBaseColor = value[logoBaseColor],
                            textColor = value[textColor],
                        ),
                )
            }
        } catch (e: Exception) {
            Response(
                success = false,
                error = ResponseError(errorCode = 42, errorMessage = e.message.toString()),
            )
        }

    fun equalsBrandDTO(brandDTO: BrandDTO) {
        val databaseSearch = getBrandByLogoName(brandDTO.logoName)
        val result =
            if (databaseSearch.isSuccess) {
                updateBrand(brandDTO)
            } else {
                insertBrand(brandDTO)
            }

        println(result)
    }

    fun getBrandList(): Response<List<BrandDTOResponse>> =
        transaction {
            val value = Brand.selectAll().toList()
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
                            BrandDTOResponse(
                                id = it[Brand.id],
                                logoName = it[logoName],
                                logoBaseColor = it[logoBaseColor],
                                textColor = it[textColor],
                            )
                        },
                )
            }
        }
}
