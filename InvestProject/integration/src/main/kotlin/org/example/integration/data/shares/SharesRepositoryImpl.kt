package org.example.integration.data.shares

import org.example.integration.dataBase.shares.Shares
import org.example.integration.dataBase.shares.SharesDTO
import org.example.integration.domain.repository.shares.SharesRepository
import org.example.integration.models.Instruments
import org.example.integration.models.shares.SharesTinkoffResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

class SharesRepositoryImpl(
    private val sharesApi: SharesApi,
) : SharesRepository {
    override suspend fun getSharesFromTinkoffServer(): Instruments<SharesTinkoffResponse> = sharesApi.getSharesTinkoffResponse()

    override suspend fun insertShares(sharesDTO: SharesDTO): ResponseDB = Shares.insertShares(sharesDTO)

    override suspend fun updateShares(sharesDTO: SharesDTO): ResponseDB = Shares.updateShares(sharesDTO)

    override suspend fun getShares(uid: String): Response<SharesDTO> = Shares.getShares(uid)

    override suspend fun equalsSharesDTO(sharesDTO: SharesDTO) = Shares.equalsSharesDTO(sharesDTO)

    override suspend fun getSharesList(): Response<List<SharesDTO>> = Shares.getSharesList()
}
