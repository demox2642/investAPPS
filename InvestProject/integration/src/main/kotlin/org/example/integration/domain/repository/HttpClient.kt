package org.example.integration.domain.repository

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.integration.utils.Settings

object HttpClient {
    val client =
        HttpClient(CIO) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                        prettyPrint = true
                        isLenient = true
                    },
                )
            }

            install(Auth) {
                bearer {
                    refreshTokens {
                        BearerTokens(Settings.Token, "")
                    }
                }
            }
        }
}
