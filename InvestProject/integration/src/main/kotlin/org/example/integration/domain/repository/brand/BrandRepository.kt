package org.example.integration.domain.repository.brand

import org.example.integration.dataBase.brand.BrandDTO
import org.example.integration.dataBase.brand.BrandDTOResponse
import ru.demox_bank.utils.ResponseDB
import utils.Response

interface BrandRepository {
    suspend fun insertBrand(brandDTO: BrandDTO): ResponseDB

    suspend fun updateBrand(brandDTO: BrandDTO): ResponseDB

    suspend fun getBrandByLogoName(value: String): Response<BrandDTOResponse>

    suspend fun equalsBrandDTO(brandDTO: BrandDTO)

    suspend fun getBrandList(): Response<List<BrandDTOResponse>>

    suspend fun updateBrandList(brandList: List<BrandDTO>)
}
