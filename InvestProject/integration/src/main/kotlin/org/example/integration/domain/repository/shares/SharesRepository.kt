package org.example.integration.domain.repository.shares

import org.example.integration.dataBase.shares.SharesDTO
import org.example.integration.models.Instruments
import org.example.integration.models.shares.SharesTinkoffResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

interface SharesRepository {
    suspend fun getSharesFromTinkoffServer(): Instruments<SharesTinkoffResponse>

    suspend fun insertShares(sharesDTO: SharesDTO): ResponseDB

    suspend fun updateShares(sharesDTO: SharesDTO): ResponseDB

    suspend fun getShares(uid: String): Response<SharesDTO>

    suspend fun equalsSharesDTO(sharesDTO: SharesDTO)

    suspend fun getSharesList(): Response<List<SharesDTO>>
}
