package org.example.integration.domain.repository.etf

import org.example.integration.dataBase.etf.EtfDTO
import org.example.integration.models.Instruments
import org.example.integration.models.etf.EtfTinkoffResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

interface EtfRepository {
    suspend fun getEtfFromTinkoffServer(): Instruments<EtfTinkoffResponse>

    suspend fun insertEtf(etfDTO: EtfDTO): ResponseDB

    suspend fun updateEtf(etfDTO: EtfDTO): ResponseDB

    suspend fun getEtf(uid: String): Response<EtfDTO>

    suspend fun equalsEtfDTO(etfDTO: EtfDTO)

    suspend fun getEtfList(): Response<List<EtfDTO>>
}
