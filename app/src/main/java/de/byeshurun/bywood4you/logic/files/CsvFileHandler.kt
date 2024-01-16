package de.byeshurun.bywood4you.logic.files

import android.content.Context
import de.byeshurun.bywood4you.model.User
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class CsvFileHandler private constructor() {
    companion object {
        private const val USERS_CSV_FILE_NAME = "users.csv"

        @Volatile
        private var instance: CsvFileHandler? = null

        fun getInstance(): CsvFileHandler {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = CsvFileHandler()
                    }
                }
            }
            return instance!!
        }
    }

    fun exportUsersToCsvFile(context: Context, userToExport: List<User>) {
        try {
            val file = File(context.filesDir, USERS_CSV_FILE_NAME)
            val fileWriter = FileWriter(file)

            for (user in userToExport) {
                fileWriter.append(user.getAllAttributesAsCsvLine())
            }
            fileWriter.flush()
            fileWriter.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun importUsersFromCsvFile(context: Context): List<User> {
        val usersFromCsvFile = mutableListOf<User>()

        try {
            val file = File(context.filesDir, USERS_CSV_FILE_NAME)

            if (file.exists()) {


                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)

                var readCsvLine: String?

//                var eof = false;
//                while (!eof){
//                    val csvLine = bufferedReader.readLine()
//
//                    if (csvLine == null) {
//                        eof = true
//                    } else {
//                        val userFromFile = User(csvLine)
//                        usersFromFile.add(userFromFile)
//                    }
//                }

                while (bufferedReader.readLine().also { readCsvLine = it } != null) {
                    val userFromFile = User(readCsvLine)
                    usersFromCsvFile.add(userFromFile)
                }

                bufferedReader.close()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return usersFromCsvFile
    }
}