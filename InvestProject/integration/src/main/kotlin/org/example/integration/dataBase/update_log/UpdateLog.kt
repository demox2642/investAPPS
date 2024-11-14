package org.example.integration.dataBase.update_log

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import ru.demox_bank.utils.ResponseDB

object UpdateLog : Table("update_log") {
    private val log = UpdateLog.varchar("log", 1000)

    fun insertUpdateLog(update_log: String) {
        try {
            transaction {
                UpdateLog.insert {
                    it[log] = update_log
                }
            }
            ResponseDB.Success(data = true)
        } catch (e: Exception) {
            ResponseDB.Failed(message = e.message!!)
        }
    }
}
