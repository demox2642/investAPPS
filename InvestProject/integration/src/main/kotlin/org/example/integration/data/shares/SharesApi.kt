package org.example.integration.data.shares

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.integration.data.models.ServerCall
import org.example.integration.domain.repository.HttpClient
import org.example.integration.models.Instruments
import org.example.integration.models.shares.SharesTinkoffResponse

class SharesApi {
    suspend fun getSharesTinkoffResponse(): Instruments<SharesTinkoffResponse> {
        val response: Instruments<SharesTinkoffResponse> =
            HttpClient.client
                .post(
                    "https://sandbox-invest-public-api.tinkoff.ru/rest/tinkoff.public.invest.api.contract.v1.InstrumentsService/Shares",
                ) {
                    contentType(ContentType.Application.Json)
                    setBody(ServerCall("INSTRUMENT_STATUS_UNSPECIFIED"))
                }.body()
        return response
    }
}
