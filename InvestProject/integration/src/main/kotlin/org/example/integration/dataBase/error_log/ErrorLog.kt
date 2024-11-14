package org.example.integration.dataBase.error_log

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import ru.demox_bank.utils.ResponseDB

object ErrorLog : Table("error_log") {
    private val log = ErrorLog.varchar("log", 1000)

    fun insertErrorLog(errorLog: String) {
        try {
            transaction {
                ErrorLog.insert {
                    it[log] = errorLog
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }
    }
}
