package org.example.integration.data.etf

import org.example.integration.dataBase.etf.Etf
import org.example.integration.dataBase.etf.EtfDTO
import org.example.integration.domain.repository.etf.EtfRepository
import org.example.integration.models.Instruments
import org.example.integration.models.etf.EtfTinkoffResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

class EtfRepositoryImpl(
    private val etfApi: EtfApi,
) : EtfRepository {
    override suspend fun getEtfFromTinkoffServer(): Instruments<EtfTinkoffResponse> = etfApi.getEtfFromTinkoffServer()

    override suspend fun insertEtf(etfDTO: EtfDTO): ResponseDB = Etf.insertEtf(etfDTO)

    override suspend fun updateEtf(etfDTO: EtfDTO): ResponseDB = Etf.updateEtf(etfDTO)

    override suspend fun getEtf(uid: String): Response<EtfDTO> = Etf.getEtf(uid)

    override suspend fun equalsEtfDTO(etfDTO: EtfDTO) = Etf.equalsEtfDTO(etfDTO)

    override suspend fun getEtfList(): Response<List<EtfDTO>> = Etf.getEtfList()
}
