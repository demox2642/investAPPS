package org.example.integration.data.brand

import org.example.integration.dataBase.brand.Brand
import org.example.integration.dataBase.brand.BrandDTO
import org.example.integration.dataBase.brand.BrandDTOResponse
import org.example.integration.domain.repository.brand.BrandRepository
import ru.demox_bank.utils.ResponseDB
import utils.Response

class BrandRepositoryImpl : BrandRepository {
    override suspend fun insertBrand(brandDTO: BrandDTO): ResponseDB = Brand.insertBrand(brandDTO)

    override suspend fun updateBrand(brandDTO: BrandDTO): ResponseDB = Brand.updateBrand(brandDTO)

    override suspend fun getBrandByLogoName(value: String): Response<BrandDTOResponse> = Brand.getBrandByLogoName(value)

    override suspend fun equalsBrandDTO(brandDTO: BrandDTO) = Brand.equalsBrandDTO(brandDTO)

    override suspend fun getBrandList(): Response<List<BrandDTOResponse>> = Brand.getBrandList()

    override suspend fun updateBrandList(brandList: List<BrandDTO>): String {
        val brandDB = Brand.getBrandList()

        return if (brandDB.isSuccess) {
            brandList.forEach {
                Brand.equalsBrandDTO(it)
            }
            "Brand UPDATE COMPLETE"
        } else {
            "Brand UPDATE ERROR ${brandDB.error}"
        }
    }
}
